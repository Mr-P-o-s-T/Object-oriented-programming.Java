package org.post.web.controller;

import org.post.data.Bet;
import org.post.exception.ValidationException;
import org.post.service.BetService;
import org.post.web.controller.validation.Validate;
import org.post.web.controller.validation.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BetController {
  private final BetService service;

  public BetController(BetService service) {
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
                                   @RequestParam(name = "thrd_horse_id") String thrdHorseId) {

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
}
