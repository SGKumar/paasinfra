package com.atlassian.old.samples;

import java.util.HashMap;

public class StringMatch {

  private static HashMap<Character, Integer> initPatternChars(String sPattern) {
    HashMap<Character, Integer> patternCounts = new HashMap<>();
    for(Character c : sPattern.toCharArray()) {
      patternCounts.putIfAbsent(c, 0);
      Integer count = patternCounts.get(c) + 1;
      patternCounts.put(c, count);
    }
    return patternCounts;
  }

  private static HashMap<Character, Integer> initMatchChars(String sPattern) {
    HashMap<Character, Integer> matchCounts = new HashMap<>();
    for(Character c : sPattern.toCharArray()) {
      matchCounts.put(c, 0);
    }
    return matchCounts;
  }

  private static boolean containsPattern(HashMap<Character, Integer> patternCounts, HashMap<Character, Integer> matchCounts) {
    boolean containsPattern = true;
    for(Character c : patternCounts.keySet()) {
      containsPattern &= (matchCounts.get(c) >= patternCounts.get(c));
    }
    return containsPattern;
  }

  /*
  * Return all the candidates in a list in decreasing order of their votes
  */
  public static String smallestWindow(String sBase, String sPattern) {
    HashMap<Character, Integer> patternCounts = initPatternChars(sPattern);
    HashMap<Character, Integer> matchCounts = initMatchChars(sPattern);
    
    int resLeft = -1, resRight = -1;  
    for(int leftPtr = sBase.length()-1, rightPtr = sBase.length()-1; leftPtr >= 0; leftPtr--) {

      Character currLeftChar = sBase.charAt(leftPtr);
      if(!patternCounts.containsKey(currLeftChar)) {
        continue;
      }

      matchCounts.put(currLeftChar, matchCounts.get(currLeftChar)+1);
      if(containsPattern(patternCounts, matchCounts)) {
        resLeft = leftPtr;
        resRight = rightPtr;
      }

      while(leftPtr <= rightPtr) {
        Character currRightChar = sBase.charAt(rightPtr);
        if(!patternCounts.containsKey(currRightChar)) {
          rightPtr--;
        }
        else if(matchCounts.get(currRightChar) > patternCounts.get(currRightChar)) {
          rightPtr--;
          matchCounts.put(currRightChar, matchCounts.get(currRightChar)-1);
        }
        else {
          break;
        }
        resRight = rightPtr;
      }
    }

    if(resRight == -1 || resLeft == -1) {
      return "-1";
    }
    else {
      return sBase.substring(resLeft, resRight+1);
    }
  }

  public static void main(String[] args) {
    System.out.println(smallestWindow("timetopractice", "toc"));
    System.out.println(smallestWindow("zoomlazapzo", "zo"));
    System.out.println(smallestWindow("zoomlazapzo", "to"));
    System.out.println(smallestWindow("", "toc"));
    System.out.println(smallestWindow("timetopractice", ""));
  }
}