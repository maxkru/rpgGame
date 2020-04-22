package kriuchkov.maksim.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import kriuchkov.maksim.game.logic.Team;
import kriuchkov.maksim.game.logic.hero.Assassin;
import kriuchkov.maksim.game.logic.hero.Doctor;
import kriuchkov.maksim.game.logic.hero.Hero;
import kriuchkov.maksim.game.logic.hero.Warrior;

import java.io.IOException;

public class MainWindowController {

    public Button secondaryButton;
    public ListView<Hero> team1ListView;
    public ListView<Hero> team2ListView;
    public TextArea outputTextArea;

    private BattleObserver observer;

    @FXML
    private void exit() throws IOException {

    }

    public void updateTeamListViews(Team team1, Team team2) {
        team1ListView.setItems(FXCollections.observableList(team1.getHeroes()));
        team2ListView.setItems(FXCollections.observableList(team2.getHeroes()));
    }

    public void addTextToOutput(String text) {
        outputTextArea.appendText(text);
    }

    @FXML
    private void startBattle() {
        observer.startNewBattle();
    }

    public void init() {
        observer = new BattleObserver(this);
    }
}
