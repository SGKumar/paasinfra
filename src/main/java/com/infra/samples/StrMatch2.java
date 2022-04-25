package com.atlassian.old.samples;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.tuple.Pair;
import java.util.List;

public class StrMatch2 {

  private static String containsMatch(String sFull, String sPattern) {
    // create map to count pattern chars
    // create map to init pattern chars with sFull
    // iterate over pattern 
    // count pattern chars with sFull
    // if count >= pattern we have a match

    HashMap<Character, Integer> mapPattern = initPatternCounts(sPattern);
    HashMap<Character, Integer> mapBase = initBaseChars(sFull);
    int left = sFull.length(), right = sFull.length();
    int resLeft = 0, resRight = 0;

    for(; left >= 0; left--) {
      if(mapBase.containsKey(sFull.charAt(resLeft))) {
        mapPattern.put(sFull.charAt(resLeft), mapPattern.get(sFull.charAt(resLeft))+1);
      }
      if(containsMatch(mapBase, mapPattern)) {
        Pair<Integer, Integer> iSpan = smallestSpan(mapBase, mapPattern, left, right, sPattern.length());
      }
    }

    return "-1";
  }

  private static Pair<Integer, Integer> smallestSpan(HashMap<Character, Integer> mapBase,
      HashMap<Character, Integer> mapPattern, int left, int right, int lenPattern) {
    while(left + lenPattern >= right) {
      
    }
    return null;
  }

  private static boolean containsMatch(HashMap<Character, Integer> mapBase, HashMap<Character, Integer> mapPattern) {
    for(Character c : mapBase.keySet()) {
      if(!mapBase.get(c).equals(mapPattern.get(c))) {
        return false;
      }
    }
    return true;
  }

  private static HashMap<Character, Integer> initBaseChars(String sBase) {
    HashMap<Character, Integer> mapBaseChars = new HashMap<>();
    for(Character c : sBase.toCharArray()) {
      mapBaseChars.putIfAbsent(c, 0);
    }
    return mapBaseChars;
  }

  private static HashMap<Character, Integer> initPatternCounts(String sPattern) {
    HashMap<Character, Integer> mapPattern = new HashMap<>();
    for(Character c : sPattern.toCharArray()) {
      mapPattern.putIfAbsent(c, 0);
      Integer count = mapPattern.get(c);
      count++;
    }
    return mapPattern;
  }

  public static void main(String[] args) {
    //System.out.println(containsMatch("zoaoaloaopa", "ola"));


    List<Pair<Integer, Integer>> arrayOfInts = new ArrayList<>();
    arrayOfInts.add(Pair.of(0, 1));
    arrayOfInts.add(Pair.of(1, 1));
    arrayOfInts.add(Pair.of(3, 2));
    arrayOfInts.add(Pair.of(2, 3));
    arrayOfInts.add(Pair.of(4, 4));
    arrayOfInts.add(Pair.of(5, 5));

    System.out.println("Pair.of(2,3) is" + (arrayOfInts.contains(Pair.of(2,3))?" ":" NOT ") + "present");
    System.out.println("Pair.of(2,4) is" + (arrayOfInts.contains(Pair.of(2,4))?" ":" NOT ") + "present");
    System.out.println("Pair.of(5,4) is" + (arrayOfInts.contains(Pair.of(5,4))?" ":" NOT ") + "present");
    System.out.println("Pair.of(5,5) is" + (arrayOfInts.contains(Pair.of(5,5))?" ":" NOT ") + "present");

    //arrayOfInts.add(index, element);
    Pair<Integer, Integer> head = Pair.of(2,5);
    if(head.getLeft() < 0){}

  }
  

}
