package kriuchkov.maksim.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    public ListView<String> outputListView;
    public ObservableList<Hero> outputList = FXCollections.observableArrayList(new Warrior(250, "Тигрил", 11, 0),
            new Assassin(150, "Акали", 14, 0, 0.5f),
            new Doctor(120, "Жанна", 4, 8));

    @FXML
    private void exit() throws IOException {
//        System.exit(0);
        team1ListView.setItems(outputList);
    }

    public void updateTeamListViews(Team team1, Team team2) {
        team1ListView.setItems(FXCollections.observableList(team1.getHeroes()));
        team2ListView.setItems(FXCollections.observableList(team2.getHeroes()));
    }
}
