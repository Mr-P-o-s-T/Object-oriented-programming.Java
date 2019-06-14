package org.post.web.controller;

import org.post.exception.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
	private static String login = "admin", pass = "admin";

	@GetMapping("/")
	public String all() {
		return "login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String checkLogin(@RequestParam() String name, @RequestParam() String password,
							 HttpServletResponse response) {
		if (name.equals(login) && password.equals(pass)) {
			response.addCookie(new Cookie("username", name));
			return "index";
		}
		else throw new ValidationException("Administrator authentication failed!");
	}

	@ExceptionHandler(ValidationException.class)
	public ModelAndView validationException(Exception ex) {
		ModelAndView err = new ModelAndView("error");
		err.addObject(RequestDispatcher.ERROR_MESSAGE, ex.getMessage());
		return err;
	}
}
