package io.gitee.oldpassive.spring.cloud.load.balancer;

import java.util.List;
import org.springframework.cloud.client.ServiceInstance;

public interface LoadBalancer {

  ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
