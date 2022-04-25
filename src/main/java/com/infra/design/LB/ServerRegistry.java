package com.atlassian.design.LB;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ServerRegistry {
  private ConcurrentHashMap<String, Integer> serverWeights;;
  private static ServerRegistry serverRegistry;

  private ServerRegistry() {
      serverWeights = new ConcurrentHashMap<>();
      initServers();
  }

  private void initServers() {
    serverWeights.put("100.100.100.1", 1);
    serverWeights.put("100.100.100.2", 2);
    serverWeights.put("100.100.100.3", 3);
    serverWeights.put("100.100.100.4", 4);
    serverWeights.put("100.100.100.5", 5);
  }

  public void addServerWithWeight(String server, int weight) {
    serverWeights.put(server, weight);
  }

  public static ServerRegistry getInstance() {
    if(serverRegistry == null) {
      serverRegistry = new ServerRegistry();
    }
    return serverRegistry;
  }

  public List<String> getServers() {
    ArrayList<String> servers = new ArrayList<>();
    for(String server : serverWeights.keySet()) {
      servers.add(server);
    }
    return servers;
  }
  
}
