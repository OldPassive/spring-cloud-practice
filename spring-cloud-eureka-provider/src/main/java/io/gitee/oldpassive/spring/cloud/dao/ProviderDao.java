package io.gitee.oldpassive.spring.cloud.dao;

import io.gitee.oldpassive.spring.cloud.entities.ProviderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProviderDao {

  int create(ProviderEntity providerEntity);

  ProviderEntity getById(@Param("id") Long id);
}
