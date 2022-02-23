package io.gitee.oldpassive.spring.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.gitee.oldpassive.spring.cloud.exception.ServiceException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Service
public class HystrixProviderService {

  /*
   服务降级
  */
  public String simulateSuccess(Integer id) {
    return Thread.currentThread().getName() + " Success ID: " + id;
  }

  // 服务超时或服务异常均会调用降级方法
  @HystrixCommand(
      fallbackMethod = "timeoutHandler",
      commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
      })
  public String simulateTimeout(Integer id) {
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    return "Thread: " + Thread.currentThread().getName() + " ID: " + id + " Time Consumed: 3s";
  }

  public String timeoutHandler(Integer id) {
    return "Thread: " + Thread.currentThread().getName() + " ID: " + id + " timeout.";
  }

  /*
   服务熔断
  */
  @HystrixCommand(
      fallbackMethod = "circuitBreakerHandler",
      commandProperties = {
        // 是否开启断路器
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
        // 请求次数
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
        // 时间窗口期
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
        // 失败率达到多少后跳闸
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
      })
  public String simulateCircuitBreaker(@PathVariable("id") Integer id) {
    if (id < 0) {
      throw new ServiceException("ID cannot be negative!");
    }
    return Thread.currentThread().getName() + " Serial ID: " + IdUtil.simpleUUID();
  }

  public String circuitBreakerHandler(@PathVariable("id") Integer id) {
    return "ID cannot be negative! ID: " + id;
  }
}
