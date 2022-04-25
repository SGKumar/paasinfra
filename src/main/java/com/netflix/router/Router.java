package com.netflix.router;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.concurrent.AtomicInitializer;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class Router {
  private List<RouteParams> routerConfig;

  public Router() {
    routerConfig = RouteFilter.getConfigParams();
  }

  public String getRoute(String api) {
    String url = "";
    for (RouteParams route : routerConfig) {
      if (FileSystems.getDefault().getPathMatcher("glob:" + route.getPath()).matches(Paths.get(api))) {
          url = route.getUrl();
          break;
      }
    }
    return url;
  }

  public static void main(String[] args) {
    List<Integer> ints = new ArrayList<>();
    ints.add(1);
    ints.add(10);
    ints.add(20);
    ints.add(2);
    ints.add(3);
    ints.add(30);
    ints.add(5);
    ints.add(4);
    ints.add(6);
    ints.add(7);

    System.out.println("List of ints " + ints.toString());
    AtomicInteger intval = new AtomicInteger(4);

    int returningConn = 3;
    boolean connIntact = false;
    while(!connIntact) {
      int previousBits = intval.get();
      int newBits = previousBits | (1 << returningConn);
      connIntact = intval.compareAndSet(previousBits, newBits);
    }
  }

}