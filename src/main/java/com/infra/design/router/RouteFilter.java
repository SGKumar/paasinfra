package com.atlassian.design.router;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class RouteFilter {
    private static List<RouteParams> pathRouteTable;

    static {
        pathRouteTable = new ArrayList<RouteParams>();
        pathRouteTable.add(new RouteParams("users", "/users/**", "http://api.atlassian.io/users"));
        pathRouteTable.add(new RouteParams("orders", "/orders/**", "http://api.atlassian.io/orders"));
        pathRouteTable.add(new RouteParams("payments", "/payments/**", "http://api.atlassian.io/payments"));
    }

    public String getRoute(String api) {
        String url = "";
        for(RouteParams route : pathRouteTable) {
            if(api.contains(route.getService())) {
                url = route.getUrl();
                break;
            }
        }
        return url;
    } 

}