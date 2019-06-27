package org.post.web.controller;

import org.post.exception.ValidationException;
import org.post.service.RaceCastService;
import org.post.web.controller.validation.Validate;
import org.post.web.controller.validation.Validator;
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

@Controller
public class RaceCastController {
  private final RaceCastService service;

  public RaceCastController(RaceCastService service) {
    this.service = service;
  }

  @GetMapping("/race_cast")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ModelAndView findAllRaceCasts(HttpServletRequest request, ModelAndView modelAndView) {
    List tmp = service.findAllRaceCasts();
    request.setAttribute("raceCasts", tmp);
    modelAndView.setViewName("race_cast");
    return modelAndView;
  }

  @PostMapping("/race_cast")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ModelAndView createRaceCast(HttpServletRequest request, ModelAndView modelAndView,
                                   @RequestParam(name = "race_id") String raceId,
                                   @RequestParam(name = "horse_id") String horseId,
                                   @RequestParam(name = "jockey_firstname") String firstname,
                                   @RequestParam(name = "jockey_lastname") String lastname,
                                   @RequestParam(name = "coefficient") String coefficient) {
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
}
