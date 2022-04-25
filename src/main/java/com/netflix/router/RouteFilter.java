package com.netflix.router;

import java.util.ArrayList;
import java.util.List;

public class RouteFilter {
  private static List<RouteParams> routerConfig;

  static {
    routerConfig = new ArrayList<RouteParams>();
    routerConfig.add(new RouteParams("users", "/users/**", "http://api.atlassian.io/users"));
    routerConfig.add(new RouteParams("orders", "/orders/**", "http://api.atlassian.io/orders"));
    routerConfig.add(new RouteParams("payments", "/payments/**", "http://api.atlassian.io/payments"));
  }

  private RouteFilter() {

  }

  public static List<RouteParams> getConfigParams() {
    return routerConfig;
  }
}