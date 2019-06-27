package org.post.microservice.business.web;

import org.post.microservice.business.entities.Bet;
import org.post.microservice.business.events.TokenKafkaListener;
import org.post.microservice.business.exceptions.ValidationException;
import org.post.microservice.business.services.BetService;
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
public class BetController {
  private final KafkaTemplate<String, String> producer;
  private final TokenKafkaListener listener;

  private final BetService service;

  @Autowired
  public BetController(KafkaTemplate<String, String> producer, TokenKafkaListener listener, BetService service) {
    this.producer = producer;
    this.listener = listener;
    this.service = service;
  }

  @GetMapping("/bet")
  public ModelAndView findAllBets(HttpServletRequest request, ModelAndView modelAndView) {
    List<Bet> tmp= service.findAllBets();
    request.setAttribute("bets", tmp);
    modelAndView.setViewName("bet");
    return modelAndView;
  }

  @PostMapping("/bet")
  public ModelAndView createBet(HttpServletRequest request, ModelAndView modelAndView,
								@RequestParam(name = "client_id") String clientId,
								@RequestParam(name = "race_id") String raceId,
								@RequestParam(name = "horse_id") String horseId,
								@RequestParam(name = "bet") String _bet,
								@RequestParam(name = "bet_type") String betType,
								@RequestParam(name = "scnd_horse_id") String scndHorseId,
								@RequestParam(name = "thrd_horse_id") String thrdHorseId,
                                @RequestParam(name = "accessToken", required = false) String accessToken) {

    try {
      Validator.initValidation();
      Validator.validate(clientId, Validate.LONG);
      Validator.validate(raceId, Validate.LONG);
      Validator.validate(horseId, Validate.LONG);
      Validator.validate(_bet, Validate.FLOAT);
      Validator.validate(betType, Validate.OPTIONS);
      Validator.validate(scndHorseId, Validate.NULLABLE_LONG);
      Validator.validate(thrdHorseId, Validate.NULLABLE_LONG);

      service.createBet(Validator.getLong(), Validator.getLong(), Validator.getLong(), Validator.getFloat(),
              Validator.getString(), Validator.getLong(), Validator.getLong());
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

    request.setAttribute("bets", service.findAllBets());
    modelAndView.setViewName("bet");
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
