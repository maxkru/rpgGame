package kriuchkov.maksim.game;

class Warrior extends Hero {

    public Warrior(int health, String type, int damage, int addHeal) {
        super(health, type, damage, addHeal);
    }

    @Override
    void heal(Hero target) {
        System.out.println("Воины не умеют лечить!");
    }

    @Override
    void infoFull() {
        if (alive)
            System.out.printf("%s: воин, %d/%d hp, урон %d\n", name, currentHealth, maxHealth, damage);
        else
            System.out.printf("%s: воин, герой мертв, урон %d\n", name, damage);
    }
}
