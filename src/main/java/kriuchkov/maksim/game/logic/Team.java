package kriuchkov.maksim.game.logic;

import kriuchkov.maksim.game.logic.hero.Doctor;
import kriuchkov.maksim.game.logic.hero.Hero;
import kriuchkov.maksim.game.logic.util.RNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {

    private Team enemyTeam;

    private RNG rng = RNG.getInstance();

    private List<Hero> heroes = new ArrayList<>();
    private List<Hero> aliveHeroes = new ArrayList<>();
    private List<Hero> readyHeroes = new ArrayList<>();

    public Team(Hero... heroes) {
        this.heroes.addAll(Arrays.asList(heroes));
        this.aliveHeroes.addAll(this.heroes);
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setEnemyTeam(Team enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    public void randomAct() {
        Hero actor = this.pickRandomReady();
        if (actor instanceof Doctor && !this.onlyDoctorsAlive()) {
            actor.heal(this.pickRandomAlive());
        } else {
            actor.hit(enemyTeam.pickRandomAlive());
            enemyTeam.renewAliveList();
        }
        readyHeroes.remove(actor);
    }

    private boolean onlyDoctorsAlive() {
        boolean result = true;
        for (Hero hero : aliveHeroes) {
            if (!(hero instanceof Doctor)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private Hero pickRandomAlive() {
        return aliveHeroes.get(rng.roll(0, this.aliveHeroes.size() - 1));
    }

    private Hero pickRandomReady() {
        return readyHeroes.get(rng.roll(0, this.readyHeroes.size() - 1));
    }

    public void setAllAliveAsReady() {
        this.readyHeroes.clear();
        this.readyHeroes.addAll(aliveHeroes);
    }

    public void renewAliveList() {
        aliveHeroes.removeIf(hero -> ! hero.isAlive());
    }

    public boolean hasAliveHeroes() {
        return aliveHeroes.size() > 0;
    }

    public boolean hasReadyHeroes() {
        return readyHeroes.size() > 0;
    }
}
