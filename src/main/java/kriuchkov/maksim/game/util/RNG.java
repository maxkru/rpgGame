package kriuchkov.maksim.game.util;

import java.util.Random;

public class RNG {
    private Random random;
    private static RNG instance;

    static {
        instance = new RNG();
    }

    private RNG() {
        random = new Random();
    }

    public static RNG getInstance() {
        return instance;
    }

    public int roll(int min, int max) {
        if (min > max)
            throw new IllegalArgumentException();
        return min + random.nextInt(max - min + 1);
    }

    public boolean rollChance(float probability) {
        return (random.nextFloat() < probability);
    }
}
