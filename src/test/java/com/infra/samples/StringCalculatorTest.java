package com.infra.samples;

import org.junit.Test;

import com.infra.samples.StringCalculator;

import org.junit.Assert;

public class StringCalculatorTest {
  @Test
  public final void Return0ForEmptyString() {
    int sum = StringCalculator.add("");
    Assert.assertEquals(0, sum);
  }

  @Test
  public final void ReturnSameNumberForJust1Number() {
    int sum = StringCalculator.add("5");
    Assert.assertEquals(5, sum);
  }

  /*
  @Test(expected = RuntimeException.class)
  public final void ThrowExceptionForMoreThan2Numbers() {
    StringCalculator.add("1,2,3");
  }

  */

  @Test(expected = RuntimeException.class)
  public final void ThrowExceptionForNonNumber() {
    StringCalculator.add("1,x");
  }

  @Test
  public final void NoExceptionFor2Numbers() {
    int sum = StringCalculator.add("1,2");
    Assert.assertEquals(3, sum);
  }

  @Test
  public final void Sum2NewLineSeparatedNumbers() {
    int sum = StringCalculator.add("1\n2");
    Assert.assertEquals(3, sum);
  }

  @Test
  public final void SumNumbersSeparatedByCommaNewLine() {
    int sum = StringCalculator.add("1\n2,3");
    Assert.assertEquals(6, sum);
  }

  @Test
  public final void ReturnSumForAnyNumberOfNumbers() {
    int sum = StringCalculator.add("1,2,3,4,5");
    Assert.assertEquals(15, sum);
  }
}