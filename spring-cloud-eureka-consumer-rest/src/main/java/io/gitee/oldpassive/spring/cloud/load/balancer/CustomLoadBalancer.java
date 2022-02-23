package io.gitee.oldpassive.spring.cloud.load.balancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

@Component
public class CustomLoadBalancer implements LoadBalancer {

  private final AtomicInteger atomicInteger = new AtomicInteger(0);

  public final int getAndIncrement() {
    int current;
    int next;
    do {
      current = this.atomicInteger.get();
      next = current >= Integer.MAX_VALUE ? 0 : current + 1;
    } while (!this.atomicInteger.compareAndSet(current, next));
    return next;
  }

  @Override
  public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
    int index = getAndIncrement() % serviceInstances.size();
    return serviceInstances.get(index);
  }
}
