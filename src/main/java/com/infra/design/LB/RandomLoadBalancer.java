package com.atlassian.design.LB;

import java.util.List;
import java.util.Random;

public class RandomLoadBalancer implements LoadBalancer {

  @Override
  public String getServerInstance(String serviceName) {

    List<String> servers = ServerRegistry.getInstance().getServers();

    Integer index = new Random().nextInt(servers.size());
    return servers.get(index);
  }
  
}
