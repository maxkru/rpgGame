package kriuchkov.maksim.game;

import kriuchkov.maksim.game.util.RNG;

class Doctor extends Hero {

    public Doctor(int health, String name, int damage, int addHeal) {
        super(health, name, damage, addHeal);
    }

    @Override
    void heal(Hero target) {
        if (!this.isAlive()) {
            System.out.println(name + ": мертвый герой лечить не может!");
            return;
        }

        int initHealth = target.currentHealth;
        target.takeHeal(RNG.getInstance().roll(addHeal * 3 / 4, addHeal * 5 / 4));
        int healthDiff = target.currentHealth - initHealth;
        if (healthDiff > 0)
            System.out.printf("%s восстанавливает %d здоровья герою %s\n", this.name, healthDiff, target.name);
        else
            System.out.printf("%s попытался вылечить героя %s. Не получилось.\n", this.name, target.name);
        target.infoShort();
    }

    @Override
    void infoFull() {
        if (alive)
            System.out.printf("%s: доктор, %d/%d hp, урон %d, лечение %d\n", name, currentHealth, maxHealth, damage, addHeal);
        else
            System.out.printf("%s: доктор, герой мертв, урон %d, лечение %d\n", name, damage, addHeal);
    }
}
