package org.post.web.controller;

import org.post.exception.ValidationException;
import org.post.service.RaceService;
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
import java.sql.Date;
import java.util.List;

@Controller
public class RaceController {
  private final RaceService service;

  public RaceController(RaceService service) {
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
                                   @RequestParam(name = "date") String date) {
    try {
      Validator.initValidation();
      Validator.validate(racecourse, Validate.STRING);
      Validator.validate(date, Validate.DATE);

      service.createRace(Validator.getString(), Validator.getDate());
    }
    finally {
      Validator.finishValidation();
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
}
