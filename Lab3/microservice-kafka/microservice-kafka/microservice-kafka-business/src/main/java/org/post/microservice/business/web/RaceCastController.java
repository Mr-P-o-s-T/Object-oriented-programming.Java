package org.post.microservice.business.web;

import org.post.microservice.business.events.TokenKafkaListener;
import org.post.microservice.business.exceptions.ValidationException;
import org.post.microservice.business.services.RaceCastService;
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
public class RaceCastController {
  private final KafkaTemplate<String, String> producer;
  private final TokenKafkaListener listener;

  private final RaceCastService service;

  @Autowired
  public RaceCastController(KafkaTemplate<String, String> producer, TokenKafkaListener listener, RaceCastService service) {
    this.producer = producer;
    this.listener = listener;
    this.service = service;
  }

  @GetMapping("/race_cast")
  public ModelAndView findAllRaceCasts(HttpServletRequest request, ModelAndView modelAndView) {
    List tmp = service.findAllRaceCasts();
    request.setAttribute("raceCasts", tmp);
    modelAndView.setViewName("race_cast");
    return modelAndView;
  }

  @PostMapping("/race_cast")
  public ModelAndView createRaceCast(HttpServletRequest request, ModelAndView modelAndView,
									 @RequestParam(name = "race_id") String raceId,
									 @RequestParam(name = "horse_id") String horseId,
									 @RequestParam(name = "jockey_firstname") String firstname,
									 @RequestParam(name = "jockey_lastname") String lastname,
									 @RequestParam(name = "coefficient") String coefficient,
                                     @RequestParam(name = "accessToken", required = false) String accessToken) {
    try {
      Validator.initValidation();
      Validator.validate(raceId, Validate.LONG);
      Validator.validate(horseId, Validate.LONG);
      Validator.validate(firstname, Validate.STRING);
      Validator.validate(lastname, Validate.STRING);
      Validator.validate(coefficient, Validate.FLOAT);

      service.createRaceCast(Validator.getLong(), Validator.getLong(), Validator.getString(), Validator.getString(),
              Validator.getFloat());
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

    request.setAttribute("raceCasts", service.findAllRaceCasts());
    modelAndView.setViewName("race_cast");
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
