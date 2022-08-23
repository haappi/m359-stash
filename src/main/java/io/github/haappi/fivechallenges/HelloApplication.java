package io.github.haappi.fivechallenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    System.out.println("""
            Select an option:
            1. Breeding of bugs.
            2. Area of a circle.
            3. Salary simulation.
            4. Shop simulator.
            5. Calculate roots of quadratic equation.
            6. Exit.
            """);
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    String name = reader.readLine();
    switch (name) {
        case "1":
            Bugs.run(stage);
            break;
        case "2":
            AreaOfACircle.run(stage);
            break;
        case "3":
            System.out.println("Enter starting salary: ");
            SalarySimulation salarySim = new SalarySimulation(reader.readLine());
            salarySim.run(stage); // for some reason this contractor is not working
            // https://stackoverflow.com/questions/30814258/javafx-pass-parameters-while-instantiating-controller-class
            // most likely something that has to do with that.
            break;
        case "4":
            ShopSimulation.run(stage);
            break;
        case "5":
            CalculateRootsOfQuadraticEquation.run(stage);
            break;
        case "6":
            System.exit(0);
            break;
        default:
            System.out.println("Invalid option");
            break;
    }
  }

  public static void main(String[] args) {
    launch();
  }
}
