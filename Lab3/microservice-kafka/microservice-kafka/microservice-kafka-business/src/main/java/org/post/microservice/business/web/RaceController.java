package org.post.microservice.business.web;

import org.post.microservice.business.events.TokenKafkaListener;
import org.post.microservice.business.exceptions.ValidationException;
import org.post.microservice.business.services.RaceService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class RaceController {
  private final KafkaTemplate<String, String> producer;
  private final TokenKafkaListener listener;

  private final RaceService service;

  @Autowired
  public RaceController(KafkaTemplate<String, String> producer, TokenKafkaListener listener, RaceService service) {
    this.producer = producer;
    this.listener = listener;
    this.service = service;
  }

  @GetMapping("/race")
  public ModelAndView findAllRaces(HttpServletRequest request, ModelAndView modelAndView) {
    List tmp = service.findAllRaces();
    request.setAttribute("races", tmp);
    modelAndView.setViewName("race");
    return modelAndView;
  }

  @PostMapping("/race")
  public ModelAndView createRace(HttpServletRequest request, ModelAndView modelAndView,
								 @RequestParam(name = "racecourse") String racecourse,
								 @RequestParam(name = "date") String date,
                                 @RequestParam(name = "accessToken", required = false) String accessToken) {
    try {
      Validator.initValidation();
      Validator.validate(racecourse, Validate.STRING);
      Validator.validate(date, Validate.DATE);

      service.createRace(Validator.getString(), Validator.getDate());
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

    request.setAttribute("races", service.findAllRaces());
    modelAndView.setViewName("race");
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
