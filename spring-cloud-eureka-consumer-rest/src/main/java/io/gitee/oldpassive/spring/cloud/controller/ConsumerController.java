package io.gitee.oldpassive.spring.cloud.controller;

import io.gitee.oldpassive.spring.cloud.entities.CommonResult;
import io.gitee.oldpassive.spring.cloud.entities.ProviderEntity;
import io.gitee.oldpassive.spring.cloud.load.balancer.LoadBalancer;
import java.net.URI;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/consumer")
@SuppressWarnings("rawtypes")
public class ConsumerController {

  public static final String PROVIDER_URL = "http://CLOUD-SERVICE-PROVIDER";

  @Resource private RestTemplate restTemplate;

  @Resource private LoadBalancer loadBalancer;

  @Resource private DiscoveryClient discoveryClient;

  @GetMapping("/provider/create")
  public CommonResult create(ProviderEntity providerEntity) {
    return restTemplate.postForObject(
        PROVIDER_URL + "/provider/create", providerEntity, CommonResult.class);
  }

  @GetMapping("/provider/get/{id}")
  public CommonResult get(@PathVariable("id") Long id) {
    return restTemplate.getForObject(PROVIDER_URL + "/provider/get/" + id, CommonResult.class);
  }

  @GetMapping("/provider/get/entity/{id}")
  public CommonResult getProviderEntity(@PathVariable("id") Long id) {
    ResponseEntity<CommonResult> entity =
        restTemplate.getForEntity(PROVIDER_URL + "/provider/get/" + id, CommonResult.class);
    if (entity.getStatusCode().is2xxSuccessful()) {
      return entity.getBody();
    } else {
      return new CommonResult<>(444, "Operation failed!");
    }
  }

  @GetMapping("/provider/port")
  public String getServerPort() {
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-SERVICE-PROVIDER");
    if (instances == null || instances.isEmpty()) {
      return null;
    }
    ServiceInstance serviceInstance = loadBalancer.instance(instances);
    URI uri = serviceInstance.getUri();
    return restTemplate.getForObject(uri + "/provider/port", String.class);
  }
}
