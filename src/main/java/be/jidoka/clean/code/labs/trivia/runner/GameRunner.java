package be.jidoka.clean.code.labs.trivia.runner;

import be.jidoka.clean.code.labs.trivia.uglytrivia.Game;

import java.util.Random;

public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        final Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        final Random rand = new Random();

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}
