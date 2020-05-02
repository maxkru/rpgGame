package kriuchkov.maksim.game.logic;

import kriuchkov.maksim.game.BattleObserver;
import kriuchkov.maksim.game.logic.hero.Hero;
import kriuchkov.maksim.game.logic.util.RNG;

public class Battle {

    private static final int NUMBER_OF_ROUNDS = 30;

    private final RNG rng = RNG.getInstance();

    private BattleObserver observer;
    private Team team1;
    private Team team2;

    private Object mon = new Object();

    public Battle(BattleObserver observer, Team team1, Team team2) {
        this.observer = observer;
        this.team1 = team1;
        this.team2 = team2;

        for (Hero hero : team1.getHeroes())
            hero.setObserver(observer);
        for (Hero hero : team2.getHeroes())
            hero.setObserver(observer);
    }

    public void battle() {
        team1.setEnemyTeam(team2);
        team2.setEnemyTeam(team1);

        for (int j = 0; j < NUMBER_OF_ROUNDS; j++) {
            team1.setAllAliveAsReady();
            team2.setAllAliveAsReady();
            observer.outputMessage("---------------\n");
            observer.outputMessage("Раунд " + (j + 1) + "\n");
            while (team1.hasReadyHeroes() || team2.hasReadyHeroes()) {
                if (rng.roll(0, 1) == 0) {
                    team1.randomAct();
                    observer.updateTeams();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                    team2.randomAct();
                    observer.updateTeams();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                } else {
                    team2.randomAct();
                    observer.updateTeams();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                    team1.randomAct();
                    observer.updateTeams();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                }
            }
            if (!team1.hasAliveHeroes()) {
                observer.outputMessage("Команда 1 уничтожена!");
                break;
            }
            if (!team2.hasAliveHeroes()) {
                observer.outputMessage("Команда 2 уничтожена!");
                break;
            }
            observer.updateTeams();
        }

//        printTeams(team1, team2);
    }


}