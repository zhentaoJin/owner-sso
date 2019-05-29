package com.jzt.sso.conf;

import org.springframework.stereotype.Component;


/**
 * 获取重定向地址
 */
@Component
public class ApplicationProperties {

  private String reUrl;

  public String getReUrl() {
    return reUrl;
  }

  public void setReUrl(String reUrl) {
    this.reUrl = reUrl;
  }
}
