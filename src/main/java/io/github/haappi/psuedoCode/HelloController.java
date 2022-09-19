package io.github.haappi.psuedoCode;

import static io.github.haappi.psuedoCode.Common.*;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.swing.*;

public class HelloController {
  @FXML protected MenuButton selector;
  @FXML protected TextField inputThing;
  @FXML protected Text output;

  /**
   * This function is totally useless. Go away<br>
   *
   * <p>1. <span color="red">Print x multiples of y</span><br>
   * 2. <span color="red">Print substrings of a string</span><br>
   * 3. <span color="red">Roll x die y times and print out the max, min, and average</span><br>
   * 4. <span color="red">Expand a number</span><br>
   * 5. <span color="red">Calculate if a number is Prime</span><br>
   * 6. <span color="red">Print x # of primes</span><br>
   * 7. <span color="red">Prime factoriztion of a number</span><br>
   * 8. <span color="red">>Reduce a #</span><br>
   * 9. <span color="red">Fibonacci</span><br>
   * 10. <span color="red">>Pairs of factors</span><br>
   * 11. <span color="red">>calculate the factorial of a number</span><br>
   * 12. <span color="red">>list of strong numbers</span><br>
   * 13. <span color="red">>list of armstrong numbers</span><br>
   * 14. <span color="red">>list of narcissistic numbers (same as above)</span><br>
   * 15. <span color="red">Heron's method for calcualating a square root</span><br>
   * 16. <span color="red">Caculate PI using an ininfite series</span><br>
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
    this.output.setText(String.valueOf(factorial(inputDialog)));
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
    this.output.setText(generateFibonacci(inputDialog));
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
    this.output.setText(generateMultiples(instances, y));
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
    this.output.setText(expandANumber(input));
  }

  private String expandANumber(int number) {
    String numberString = String.valueOf(number);
    String newString = "";
    for (int i = 0; i < numberString.length(); i++) {
      newString += numberString.charAt(i) + getZeros(numberString.length() - i - 1) + ",";
    }
    return formatStringWithSeparator(newString, " + ", ",");
  }

  private String getZeros(int count) {
    String zeros = "";
    for (int i = 0; i < count; i++) {
      zeros += "0";
    }
    return zeros;
  }

  @FXML
  protected void getPi() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("How many decimal places do you want? "), Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    this.output.setText(String.valueOf(calculatePi(input)));
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
    if (input > 15) {
      JOptionPane.showMessageDialog(null, "Too many primes");
      return;
    }
    this.output.setText(getFirstXPrimes(input));
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
    this.output.setText(String.valueOf(primes));
    return arrayToString(primes);
  }

  @FXML
  protected void subStringer() {
    String input = JOptionPane.showInputDialog("What string do you want to sub string? ");
    this.output.setText(printSubStringsOf(input));
  }

  private String printSubStringsOf(String string) {
    String newString = "";
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
    this.output.setText(rollADice(numberOfDie, numberOfSides, numberOfTimes));
  }

  @FXML
  protected void heronSquare() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("What is the number you want to find the sqrt of? "),
            Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }

    this.output.setText(String.valueOf(getSquareRootOf(input)));
  }

  private double getSquareRootOf(double guess) {
    double x = 1D;
    do {
      x = round((x + (guess / x)) / 2, 5);
    } while (x != round((x + (guess / x)) / 2, 5));
    return x;
  }

  @FXML
  protected void inputSlapped(ActionEvent actionEvent) {}

  @FXML
  protected void primeNumberThing() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("What number do you want to see is prime or not? "),
            Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    this.output.setText(String.valueOf(isPrime(input)));
  }

  @FXML
  protected void primeFactorANumber() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("What number do you want to prime factor? "),
            Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    this.output.setText(primeFactorization(input));
  }

  @FXML
  protected void simplfyFraction() {
    Integer numerator =
        parseJOptionInput(
            JOptionPane.showInputDialog("What is the numerator of the fraction? "), Integer.class);
    Integer denominator =
        parseJOptionInput(
            JOptionPane.showInputDialog("What is the denominator of the fraction? "),
            Integer.class);
    if (numerator == null || denominator == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    this.output.setText(reduceFraction(numerator, denominator));
  }

  @FXML
  protected void factorPairing() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("What number do you to get factor pairs for? "),
            Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    this.output.setText(getFactorPairsFor(input));
  }

  @FXML
  protected void getStrongNumbers() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("Up-to what number do you want? "), Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }

    String output = "";
    for (int i = 0; i < input; i++) {
      if (isStrongNumber(i)) {
        output += i + ", ";
      }
    }
    this.output.setText(output);
  }

  @FXML
  protected void getArmstrongNumbers() {
    Integer input =
        parseJOptionInput(
            JOptionPane.showInputDialog("How many armstrong numbers do you want? "), Integer.class);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    if (input > 15) {
      JOptionPane.showMessageDialog(null, "I'm not here to burn my computer.");
      return;
    }
    String output = "";
    for (int i = 0; i < 10000000; i++) {
      if (isArmstrongNumber(i)) {
        output += i + ", ";
        if (output.split(", ").length >= input) {
          break;
        }
      }
    }

    this.output.setText(output);
  }
}
