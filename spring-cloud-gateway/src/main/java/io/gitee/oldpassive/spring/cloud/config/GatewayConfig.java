package io.gitee.oldpassive.spring.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
    RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
    routes.route("path_route", r -> r.path("/hot").uri("https://www.zhihu.com/hot")).build();
    return routes.build();
  }
}
