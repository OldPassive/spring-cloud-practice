package io.gitee.oldpassive.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosProviderController {

  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/provider/nacos/{id}")
  public String getServerPort(@PathVariable("id") Integer id) {
    return "Service registered on Nacos, Server Port: " + "\t id" + id;
  }
}
