package io.gitee.oldpassive.spring.cloud.controller;

import io.gitee.oldpassive.spring.cloud.entities.CommonResult;
import io.gitee.oldpassive.spring.cloud.entities.ProviderEntity;
import io.gitee.oldpassive.spring.cloud.service.ProviderService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/provider")
public class ProviderController {

  @Resource private ProviderService providerService;

  @Value("${server.port}")
  private String serverPort;

  @Resource private DiscoveryClient discoveryClient;

  @PostMapping(value = "/create")
  public CommonResult<Integer> create(@RequestBody ProviderEntity providerEntity) {
    int result = providerService.create(providerEntity);
    log.info("Insertion result: " + result);
    return result > 0
        ? new CommonResult<>(
            200, "Successfully insert into database. Server Port: " + serverPort, result)
        : new CommonResult<>(444, "Fail to insert into database", null);
  }

  @GetMapping(value = "/get/{id}")
  public CommonResult<ProviderEntity> getById(@PathVariable("id") Long id) {
    ProviderEntity providerEntity = providerService.getById(id);
    log.info("Query result: " + providerEntity);
    return providerEntity != null
        ? new CommonResult<>(200, "Successful query. Server Port: " + serverPort, providerEntity)
        : new CommonResult<>(444, "Result not found, query ID: " + id, null);
  }

  @GetMapping("/discovery")
  public Object discovery() {
    discoveryClient.getServices().forEach(s -> log.info("element: " + s));
    discoveryClient
        .getInstances("CLOUD-SERVICE-PROVIDER")
        .forEach(
            i ->
                log.info(
                    i.getServiceId()
                        + "\t"
                        + i.getHost()
                        + "\t"
                        + i.getPort()
                        + "\t"
                        + i.getUri()));
    return discoveryClient;
  }

  @GetMapping(value = "/port")
  public String getServerPort() {
    return serverPort;
  }

  @GetMapping(value = "/open-feign/timeout")
  public String simulateOpenFeignTimeout() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    return serverPort;
  }
}
