package org.hreinn.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InputController {

  @GetMapping("/input")
  public String getInput(){
    return "Ok";
  }
}
