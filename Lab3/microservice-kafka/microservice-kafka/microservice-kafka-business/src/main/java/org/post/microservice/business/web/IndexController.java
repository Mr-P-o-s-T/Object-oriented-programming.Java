package org.post.microservice.business.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  @GetMapping("/")
  public String all() {
    return "index";
  }

  @GetMapping("/index")
  public String index() {
    return "index";
  }
}
