package io.gitee.oldpassive.spring.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelProviderController {

  @GetMapping("/test")
  public String testSentinelConnection() {
    return "Sentinel Connected";
  }
}
