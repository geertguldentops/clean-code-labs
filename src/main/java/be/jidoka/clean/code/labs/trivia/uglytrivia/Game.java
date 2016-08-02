package be.jidoka.clean.code.labs.trivia.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Game {

    private static final int LAST_BOARD_POSITION = 11;

    private final List<String> popQuestions = new LinkedList<>();
    private final List<String> scienceQuestions = new LinkedList<>();
    private final List<String> sportsQuestions = new LinkedList<>();
    private final List<String> rockQuestions = new LinkedList<>();

    private final List<String> players = new ArrayList<>();
    private final int[] places = new int[6];
    private final int[] purses = new int[6];
    private final boolean[] inPenaltyBox = new boolean[6];

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        IntStream.range(1, 51).forEach(i -> {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add("Science Question " + i);
            sportsQuestions.add("Sports Question " + i);
            rockQuestions.add("Rock Question " + i);
        });
    }

    // Seam for unit tests.
    /*default */List<String> getPopQuestions() {
        return popQuestions;
    }

    // Seam for unit tests.
    /*default */List<String> getScienceQuestions() {
        return scienceQuestions;
    }

    // Seam for unit tests.
    /*default */List<String> getSportsQuestions() {
        return sportsQuestions;
    }

    // Seam for unit tests.
    /*default */List<String> getRockQuestions() {
        return rockQuestions;
    }

    // Seam for unit tests.
    /*default */int[] getPlaces() {
        return places;
    }

    // Seam for unit tests.
    /*default */int[] getPurses() {
        return purses;
    }

    // Seam for unit tests.
    /*default */boolean[] getInPenaltyBox() {
        return inPenaltyBox;
    }

    // Seam for unit tests.
    /*default */List<String> getPlayers() {
        return players;
    }

    public void add(String playerName) {
        players.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    // Roll is randomly called with a value between 1 and 5.
    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

                movePlayerToNewPosition(roll);

                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");

                isGettingOutOfPenaltyBox = false;
            }

        } else {
            movePlayerToNewPosition(roll);

            askQuestion();
        }
    }

    private void movePlayerToNewPosition(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;

        if (places[currentPlayer] > LAST_BOARD_POSITION) {
            places[currentPlayer] = places[currentPlayer] - (LAST_BOARD_POSITION + 1);
        }

        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
    }

    private void askQuestion() {
        final String currentCategory = determineCurrentCategory();

        System.out.println("The category is " + currentCategory);

        switch (currentCategory) {
            case "Pop":
                System.out.println(popQuestions.remove(0));
                break;
            case "Science":
                System.out.println(scienceQuestions.remove(0));
                break;
            case "Sports":
                System.out.println(sportsQuestions.remove(0));
                break;
            case "Rock":
                System.out.println(rockQuestions.remove(0));
                break;
        }
    }

    private String determineCurrentCategory() {
        if (places[currentPlayer] == 0 || places[currentPlayer] == 4 || places[currentPlayer] == 8) {
            return "Pop";
        } else if (places[currentPlayer] == 1 || places[currentPlayer] == 5 || places[currentPlayer] == 9) {
            return "Science";
        } else if (places[currentPlayer] == 2 || places[currentPlayer] == 6 || places[currentPlayer] == 10) {
            return "Sports";
        } else {
            return "Rock";
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");

                purses[currentPlayer]++;

                System.out.println(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return true;
            }
        } else {
            System.out.println("Answer was corrent!!!!");

            purses[currentPlayer]++;

            System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

            boolean winner = didPlayerWin();

            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");

        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return true;
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

}
