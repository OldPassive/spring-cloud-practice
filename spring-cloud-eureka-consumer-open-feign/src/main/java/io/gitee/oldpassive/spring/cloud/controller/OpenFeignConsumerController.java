package io.gitee.oldpassive.spring.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.gitee.oldpassive.spring.cloud.entities.CommonResult;
import io.gitee.oldpassive.spring.cloud.entities.ProviderEntity;
import io.gitee.oldpassive.spring.cloud.service.HystrixConsumerService;
import io.gitee.oldpassive.spring.cloud.service.OpenFeignProviderService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "globalFallback")
public class OpenFeignConsumerController {

  @Resource private OpenFeignProviderService openFeignProviderService;

  @Resource private HystrixConsumerService hystrixConsumerService;

  public static final String GLOBAL_FALLBACK_MSG = "Global fallback handler";

  @GetMapping(value = "/provider/get/{id}")
  public CommonResult<ProviderEntity> getById(@PathVariable("id") Long id) {
    return openFeignProviderService.getById(id);
  }

  @GetMapping(value = "/provider/open-feign/timeout")
  public String simulateOpenFeignTimeout() {
    return openFeignProviderService.simulateOpenFeignTimeout();
  }

  @GetMapping("/hystrix/provider/ok/{id}")
  public String simulateHystrixSuccess(@PathVariable("id") Integer id) {
    return hystrixConsumerService.requestSuccess(id);
  }

  @HystrixCommand
  @GetMapping("/hystrix/provider/timeout/{id}")
  public String simulateHystrixTimeout(@PathVariable("id") Integer id) {
    return hystrixConsumerService.requestTimeout(id);
  }

  public String timeoutHandler(@PathVariable("id") Integer id) {
    return "Consumer timeout found! ID: " + id;
  }

  public String globalFallback() {
    return GLOBAL_FALLBACK_MSG;
  }
}
