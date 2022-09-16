package io.github.haappi.psuedoCode;

import static io.github.haappi.psuedoCode.Common.*;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javax.swing.*;

public class HelloController {

  private final List<Integer> listOfPrimes;

  public HelloController() {
    listOfPrimes = List.of(2, 3, 5, 7, 11);
  }

  /**
   * This function is totally useless. Go away<br>
   *
   * <p>1. <span color="green">Print x multiples of y</span><br>
   * 2. <span color="green">Print substrings of a string</span><br>
   * 3. <span color="green">Roll x die y times and print out the max, min, and average</span><br>
   * 4. <span color="green">Expand a number</span><br>
   * 5. <span color="green">Calculate if a number is Prime</span><br>
   * 6. <span color="green">Print x # of primes</span><br>
   * 7. <span color="red">Prime factoriztion of a number</span><br>
   * 8. <span color="red">Reduce a #</span><br>
   * 9. <span color="green">Fibonacci</span><br>
   * 10. <span color="red">Pairs of factors</span><br>
   * 11. <span color="red">calculate the factorial of a number</span><br>
   * 12. <span color="red">list of strong numbers</span><br>
   * 13. <span color="red">list of armstrong numbers</span><br>
   * 14. <span color="red">list of narcissistic numbers (same as above)</span><br>
   * 15. <span color="green">Heron's method for calcualating a square root</span><br>
   * 16. <span color="green">Caculate PI using an ininfite series</span><br>
   */
  private void absolutelyUselessFunction() {
    throw new RuntimeException("bruh");
  }

  private String generateFibonacci(int count) {
    count++;
    List<Integer> numbers = new ArrayList<>();
    numbers.add(1); // todo just check this over
    numbers.add(1);
    for (int i = 2; i < count; i++) {
      numbers.add(numbers.get(i - 2) + numbers.get(i - 1)); // add the last two numbers together
    }
    return arrayToString(numbers);
  }

  @FXML
  protected void getFactorial() {
    Integer inputDialog =
        parseJOptionInput(
            JOptionPane.showInputDialog("What number do you want to find the factorial for? "),
            Integer.class);
    if (inputDialog == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    System.out.println(factorial(inputDialog));
  }

  @FXML
  protected void isNumberStrong() {
    Integer inputDialog =
        parseJOptionInput(
            JOptionPane.showInputDialog("What number do you want to find the factorial for? "),
            Integer.class);
    if (inputDialog == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    Long factorial = factorial(inputDialog);

    if (factorial == Long.parseLong(String.valueOf(inputDialog))) {
      System.out.println("is a facty boi");
    } else {
      System.out.println("you no is a facty bo"); // isnt a factorial in this case
    }
  }

  @FXML
  protected void doFibonacci() {
    Integer inputDialog =
        parseJOptionInput(
            JOptionPane.showInputDialog("How many numbers do you want to go til? "), Integer.class);
    if (inputDialog == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    System.out.println(generateFibonacci(inputDialog));
  }

  @FXML
  protected void doMultiples() {
    Integer instances =
        parseJOptionInput(JOptionPane.showInputDialog("How many instances? "), Integer.class);
    Integer y =
        parseJOptionInput(
            JOptionPane.showInputDialog("What # do you want to find the multiple for? "),
            Integer.class);

    if (instances == null || y == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    System.out.println(generateMultiples(instances, y));
  }

  private String generateMultiples(int count, int multiple) {
    count++;
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      numbers.add(i * multiple);
    }
    return arrayToString(numbers);
  }

  @FXML
  protected void expandANumberThing() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("What number do you want to expand? "), Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    System.out.println(expandANumber(input));
  }

  private String expandANumber(int number) {
    String numberString = String.valueOf(number);
    String newString = "";
    for (int i = 0; i < numberString.length(); i++) {
      newString =
          newString + numberString.charAt(i) + getZeros(numberString.length() - i - 1) + ", ";
    }
    return formatStringWithSeparator(
        newString, " + ", ", "); // fixme the last number is just yeeted out of existence
  }

  private String getZeros(int count) {
    String zeros = "";
    for (int i = 0; i < count; i++) {
      zeros += "0";
    }
    return zeros;
  }

  //  private String doPrimeFactorization(int numberToFactor) {
  //    boolean isNumberPrime = isPrime(numberToFactor);
  //    if (isNumberPrime) {
  //      return String.valueOf(numberToFactor);
  //    }
  //
  //
  //  }

  @FXML
  protected void getPi() {
    System.out.println(calcuatePi());
  }

  @FXML
  protected void getXPrimes() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("How many primes do you want? "), Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    System.out.println(getFirstXPrimes(input));
  }

  private String getFirstXPrimes(int count) {
    List<Integer> primes = new ArrayList<>();
    int i = 1;
    while (primes.size() < count) {
      if (isPrime(i)) {
        primes.add(i);
      }
      i++;
    }
    System.out.println(primes); // fixme this is also removing a number for some reason
    return arrayToString(primes);
  }

  @FXML
  protected void subStringer() {
    String input = JOptionPane.showInputDialog("What string do you want to sub string? ");
    System.out.println(printSubStringsOf(input));
  }

  private String printSubStringsOf(String string) {
    String newString = ""; // fixme last character not being added
    for (int i = 0; i < string.length(); i++) {
      for (int j = i + 1; j <= string.length(); j++) {
        newString += string.substring(i, j) + ", ";
      }
    }
    return formatStringWithSeparator(newString, ", ", ", ");
  }

  @FXML
  protected void diceRoller() {
    Integer numberOfDie =
        parseJOptionInput(
            JOptionPane.showInputDialog("How many dice do you want to roll? "), Integer.class);

    Integer numberOfTimes =
        parseJOptionInput(
            JOptionPane.showInputDialog("How many times do you want to roll? "), Integer.class);

    Integer numberOfSides =
        parseJOptionInput(
            JOptionPane.showInputDialog(
                "How many sides do you want the dice to have? Ignore for 6"),
            Integer.class);

    if (numberOfDie == null || numberOfTimes == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    if (numberOfSides == null) {
      numberOfSides = 6;
    }
    System.out.println(rollXDiceYTimes(numberOfDie, numberOfTimes, numberOfSides));
  }

  private String rollXDiceYTimes(int numberOfDice, int numberOfTimes, int diceSides) {
    int average = 0;
    int min = 0;
    int max = 0;

    for (int i = 0; i < numberOfTimes; i++) {
      int roll = rollNumberOfDice(numberOfDice, diceSides);
      average += roll;
      if (roll > max) {
        max = roll;
      }
      if (roll < min) {
        min = roll;
      }
    }
    average = average / numberOfTimes;
    return "Average: " + average + ", Min: " + min + ", Max: " + max;
  }

  private int rollNumberOfDice(int numberOfDice, int diceSides) {
    int total = 0;
    for (int i = 0; i < numberOfDice; i++) {
      total += rollDice(diceSides);
    }
    return total;
  }

  private int rollDice(int diceSides) {
    return (int) (Math.random() * diceSides) + 1;
  }

  @FXML
  protected void heronSquare() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("What is the nunber you want to find the sqrt of? "),
            Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }

    System.out.println(getSquareRootOf(input));
    System.out.println(Math.sqrt(input));
  }

  private double getSquareRootOf(double guess) {
    double x = 1D;
    do {
      x = round((x + (guess / x)) / 2, 5);
    } while (x != round((x + (guess / x)) / 2, 5));
    return x;
  }
}
