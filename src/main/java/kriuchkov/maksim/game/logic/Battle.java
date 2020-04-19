package kriuchkov.maksim.game.logic;

import kriuchkov.maksim.game.logic.hero.Assassin;
import kriuchkov.maksim.game.logic.hero.Doctor;
import kriuchkov.maksim.game.logic.hero.Hero;
import kriuchkov.maksim.game.logic.hero.Warrior;
import kriuchkov.maksim.game.logic.util.RNG;

public class Battle {

    private static final int NUMBER_OF_ROUNDS = 30;

    private static RNG rng = RNG.getInstance();

    public static void main(String[] args) {

        Team team1 = new Team (
                new Warrior(250, "Тигрил", 11, 0),
                new Assassin(150, "Акали", 14, 0, 0.5f),
                new Doctor(120, "Жанна", 4, 8)
        );


        Team team2 = new Team (
                new Warrior(290, "Минотавр", 10, 0),
                new Assassin(160, "Джинкс", 14, 0, 0.4f),
                new Doctor(110, "Зои", 4, 9)
        );

        team1.setEnemyTeam(team2);
        team2.setEnemyTeam(team1);

        for (int j = 0; j < NUMBER_OF_ROUNDS; j++) {
//            System.out.println("---------------");
//            System.out.println("Раунд " + (j + 1));
            while (team1.hasReadyHeroes() || team2.hasReadyHeroes()) {
                if (rng.roll(0, 1) == 0) {
                    team1.randomAct();
                    team2.randomAct();
                } else {
                    team2.randomAct();
                    team1.randomAct();
                }
            }
            if (!team1.hasAliveHeroes()) {
//                System.out.println("Команда 1 уничтожена!");
                break;
            }
            if (!team2.hasAliveHeroes()) {
//                System.out.println("Команда 2 уничтожена!");
                break;
            }
            team1.setAllAliveAsReady();
            team2.setAllAliveAsReady();
        }

//        printTeams(team1, team2);
    }

    private static void printTeams(Hero[] team1, Hero[] team2) {
        System.out.println("---------------");
        System.out.println("Команда 1:");
        for (Hero t1: team1) {
            t1.infoFull();
        }
        System.out.println("Команда 2:");
        for (Hero t2: team2) {
            t2.infoFull();
        }
    }


}