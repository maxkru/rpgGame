package kriuchkov.maksim.game.logic.hero;

import kriuchkov.maksim.game.logic.util.RNG;

public class Assassin extends Hero {

    private static final float CRIT_FACTOR = 2f;
    private float criticalHitChance;
    private RNG rng = RNG.getInstance();

    public Assassin(int health, String name, int damage, int addHeal, float criticalHitChance) {
        super(health, name, damage, addHeal);
        this.criticalHitChance = criticalHitChance;
    }

    @Override
    public void heal(Hero target) {
        System.out.println("Убийцы не умеют лечить!");
    }

    @Override
    protected int calculateDamageForHit() {
        int damage = super.calculateDamageForHit();
        return rng.rollChance(criticalHitChance) ? (int) (damage * CRIT_FACTOR) : damage;
    }

    @Override
    public String toString() {
        if (alive)
            return String.format("%s: убийца, %d/%d hp, урон %d, шанс на крит %.0f%%\n", name, currentHealth, maxHealth, damage, criticalHitChance * 100f);
        else
            return String.format("%s: убийца, герой мертв, урон %d, шанс на крит %.0f%%\n", name, damage, criticalHitChance * 100f);
    }
}