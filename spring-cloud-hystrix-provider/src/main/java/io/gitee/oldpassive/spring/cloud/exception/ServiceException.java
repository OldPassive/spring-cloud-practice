package io.gitee.oldpassive.spring.cloud.exception;

public class ServiceException extends RuntimeException {

  public ServiceException(String msg) {
    super(msg);
  }
}
