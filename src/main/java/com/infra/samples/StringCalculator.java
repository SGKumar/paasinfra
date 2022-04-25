package com.atlassian.old.samples;

public class StringCalculator {
  String berry = "blue";
  
  public static final int add(final String sNumbers) {
    String delimiter = ",|\n";
    if(sNumbers.startsWith("//")) {
      //int delimiterPos = sNumbers.indexOf("//")+2;
      //delimiter = sNumbers.substring(delimiterPos, endIndex);
      //String sNumbersOnly = 
    }

    String[] numbers = sNumbers.split(delimiter);

    int sum = 0;
    for(String val : numbers) {
      if(!val.isEmpty()) {
        sum += Integer.parseInt(val);
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    //new StringCalculator().juicy("straw");
  }
}