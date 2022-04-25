package com.atlassian.design.LB;

public interface LoadBalancer {
  String getServerInstance(String serviceName);
}
