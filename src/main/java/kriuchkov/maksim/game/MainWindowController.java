package kriuchkov.maksim.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import kriuchkov.maksim.game.logic.Team;
import kriuchkov.maksim.game.logic.hero.Assassin;
import kriuchkov.maksim.game.logic.hero.Doctor;
import kriuchkov.maksim.game.logic.hero.Hero;
import kriuchkov.maksim.game.logic.hero.Warrior;

import java.io.IOException;

public class MainWindowController {

    public Button startButton;
    public Button updateButton;
    public ListView<Hero> team1ListView;
    public ListView<Hero> team2ListView;
    public TextArea outputTextArea;

    private BattleObserver observer;

    private StringBuilder out;
    private Team team1;
    private Team team2;

    @FXML
    private void exit() throws IOException {

    }

    public void setTeams(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    private void updateTeamListViews() {
        team1ListView.setItems(FXCollections.observableList(team1.getHeroes()));
        team2ListView.setItems(FXCollections.observableList(team2.getHeroes()));
    }

    public void addTextToOutput(String text) {
        out.append(text);
    }

    @FXML
    private void startBattle() {
        outputTextArea.clear();
        observer.startNewBattle();
    }

    public void init() {
        observer = new BattleObserver(this);
        out = new StringBuilder();
    }

    @FXML
    private void updateText() {
        outputTextArea.setText(out.toString());
        out.setLength(0);
        updateTeamListViews();
    }
}
