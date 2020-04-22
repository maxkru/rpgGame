package kriuchkov.maksim.game.logic.hero;

import kriuchkov.maksim.game.logic.util.RNG;

public class Doctor extends Hero {

    public Doctor(int health, String name, int damage, int addHeal) {
        super(health, name, damage, addHeal);
    }

    @Override
    public void heal(Hero target) {
        if (!this.isAlive()) {
            observer.outputMessage(name + ": мертвый герой лечить не может!");
            return;
        }

        int initHealth = target.currentHealth;
        target.takeHeal(RNG.getInstance().roll(addHeal * 3 / 4, addHeal * 5 / 4));
        int healthDiff = target.currentHealth - initHealth;
        if (healthDiff > 0)
            observer.outputMessage(String.format("%s восстанавливает %d здоровья герою %s\n", this.name, healthDiff, target.name));
        else
            observer.outputMessage(String.format("%s попытался вылечить героя %s. Не получилось.\n", this.name, target.name));
        target.infoShort();
    }

    @Override
    public String toString() {
        if (alive)
            return String.format("%s: доктор, %d/%d hp, урон %d, лечение %d\n", name, currentHealth, maxHealth, damage, addHeal);
        else
            return String.format("%s: доктор, герой мертв, урон %d, лечение %d\n", name, damage, addHeal);
    }
}
