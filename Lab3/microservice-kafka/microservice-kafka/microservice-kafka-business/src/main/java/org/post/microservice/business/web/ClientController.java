package org.post.microservice.business.web;

import org.post.microservice.business.events.TokenKafkaListener;
import org.post.microservice.business.exceptions.ValidationException;
import org.post.microservice.business.services.ClientService;
import org.post.microservice.business.web.validation.Validate;
import org.post.microservice.business.web.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ClientController {
  private final KafkaTemplate<String, String> producer;
  private final TokenKafkaListener listener;

  private final ClientService service;

  @Autowired
  public ClientController(KafkaTemplate<String, String> producer, TokenKafkaListener listener, ClientService service) {
    this.producer = producer;
    this.listener = listener;
    this.service = service;
  }

  @GetMapping("/client")
  public ModelAndView findAllClients(HttpServletRequest request, ModelAndView modelAndView) {
    List tmp = service.findAllClients();
    request.setAttribute("clients", tmp);
    modelAndView.setViewName("client");
    return modelAndView;
  }

  @PostMapping("/client")
  public ModelAndView createClient(HttpServletRequest request, ModelAndView modelAndView,
								   @RequestParam(name = "firstname") String firstname,
								   @RequestParam(name = "lastname") String lastname,
                                   @RequestParam(name = "accessToken", required = false) String accessToken) {
    try {
      Validator.initValidation();
      Validator.validate(firstname, Validate.STRING);
      Validator.validate(lastname, Validate.STRING);

      service.createClient(Validator.getString(), Validator.getString());
    }
    finally {
      Validator.finishValidation();
    }

    if (!listener.getAccessTokens().containsValue(accessToken)) {
      throw new ValidationException("Access error...");
    }
    else {
      Pair<String, String> logPass = getLoginPassword(accessToken);
      listener.setLogin(logPass.getFirst());
      listener.setPassword(logPass.getSecond());
      producer.send("auth", accessToken);
    }

    request.setAttribute("clients", service.findAllClients());
    modelAndView.setViewName("client");
    return modelAndView;
  }

  @ExceptionHandler(ValidationException.class)
  public ModelAndView validationException(Exception ex) {
    ModelAndView err = new ModelAndView("error");
    err.addObject(RequestDispatcher.ERROR_MESSAGE, ex.getMessage());
    return err;
  }

  private Pair<String, String> getLoginPassword(String accessToken) {
    for (Map.Entry<Pair<String, String>, String> entry : listener.getAccessTokens().entrySet())
      if (accessToken.equals(entry.getValue())) return entry.getKey();

    return null;
  }
}
