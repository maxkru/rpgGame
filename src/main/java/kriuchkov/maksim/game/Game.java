package kriuchkov.maksim.game;

import kriuchkov.maksim.game.util.RNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Game {

    private static final int NUMBER_OF_ROUNDS = 30;

    private static RNG rng = RNG.getInstance();

    public static void main(String[] args) {

        Hero[] team1 = new Hero[] {
                new Warrior(250, "Тигрил", 11, 0),
                new Assassin(150, "Акали", 14, 0, 0.5f),
                new Doctor(120, "Жанна", 4, 8)
        };


        Hero[] team2 = new Hero[] {
                new Warrior(290, "Минотавр", 10, 0),
                new Assassin(160, "Джинкс", 14, 0, 0.4f),
                new Doctor(110, "Зои", 4, 9)
        };

        List<Hero> aliveTeam1 = new ArrayList<>(Arrays.asList(team1));
        List<Hero> aliveTeam2 = new ArrayList<>(Arrays.asList(team2));

        printTeams(team1, team2);

        for (int j = 0; j < NUMBER_OF_ROUNDS; j++) {
            System.out.println("---------------");
            System.out.println("Раунд " + (j + 1));
            for (int i = 0; i < aliveTeam1.size() || i < aliveTeam2.size() ; i++) {
                if(rng.roll(0, 2) == 0) {
                    if (aliveTeam1.size() > i)
                        act(aliveTeam1.get(i), aliveTeam1, aliveTeam2);
                    if (aliveTeam2.size() > i)
                        act(aliveTeam2.get(i), aliveTeam2, aliveTeam1);
                } else {
                    if (aliveTeam2.size() > i)
                        act(aliveTeam2.get(i), aliveTeam2, aliveTeam1);
                    if (aliveTeam1.size() > i)
                        act(aliveTeam1.get(i), aliveTeam1, aliveTeam2);
                }
            }
            aliveTeam1.removeIf(hero -> !hero.isAlive());
            aliveTeam2.removeIf(hero -> !hero.isAlive());
            if (aliveTeam1.size() == 0) {
                System.out.println("Команда 1 уничтожена!");
                break;
            }
            if (aliveTeam2.size() == 0) {
                System.out.println("Команда 2 уничтожена!");
                break;
            }
        }

        printTeams(team1, team2);
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

    private static void act(Hero actor, List<Hero> allies, List<Hero> enemies) {
        if (actor instanceof Doctor && !teamIsOnlyDoctors(allies)) {
            actor.heal(allies.get(rng.roll(0, allies.size() - 1)));
        } else {
            actor.hit(enemies.get(rng.roll(0, enemies.size() - 1)));
        }
    }

    private static boolean teamIsOnlyDoctors(List<Hero> team) {
        boolean result = true;
        for (Hero hero : team) {
            if (!(hero instanceof Doctor)) {
                result = false;
                break;
            }
        }
        return result;
    }
}