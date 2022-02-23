package io.gitee.oldpassive.spring.cloud.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderEntity implements Serializable {

  private Long id;

  private String serial;
}
