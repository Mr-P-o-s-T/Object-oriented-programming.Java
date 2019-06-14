package org.post.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  @GetMapping("/")
  public String all() {
    return "login";
  }

  @GetMapping("/index")
  public String index() {
    return "index";
  }
}
