package org.post.web.controller;

import org.post.exception.ValidationException;
import org.post.service.ClientService;
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
public class ClientController {
  private final ClientService service;

  public ClientController(ClientService service) {
    this.service = service;
  }

  @GetMapping("/client")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ModelAndView findAllClients(HttpServletRequest request, ModelAndView modelAndView) {
    List tmp = service.findAllClients();
    request.setAttribute("clients", tmp);
    modelAndView.setViewName("client");
    return modelAndView;
  }

  @PostMapping("/client")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public ModelAndView createClient(HttpServletRequest request, ModelAndView modelAndView,
                                   @RequestParam(name = "firstname") String firstname,
                                   @RequestParam(name = "lastname") String lastname) {
    try {
      Validator.initValidation();
      Validator.validate(firstname, Validate.STRING);
      Validator.validate(lastname, Validate.STRING);

      service.createClient(Validator.getString(), Validator.getString());
    }
    finally {
      Validator.finishValidation();
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
}
