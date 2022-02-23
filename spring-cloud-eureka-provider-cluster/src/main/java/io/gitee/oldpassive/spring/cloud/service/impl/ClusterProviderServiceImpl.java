package io.gitee.oldpassive.spring.cloud.service.impl;

import io.gitee.oldpassive.spring.cloud.dao.ClusterProviderDao;
import io.gitee.oldpassive.spring.cloud.entities.ProviderEntity;
import io.gitee.oldpassive.spring.cloud.service.ClusterProviderService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ClusterProviderServiceImpl implements ClusterProviderService {

  @Resource private ClusterProviderDao clusterProviderDao;

  public int create(ProviderEntity providerEntity) {
    return clusterProviderDao.create(providerEntity);
  }

  public ProviderEntity getById(Long id) {
    return clusterProviderDao.getById(id);
  }
}
