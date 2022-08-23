module io.github.haappi.fivechallenges {
  requires javafx.controls;
  requires javafx.fxml;

  opens io.github.haappi.fivechallenges to
      javafx.fxml;

  exports io.github.haappi.fivechallenges;
}
