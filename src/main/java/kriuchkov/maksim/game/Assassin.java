package kriuchkov.maksim.game;

import kriuchkov.maksim.game.util.RNG;

class Assassin extends Hero {

    private static final float CRIT_FACTOR = 2f;
    private float criticalHitChance;
    private RNG rng = RNG.getInstance();

    public Assassin(int health, String name, int damage, int addHeal, float criticalHitChance) {
        super(health, name, damage, addHeal);
        this.criticalHitChance = criticalHitChance;
    }

    @Override
    void heal(Hero target) {
        System.out.println("Убийцы не умеют лечить!");
    }

    @Override
    protected int calculateDamageForHit() {
        int damage = super.calculateDamageForHit();
        return rng.rollChance(criticalHitChance) ? (int) (damage * CRIT_FACTOR) : damage;
    }

    @Override
    void infoFull() {
        if (alive)
            System.out.printf("%s: убийца, %d/%d hp, урон %d, шанс на крит %.0f%%\n", name, currentHealth, maxHealth, damage, criticalHitChance * 100f);
        else
            System.out.printf("%s: убийца, герой мертв, урон %d, шанс на крит %.0f%%\n", name, damage, criticalHitChance * 100f);
    }
}