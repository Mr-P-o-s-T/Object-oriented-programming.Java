package org.post.web.controller;

import org.post.exception.ValidationException;
import org.post.service.HorseService;
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
public class HorseController {
  private final HorseService service;

  public HorseController(HorseService service) {
    this.service = service;
  }

  @GetMapping("/horse")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ModelAndView findAllHorses(HttpServletRequest request, ModelAndView modelAndView) {
    List tmp = service.findAllHorses();
    request.setAttribute("horses", tmp);
    modelAndView.setViewName("horse");
    return modelAndView;
  }

  @PostMapping("/horse")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ModelAndView createHorse(HttpServletRequest request, ModelAndView modelAndView,
                                   @RequestParam(name = "horse_nickname") String nickname) {
    try {
      Validator.initValidation();
      Validator.validate(nickname, Validate.STRING);

      service.createHorse(Validator.getString());
    }
    finally {
      Validator.finishValidation();
    }

    request.setAttribute("horses", service.findAllHorses());
    modelAndView.setViewName("horse");
    return modelAndView;
  }

  @ExceptionHandler(ValidationException.class)
  public ModelAndView validationException(Exception ex) {
    ModelAndView err = new ModelAndView("error");
    err.addObject(RequestDispatcher.ERROR_MESSAGE, ex.getMessage());
    return err;
  }
}
