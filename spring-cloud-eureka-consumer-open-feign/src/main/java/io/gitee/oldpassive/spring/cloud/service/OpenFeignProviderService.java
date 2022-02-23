package io.gitee.oldpassive.spring.cloud.service;

import io.gitee.oldpassive.spring.cloud.entities.CommonResult;
import io.gitee.oldpassive.spring.cloud.entities.ProviderEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-SERVICE-PROVIDER")
public interface OpenFeignProviderService {

  @GetMapping(value = "/provider/get/{id}")
  CommonResult<ProviderEntity> getById(@PathVariable("id") Long id);

  @GetMapping(value = "/provider/open-feign/timeout")
  String simulateOpenFeignTimeout();
}
