package io.gitee.oldpassive.spring.cloud.controller;

import io.gitee.oldpassive.spring.cloud.service.HystrixProviderService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hystrix/provider")
public class HystrixProviderController {

  @Resource private HystrixProviderService hystrixProviderService;

  @Value("${server.port}")
  private String serverPort;

  public static final String RESULT = "result";

  @GetMapping("/ok/{id}")
  public String requestSuccess(@PathVariable("id") Integer id) {
    String res = hystrixProviderService.simulateSuccess(id);
    log.info(RESULT + ": " + res);
    return res;
  }

  @GetMapping("/timeout/{id}")
  public String requestTimeout(@PathVariable("id") Integer id) {
    String res = hystrixProviderService.simulateTimeout(id);
    log.info(RESULT + ": " + res);
    return res;
  }

  /*
   服务熔断
  */
  @GetMapping("/circuit/break/{id}")
  public String requestCircuitBreaker(@PathVariable("id") Integer id) {
    String res = hystrixProviderService.simulateCircuitBreaker(id);
    log.info(RESULT + ": " + res);
    return res;
  }
}
