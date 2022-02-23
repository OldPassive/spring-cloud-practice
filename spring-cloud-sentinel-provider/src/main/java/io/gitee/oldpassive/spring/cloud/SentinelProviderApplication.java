package io.gitee.oldpassive.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(SentinelProviderApplication.class, args);
  }
}
