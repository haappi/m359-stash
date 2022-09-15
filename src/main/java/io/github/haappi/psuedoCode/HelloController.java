package io.github.haappi.psuedoCode;

import static io.github.haappi.psuedoCode.Common.*;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javax.swing.*;

public class HelloController {

  private String generateFibonacci(int count) {
    count++;
    List<Integer> numbers = new ArrayList<>();
    numbers.add(0);
    numbers.add(1);
    for (int i = 2; i < count; i++) {
      numbers.add(numbers.get(i - 2) + numbers.get(i - 1)); // add the last two numbers together
    }
    return arrayToString(numbers);
  }

  @FXML
  protected void doFibonacci() {
    Integer inputDialog =
        parseJOptionInput(JOptionPane.showInputDialog("How many things "), Integer.class);
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
}
