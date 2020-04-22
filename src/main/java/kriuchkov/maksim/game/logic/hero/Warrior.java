package kriuchkov.maksim.game.logic.hero;

public class Warrior extends Hero {

    public Warrior(int health, String type, int damage, int addHeal) {
        super(health, type, damage, addHeal);
    }

    @Override
    public void heal(Hero target) {
        observer.outputMessage("Воины не умеют лечить!");
    }

    @Override
    public void infoFull() {
        if (alive)
            observer.outputMessage(String.format("%s: воин, %d/%d hp, урон %d\n", name, currentHealth, maxHealth, damage));
        else
            observer.outputMessage(String.format("%s: воин, герой мертв, урон %d\n", name, damage));
    }

    @Override
    public String toString() {
        if (alive)
            return String.format("%s: воин, %d/%d hp, урон %d\n", name, currentHealth, maxHealth, damage);
        else
            return String.format("%s: воин, герой мертв, урон %d\n", name, damage);
    }
}
