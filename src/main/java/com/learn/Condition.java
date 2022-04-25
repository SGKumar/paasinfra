package com.stripe;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Condition {
  Comparison comparison;
  String stringValue;
  Double doubleValue;

  public Condition(Comparison condition, Object value) {
    this.comparison = condition;
    if(value instanceof String) {
      this.stringValue = (String)value;
    }
    else if(value instanceof Double) {
      this.doubleValue = (Double)value;
    }
  } 

  public boolean apply(String value) {
    return this.stringValue.equals(value);
  }

  public boolean apply(Double value) {
    boolean compareWithValue = false;
    switch(comparison) {
      case EQ:
        compareWithValue = (this.doubleValue == value);
        break;
      case GT:
        compareWithValue = (this.doubleValue > value);
      break;
      case GTE:
        compareWithValue = (this.doubleValue >= value);
      break;
      case LT:
        compareWithValue = (this.doubleValue < value);
      break;
      case LTE:
        compareWithValue = (this.doubleValue <= value);
      break;
      default:
        break;

    }
    return compareWithValue;
  }
}

