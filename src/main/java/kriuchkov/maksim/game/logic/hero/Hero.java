package kriuchkov.maksim.game.logic.hero;

import kriuchkov.maksim.game.BattleObserver;
import kriuchkov.maksim.game.logic.util.RNG;

public abstract class Hero {

    protected int maxHealth;
    protected int currentHealth;
    protected String name;
    protected int damage;
    protected int addHeal;
    protected boolean alive;

    protected BattleObserver observer;

    public Hero(int maxHealth, String name, int damage, int addHeal) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
        this.alive = true;
    }

    public void hit(Hero target) {
        if (!this.isAlive()) {
            observer.outputMessage(name + ": мертвый герой бить не может!\n");
            return;
        }
        if (target == this) {
            observer.outputMessage(name + " попробовал ударить себя. Не получилось.\n");
            return;
        }
        if (!target.isAlive()) {
            observer.outputMessage(String.format("%s попытался ударить мёртвого героя %s\n", this.name, target.name));
            return;
        }

        int damage = calculateDamageForHit();
        target.takeDamage(damage);
        observer.outputMessage(String.format("%s наносит %d урона герою %s%s\n", this.name, damage, target.name, target.isAlive() ? "" : " и убивает его!"));
        target.infoShort();
    }

    public abstract void heal(Hero target);

    public void takeDamage(int damage) {
        if(!alive) {
            observer.outputMessage("Герой уже мертвый!\n");
        } else {
            currentHealth -= damage;
            checkAlive();
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void takeHeal(int health) {
        if (alive) {
            currentHealth += health;
            if (currentHealth > maxHealth)
                currentHealth = maxHealth;
        }
    }

    public void infoShort() {
        if (alive)
            observer.outputMessage(String.format("%s: %d/%d hp\n", name, currentHealth, maxHealth));
        else
            observer.outputMessage(String.format("%s: герой мертв\n", name));
    }

    public void infoFull() {
        observer.outputMessage(this.toString());
    }

    @Override
    public String toString() {
        if (alive)
            return String.format("%s: %d/%d hp, урон %d\n", name, currentHealth, maxHealth, damage);
        else
            return String.format("%s: герой мертв, урон %d\n", name, damage);
    }

    private void checkAlive() {
        if (currentHealth <= 0) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    protected int calculateDamageForHit() {
        return RNG.getInstance().roll(damage * 3 / 4, damage * 5 / 4);
    }

    public void setObserver(BattleObserver observer) {
        this.observer = observer;
    }
}
