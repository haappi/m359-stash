package io.github.haappi.fivechallenges;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static io.github.haappi.fivechallenges.Common.loadFMXLView;

public class SalarySimulation {
  public SalarySimulation(double startingSalary) {
    this.baseSalary = startingSalary;
  }

  public SalarySimulation(String startingSalary) {
    try {
      this.baseSalary = Double.parseDouble(startingSalary);
    } catch (NumberFormatException e) {
      this.baseSalary = 13.99;
    }
    this.output = new Text("hiiiiu");
  }

  public SalarySimulation() {
    this.baseSalary = 13.99;
  }

  @FXML private TextField percentageIncrease;

  @FXML private Text output;

  @FXML private TextField hoursWorked;
  private double hoursWorkedThisWeek;
  private double weeklySalary;

  private double baseSalary;

  public void run(Stage stage) { // Only from the constructor
    loadFMXLView(stage, "salary-simulation.fxml");
    output.setText(getMessage("How many hours did you work this week?"));
  }

  private String getMessage(String message) {
    return "Salary: " + baseSalary + "\n" + message;
  }

  @FXML
  protected void onHourEnter() {
    if (hoursWorked.getText().equals("")) {
      output.setText(getMessage("How many hours did you work this week?"));
    } else {
      try {
        double hours = Double.parseDouble(hoursWorked.getText());
        this.hoursWorkedThisWeek = hours;
        calcuateWeeklySalary();
      } catch (NumberFormatException e) {
        output.setText("Please enter a valid number");
      }
    }
  }

  private void calcuateWeeklySalary() {
    // 1.5 * baseSalary if overtime
    if (hoursWorkedThisWeek < 40) {
      weeklySalary = baseSalary * hoursWorkedThisWeek;
    } else {
      weeklySalary = baseSalary * hoursWorkedThisWeek - 40;
      weeklySalary += 1.5 * baseSalary + (hoursWorkedThisWeek - 40) * (baseSalary / 2);
    }
    output.setText(getMessage("You made $" + weeklySalary + " this week"));
  }

  @FXML
  protected void onIncrease() {
    if (percentageIncrease.getText().equals("")) {
      output.setText(getMessage("How much do you want to increase your salary by?"));
    } else {
      try {
        double increase = Double.parseDouble(percentageIncrease.getText());
        baseSalary += baseSalary * increase / 100;
        output.setText(getMessage("Your new salary is $" + baseSalary));
      } catch (NumberFormatException e) {
        output.setText("Please enter a valid number");
      }
    }
  }
}
