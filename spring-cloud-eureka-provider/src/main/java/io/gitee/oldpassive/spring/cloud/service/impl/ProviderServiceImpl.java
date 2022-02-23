package io.gitee.oldpassive.spring.cloud.service.impl;

import io.gitee.oldpassive.spring.cloud.dao.ProviderDao;
import io.gitee.oldpassive.spring.cloud.entities.ProviderEntity;
import io.gitee.oldpassive.spring.cloud.service.ProviderService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {

  @Resource private ProviderDao providerDao;

  public int create(ProviderEntity providerEntity) {
    return providerDao.create(providerEntity);
  }

  public ProviderEntity getById(Long id) {
    return providerDao.getById(id);
  }
}
