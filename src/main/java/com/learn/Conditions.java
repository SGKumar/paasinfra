package com.stripe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Conditions {
// String charge = "["CHARGE:card_country=US&currency=USD&amount=250&ip_country=CA","ALLOW:amount>500", ]"

  public static boolean check(String s) {
    String charge = "[CHARGE:card_country=US&currency=USD&amount=250&ip_country=CA,ALLOW:amount>500 AND ip_country=US]";
    // split the string by ,
    String[] parts = charge.split(":|,|\\]|\\[");
    Map<String, Object> attrMap = setupTxn(parts[2]);

    String allowBlock = parts[3];
    boolean isAnd = parts[4].contains("AND");
    String[] preds = parts[4].split("AND|OR");
    String pred = preds[0];
    for(String part : preds) {
      System.out.println("part " + part);
    }

    Map<String, Condition> attrPred = setupRule(pred);
    boolean retVal = isAnd?true:false;
    for(Map.Entry<String, Condition> entry : attrPred.entrySet()) {
      String attribute = entry.getKey();
      Object attrValue = attrMap.get(attribute);
      boolean retApply = false;
      if(attrValue instanceof String) {
        retApply = entry.getValue().apply((String)attrValue);
      }
      if(attrValue instanceof Double) {
        retApply = entry.getValue().apply((Double)attrValue);
      }
      retVal = isAnd?(retVal && retApply):(retVal || retApply);
    }

    return "ALLOW".equals(allowBlock)?retVal:!retVal;
  }

  private static Map<String, Condition> setupRule(String pred) {
    String[] predParts = pred.split("AND|OR");

    HashMap<String, Condition> predMap = new HashMap<String, Condition>();
    for(String predPart : predParts) {
      String[] keyVal = predPart.split("[<,>,<=,>=,==]");
      Comparison compareOp = getComparison(predPart, keyVal[0].length(), keyVal[1].length()); 
      predMap.put(keyVal[0], new Condition(compareOp, (Object)keyVal[1]));
    }
    System.out.println(predMap);
    return predMap;
  }

  private static Comparison getComparison(String predPart, int left, int right) {
    System.out.printf("predPart %s left %d right %d\n", predPart, left, right);
    String compare = predPart.substring(left, predPart.length()-right);

    return Comparison.get(compare);
  }

  private static Map<String, Object> setupTxn(String txn) {
    /*
    String[] attribs = txn.split("&");

    HashMap<String, Object> attrMap = new HashMap<String, Object>();
    for(String attrib : attribs) {
      String[] keyVal = attrib.split("=");
      attrMap.put(keyVal[0], keyVal[1]);
    }
    System.out.println(attrMap);
    return attrMap;
    */
    return Arrays.stream(txn.split("&"))
      .map(s ->s.split("="))
      .collect(Collectors.toMap(
        a -> a[0],
        a -> a[1]
      ));
  }

  public static void main(String[] params) {
    System.out.println(check(""));
  }
  
}
