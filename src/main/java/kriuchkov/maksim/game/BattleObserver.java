package kriuchkov.maksim.game;

import kriuchkov.maksim.game.logic.Battle;
import kriuchkov.maksim.game.logic.Team;
import kriuchkov.maksim.game.logic.hero.Assassin;
import kriuchkov.maksim.game.logic.hero.Doctor;
import kriuchkov.maksim.game.logic.hero.Warrior;

public class BattleObserver {

    private MainWindowController controller;
    private Battle observedBattle;
    private Team team1;
    private Team team2;

    BattleObserver(MainWindowController controller) {
        this.controller = controller;
        team1 = new Team (
                new Warrior(250, "Тигрил", 11, 0),
                new Assassin(150, "Акали", 14, 0, 0.5f),
                new Doctor(120, "Жанна", 4, 8)
        );


        team2 = new Team (
                new Warrior(290, "Минотавр", 10, 0),
                new Assassin(160, "Джинкс", 14, 0, 0.4f),
                new Doctor(110, "Зои", 4, 9)
        );
    }

    public void startNewBattle() {
        observedBattle = new Battle(this, team1, team2);
        Thread thread = new Thread(observedBattle::battle);
        thread.start();
    }

    public void outputMessage(String msg) {
        controller.addTextToOutput(msg);
    }

    public void setTeam1(Team team) {
        team1 = team;
    }

    public void setTeam2(Team team) {
        team2 = team;
    }

    public void updateTeamViews() {
        controller.updateTeamListViews(team1, team2);
    }
}
