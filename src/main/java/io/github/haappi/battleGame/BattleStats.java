package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.text.Text;

public class BattleStats {
    public Text wins;
    public Text dmgDealt;
    public Text dmgTaken;
    public LineChart<Number, Number> lineChart;

    public void mainMenu(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("main-menu");
    }

    @FXML
    public void initialize() {
        int _wins = 0;
        int _losses = 0;
        int _dmgDealt = 0;
        int _dmgTaken = 0;
        for (BattleData battleData : HelloApplication.getInstance().getBattleData()) {
            if (battleData.isDidPlayerWin()) {
                _wins++;
            } else {
                _losses++;
            }
            _dmgDealt += battleData.getPlayerDamageDealt();
            _dmgTaken += battleData.getPlayerDamageTaken();
        }
        wins.setText("Wins: " + _wins + " Losses: " + _losses);
        dmgDealt.setText("Damage dealt: " + _dmgDealt);
        dmgTaken.setText("Damage taken: " + _dmgTaken);


//        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
//        series1.setName("Player");
//        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
//        series2.setName("Not Player");
//        for (BattleData battleData : HelloApplication.getInstance().getBattleData()) {
//            series1.getData().add(new XYChart.Data<>(battleData.getEnemyDamageTaken(), battleData.getPlayerDamageTaken()));
//            series2.getData().add(new XYChart.Data<>(battleData.getEnemyDamageTaken(), battleData.getEnemyDamageTaken()));
//            series.getData().add(new XYChart.Data<>("Player Dealt Taken Over Time", battleData.getPlayerDamageDealt()));
//        }
//        lineChart.getData().add(series1);
//        lineChart.getData().add(series2);
    }

}
