package io.gitee.oldpassive.spring.cloud.service;

import io.gitee.oldpassive.spring.cloud.entities.ProviderEntity;
import org.apache.ibatis.annotations.Param;

public interface ProviderService {

  int create(ProviderEntity providerEntity);

  ProviderEntity getById(@Param("id") Long id);
}
