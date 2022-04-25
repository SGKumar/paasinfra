package com.atlassian.design.router;

import java.util.function.Predicate;

public class RouterPredicateFactory {

  public static void main(String[] args) {
      /*
      List<Person> people = Arrays.asList(new Person(20, Gender.MALE),
              new Person(45, Gender.FEMALE), new Person(50, Gender.MALE),
              new Person(65, Gender.MALE));

      people.stream()
              .filter(personIsBetweenAges(16, 25))
              .forEach(person -> {
                  System.out.println(person.getAge() + ", " + person.getGender());
              });*/
  }

  /*
  private static Predicate<Person> personIsMale() {
      return person -> person.getGender() == Gender.MALE;
  }

  private static Predicate<Person> personIsBetweenAges(int lower, int upper) {
      return personIsAtLeast(lower).and(personIsYoungerThan(upper));
  }

  private static Predicate<Person> personIsAtLeast(int age) {
      return person -> person.getAge() >= age;
  }

  private static Predicate<Person> personIsYoungerThan(int age) {
      return person -> person.getAge() < age;
  }
*/
  public static Predicate<String> isUserService() {
      Predicate<String> containsUser = s -> s.contains("user"); 
      return containsUser;
  }

  public static Predicate<String> isOrderService() {
      Predicate<String> containsOrder = s -> s.contains("order"); 
      return containsOrder;
  }

  public static Predicate<String> isPaymentService() {
      Predicate<String> containsPayment = s -> s.contains("payment"); 
      return containsPayment;
  }

}