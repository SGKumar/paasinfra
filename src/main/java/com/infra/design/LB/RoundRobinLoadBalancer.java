package com.atlassian.design.LB;

import java.util.List;

public class RoundRobinLoadBalancer implements LoadBalancer {
  private static Integer index = 0;

  @Override
  public String getServerInstance(String serviceName) {

    List<String> servers = ServerRegistry.getInstance().getServers();
    String server = null;
    synchronized(index) {
      index++;
      index %= servers.size();
      server = servers.get(index);
    }
    return server;
  }
  
}
