package be.jidoka.clean.code.labs.trivia.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Game {

    private static final int LAST_BOARD_POSITION = 11;
    private static final int MAX_PLAYERS = 6;

    private final List<String> popQuestions = new LinkedList<>();
    private final List<String> scienceQuestions = new LinkedList<>();
    private final List<String> sportsQuestions = new LinkedList<>();
    private final List<String> rockQuestions = new LinkedList<>();

    private final List<String> players = new ArrayList<>();
    private final int[] places = new int[MAX_PLAYERS];
    private final int[] purses = new int[MAX_PLAYERS];
    private final boolean[] inPenaltyBox = new boolean[MAX_PLAYERS];

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

    // Seam for unit tests.
    /*default */int getCurrentPlayer() {
        return currentPlayer;
    }

    // Seam for unit tests.
    /*default */boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void add(String playerName) {
        players.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    // Roll is randomly called with a value between 1 and 5.
    // Returning a String is a Seam for unit tests.
    public String roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

                moveCurrentPlayerToNewPosition(roll);

                return askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");

                isGettingOutOfPenaltyBox = false;

                return null;
            }
        } else {
            moveCurrentPlayerToNewPosition(roll);

            return askQuestion();
        }
    }

    private void moveCurrentPlayerToNewPosition(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;

        if (places[currentPlayer] > LAST_BOARD_POSITION) {
            places[currentPlayer] = places[currentPlayer] - (LAST_BOARD_POSITION + 1);
        }

        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
    }

    // Returning a String is a Seam for unit tests.
    private String askQuestion() {
        if (places[currentPlayer] == 0 || places[currentPlayer] == 4 || places[currentPlayer] == 8) {
            System.out.println("The category is Pop");

            final String askedQuestion = popQuestions.remove(0);
            System.out.println(askedQuestion);

            return askedQuestion;
        } else if (places[currentPlayer] == 1 || places[currentPlayer] == 5 || places[currentPlayer] == 9) {
            System.out.println("The category is Science");

            final String askedQuestion = scienceQuestions.remove(0);
            System.out.println(askedQuestion);

            return askedQuestion;
        } else if (places[currentPlayer] == 2 || places[currentPlayer] == 6 || places[currentPlayer] == 10) {
            System.out.println("The category is Sports");

            final String askedQuestion = sportsQuestions.remove(0);
            System.out.println(askedQuestion);

            return askedQuestion;
        } else {
            System.out.println("The category is Rock");

            final String askedQuestion = rockQuestions.remove(0);
            System.out.println(askedQuestion);

            return askedQuestion;
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");

                purses[currentPlayer]++;

                System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

                boolean winner = didPlayerWin();
                moveToNextPlayer();

                return winner;
            } else {
                moveToNextPlayer();

                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");

            purses[currentPlayer]++;

            System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

            boolean winner = didPlayerWin();
            moveToNextPlayer();

            return winner;
        }
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

    private void moveToNextPlayer() {
        currentPlayer++;

        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");

        inPenaltyBox[currentPlayer] = true;

        moveToNextPlayer();
    }

}
