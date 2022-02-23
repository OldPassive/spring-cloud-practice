package io.gitee.oldpassive.spring.cloud.service;

import io.gitee.oldpassive.spring.cloud.service.impl.HystrixConsumerServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(
    value = "CLOUD-HYSTRIX-SERVICE-PROVIDER",
    fallback = HystrixConsumerServiceFallback.class)
public interface HystrixConsumerService {

  @GetMapping("/hystrix/provider/ok/{id}")
  String requestSuccess(@PathVariable("id") Integer id);

  @GetMapping("/hystrix/provider/timeout/{id}")
  String requestTimeout(@PathVariable("id") Integer id);
}
