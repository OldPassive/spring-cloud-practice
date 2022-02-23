package io.gitee.oldpassive.spring.cloud.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class NacosConsumerController {

  @Resource private RestTemplate restTemplate;

  @Value("${service-url.nacos-user-service}")
  private String serverURL;

  @GetMapping(value = "/provider/nacos/{id}")
  public String paymentInfo(@PathVariable("id") Long id) {
    return restTemplate.getForObject(serverURL + "/provider/nacos/" + id, String.class);
  }
}
