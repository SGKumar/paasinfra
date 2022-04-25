package com.atlassian.old.samples;

import java.util.*;
public class TreeMapDemo {

   public static void main(String args[]) {
      // Create a hash map
      TreeMap<String, Double> tm = new TreeMap<>();
      
      // Put elements to the map
      tm.put("Zoe", new Double(3434.34));
      tm.put("Minnie", new Double(123.22));
      tm.put("Ayan", new Double(1378.00));
      tm.put("Daisy", new Double(99.22));
      tm.put("Pete", new Double(-19.08));
      
      // Get a set of the entries
      //Set<Map.Entry<String, Double>> set = tm.entrySet();
      
      // Get an iterator
      /*Iterator<Map.Entry<String, Double>> i = set.iterator();
      
      // Display elements
      while(i.hasNext()) {
         Map.Entry<String, Double> me = (Map.Entry<String, Double>)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
      }
      System.out.println();*/
      for(String name : tm.keySet()) {
         System.out.println(name + " : " + tm.get(name));
      }
      for(Map.Entry<String, Double> entry : tm.entrySet()) {
         System.out.println(entry.getKey() + " : " + entry.getValue());
      }
      
      // Deposit 1000 into Zoe's account
      double balance = ((Double)tm.get("Zoe")).doubleValue();
      tm.put("Zoe", new Double(balance + 1000));
      System.out.println("Zoe's new balance: " + tm.get("Zoe"));
   }
}