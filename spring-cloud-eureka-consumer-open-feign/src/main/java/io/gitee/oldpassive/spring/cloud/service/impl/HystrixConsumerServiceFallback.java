package io.gitee.oldpassive.spring.cloud.service.impl;

import io.gitee.oldpassive.spring.cloud.service.HystrixConsumerService;
import org.springframework.stereotype.Component;

@Component
public class HystrixConsumerServiceFallback implements HystrixConsumerService {

  @Override
  public String requestSuccess(Integer id) {
    return "Success fallback.";
  }

  @Override
  public String requestTimeout(Integer id) {
    return "Timeout fallback.";
  }
}
