package mochegov.game.enums;

import java.util.Random;

public enum Sign {
    ROCK, PAPER, SCISSORS;

    public final static Random RANDOM = new Random();

    public static Sign getRandom() {
        int randomIndex = RANDOM.nextInt(Sign.values().length);
        return Sign.values()[randomIndex];
    }
}
