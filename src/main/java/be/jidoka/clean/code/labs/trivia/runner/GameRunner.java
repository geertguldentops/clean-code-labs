package be.jidoka.clean.code.labs.trivia.runner;

import be.jidoka.clean.code.labs.trivia.uglytrivia.Game;

import java.util.Random;

public class GameRunner {

    private static boolean loser;

    public static void main(String[] args) {
        var aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        var rand = new Random();

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                aGame.wrongAnswer();
            } else {
                loser = aGame.wasCorrectlyAnswered();
            }
        } while (loser);
    }
}
