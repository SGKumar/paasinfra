package com.infra.design.LB;

import java.util.stream.IntStream;

import com.infra.design.LB.LoadBalancer;
import com.infra.design.LB.RandomLoadBalancer;
import com.infra.design.LB.RoundRobinLoadBalancer;

//import org.junit.Before;
import org.junit.Test;

//import static org.junit.Assert.assertTrue;

public class LoadBalancerTest {

    @Test
    public void RoundRobinLoadBalancer() {
        LoadBalancer loadBalancer = new RoundRobinLoadBalancer();
        callLoadBalancer(loadBalancer);
    }

    @Test
    public void RandomLoadBalancer() {
        LoadBalancer loadBalancer = new RandomLoadBalancer();
        callLoadBalancer(loadBalancer);
    }

    private void callLoadBalancer(LoadBalancer loadBalancer) {
        /*
        for(int i = 0; i < 10; i++) {
            String serverName = loadBalancer.getServerInstance("atlassian.confluenceService");
            System.out.printf("%s: %s\n", loadBalancer.getClass().getSimpleName(), serverName);
        }
        */
        IntStream.range(0, 10)
        .parallel()   
        .forEach(i -> {
            String serverName = loadBalancer.getServerInstance("atlassian.confluenceService");
            System.out.printf("i: %d; thread: %s %s: %s\n", i, Thread.currentThread().getName(), loadBalancer.getClass().getSimpleName(), serverName);
            //System.out.printf("%s: %s\n", loadBalancer.getClass().getSimpleName(), serverName);
        });    
  
    }
}
