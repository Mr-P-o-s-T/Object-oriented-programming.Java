package org.post.microservice.business.web;

import org.post.microservice.business.events.TokenKafkaListener;
import org.post.microservice.business.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

@Controller
public class LoginController {
	private final KafkaTemplate<String, String> producer;
	private final TokenKafkaListener listener;

	@Autowired
	public LoginController(KafkaTemplate<String, String> producer, TokenKafkaListener listener) {
		this.producer = producer;
		this.listener = listener;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String checkLogin(@RequestParam() String login, @RequestParam() String password, HttpServletResponse
			response) throws InterruptedException, ExecutionException {
		synchronized (listener) {
			listener.setLogin(login);
			listener.setPassword(password);
			producer.send("auth", login + ":" + password).get();
			listener.wait();
		}

		String token = listener.getAccessTokens().get(Pair.of(login, password));
		if (token != null) response.addCookie(new Cookie("accessToken", token));
		else throw new ValidationException("Wrong login or password...");
		response.addCookie(new Cookie("username", login));

		return "index";
	}

	@ExceptionHandler(ValidationException.class)
	public ModelAndView validationException(Exception ex) {
		ModelAndView err = new ModelAndView("error");
		err.addObject(RequestDispatcher.ERROR_MESSAGE, ex.getMessage());
		return err;
	}
}
