package be.jidoka.clean.code.labs.trivia.uglytrivia;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class GameTest {

    private final Game game = new Game();

    @Test
    public void shouldCreate50PopQuestions_onCreateGame() {
        assertThat(game.getPopQuestions())
                .hasSize(50)
                .containsSequence("Pop Question 1", "Pop Question 2", "Pop Question 3", "Pop Question 4", "Pop Question 5");
    }

    @Test
    public void shouldCreate50ScienceQuestions_onCreateGame() {
        assertThat(game.getScienceQuestions())
                .hasSize(50)
                .containsSequence("Science Question 8", "Science Question 9", "Science Question 10", "Science Question 11");
    }

    @Test
    public void shouldCreate50SportsQuestions_onCreateGame() {
        assertThat(game.getSportsQuestions())
                .hasSize(50)
                .containsSequence("Sports Question 22", "Sports Question 23", "Sports Question 24", "Sports Question 25", "Sports Question 26");
    }

    @Test
    public void shouldCreate50RockQuestions_onCreateGame() {
        assertThat(game.getRockQuestions())
                .hasSize(50)
                .containsSequence("Rock Question 45", "Rock Question 46", "Rock Question 47", "Rock Question 48", "Rock Question 49");
    }

    @Test
    public void shouldInitialisePlacesAndPursesAndPenaltyBoxes_onCreateGame() {
        assertThat(game.getPlaces()).hasSize(6).containsOnly(0);
        assertThat(game.getPurses()).hasSize(6).containsOnly(0);
        assertThat(game.getInPenaltyBox()).hasSize(6).containsOnly(false);
    }

    @Test
    public void shouldAddPlayers_onMultipleAdds_withPlayerNames() {
        game.add("Jos");
        game.add("Jan");
        game.add("Jef");

        assertThat(game.getPlayers()).containsOnly("Jos", "Jan", "Jef");
    }

    @Test
    public void shouldMovePlayerToNewPosition_onRoll_withRollLessThanOrEqualTo11() {
        game.add("Jos");

        assertThat(game.getPlaces()[0]).isZero();

        game.roll(4);

        assertThat(game.getPlaces()[0]).isEqualTo(4);
    }

    @Test
    public void shouldMovePlayerToBeginOfBoard_onRoll_withRollExactly12() {
        game.add("Jos");

        assertThat(game.getPlaces()[0]).isZero();

        game.roll(4);
        game.roll(5);
        game.roll(3);

        assertThat(game.getPlaces()[0]).isZero();
    }

    @Test
    public void shouldMovePlayerToPositionMinusBoardSize_onRoll_withRollMoreThan12() {
        game.add("Jos");

        assertThat(game.getPlaces()[0]).isZero();

        game.roll(5);
        game.roll(5);
        game.roll(3);

        assertThat(game.getPlaces()[0]).isEqualTo(1);
    }

    @Test
    public void shouldReturnPopQuestions_onRoll_withRollMovesYouToPosition4Or8Or0() {
        game.add("Jos");

        assertThat(game.roll(4)).isEqualTo("Pop Question 1");
        assertThat(game.roll(4)).isEqualTo("Pop Question 2");
        assertThat(game.roll(4)).isEqualTo("Pop Question 3");
    }

    @Test
    public void shouldReturnScienceQuestions_onRoll_withRollMovesYouToPosition1Or5Or9() {
        game.add("Jos");

        assertThat(game.roll(1)).isEqualTo("Science Question 1");
        assertThat(game.roll(4)).isEqualTo("Science Question 2");
        assertThat(game.roll(4)).isEqualTo("Science Question 3");
    }

    @Test
    public void shouldReturnSportsQuestions_onRoll_withRollMovesYouToPosition2Or6Or10() {
        game.add("Jos");

        assertThat(game.roll(2)).isEqualTo("Sports Question 1");
        assertThat(game.roll(4)).isEqualTo("Sports Question 2");
        assertThat(game.roll(4)).isEqualTo("Sports Question 3");
    }

    @Test
    public void shouldReturnRockQuestions_onRoll_withRollMovesYouToPosition3Or7Or11() {
        game.add("Jos");

        assertThat(game.roll(3)).isEqualTo("Rock Question 1");
        assertThat(game.roll(4)).isEqualTo("Rock Question 2");
        assertThat(game.roll(4)).isEqualTo("Rock Question 3");
    }

    @Test
    public void shouldMoveCurrentPlayerToPenaltyBoxAndMoveCurrentPlayerUpByOne_onWrongAnswer_withCurrentPlayerIsNotLastPlayer() {
        game.add("Jos");
        game.add("Jan");

        assertThat(game.getCurrentPlayer()).isZero();
        assertThat(game.getInPenaltyBox()[0]).isFalse();
        assertThat(game.getInPenaltyBox()[1]).isFalse();

        game.wrongAnswer();

        assertThat(game.getCurrentPlayer()).isEqualTo(1);
        assertThat(game.getInPenaltyBox()[0]).isTrue();
        assertThat(game.getInPenaltyBox()[1]).isFalse();
    }

    @Test
    public void shouldMoveCurrentPlayerToPenaltyBoxAndMoveCurrentPlayerToFirstPlayer_onWrongAnswer_withCurrentPlayerIsLastPlayer() {
        game.add("Jos");
        game.add("Jan");
        game.add("Jef");
        game.add("An");
        game.add("Annie");
        game.add("An-Marie");

        // Move current player to the last player, An-Marie.
        for (int i = 0; i < 5; i++) {
            game.wrongAnswer();
        }

        assertThat(game.getCurrentPlayer()).isEqualTo(5);
        assertThat(game.getInPenaltyBox()[0]).isTrue();
        assertThat(game.getInPenaltyBox()[1]).isTrue();
        assertThat(game.getInPenaltyBox()[2]).isTrue();
        assertThat(game.getInPenaltyBox()[3]).isTrue();
        assertThat(game.getInPenaltyBox()[4]).isTrue();
        assertThat(game.getInPenaltyBox()[5]).isFalse();

        game.wrongAnswer();

        assertThat(game.getCurrentPlayer()).isZero();
        assertThat(game.getInPenaltyBox()[5]).isTrue();
    }

    @Test
    public void shouldReturnNullAnsweredQuestionAndWillNotGetOutOfPenaltyBox_onRoll_withEvenNumberAndCurrentPlayerIsInPenaltyBox() {
        game.add("Jos");
        game.add("Jan");
        game.add("Jef");
        game.add("An");
        game.add("Annie");
        game.add("An-Marie");

        // Move current player to the first player AFTER he has gotten the wrongAnswer and is in the penalty box.
        for (int i = 0; i < 6; i++) {
            game.wrongAnswer();
        }

        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getCurrentPlayer()).isZero();
        assertThat(game.getInPenaltyBox()[0]).isTrue();

        final String answeredQuestion = game.roll(2);

        assertThat(answeredQuestion).isNull();
        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
    }

    @Test
    public void shouldReturnAnsweredQuestionAndWillGetOutOfPenaltyBox_onRoll_withOddNumberAndCurrentPlayerIsInPenaltyBox() {
        game.add("Jos");
        game.add("Jan");
        game.add("Jef");
        game.add("An");
        game.add("Annie");
        game.add("An-Marie");

        // Move current player to the first player AFTER he has gotten the wrongAnswer and is in the penalty box.
        for (int i = 0; i < 6; i++) {
            game.wrongAnswer();
        }

        assertThat(game.isGettingOutOfPenaltyBox()).isFalse();
        assertThat(game.getCurrentPlayer()).isZero();
        assertThat(game.getInPenaltyBox()[0]).isTrue();

        final String answeredQuestion = game.roll(1);

        assertThat(answeredQuestion).isNotNull();
        assertThat(game.isGettingOutOfPenaltyBox()).isTrue();
    }

    @Test
    public void shouldReturnLoserAndAddGoldCoinToCurrentPlayerPurseAndMoveToNextPlayer_onWasCorrectlyAnswered_withCurrentPlayerIsNotInPenaltyBoxAndHasLessThan6GoldCoins() {
        game.add("Jan");
        game.add("Jos");

        assertThat(game.getCurrentPlayer()).isZero();
        assertThat(game.getInPenaltyBox()[0]).isFalse();
        assertThat(game.getPurses()[0]).isZero();

        final boolean isCurrentPlayerLoser = game.wasCorrectlyAnswered();
        assertThat(isCurrentPlayerLoser).isTrue();

        assertThat(game.getInPenaltyBox()[0]).isFalse();
        assertThat(game.getPurses()[0]).isEqualTo(1);
        assertThat(game.getCurrentPlayer()).isEqualTo(1);
    }

    @Test
    public void shouldReturnWinnerWhichHas6GoldenCoinsInPurse_onWasCorrectlyAnswered_withCurrentPlayerHas6GoldCoins() {
        game.add("Jos");
        game.add("Jan");
        game.add("Jef");
        game.add("An");
        game.add("Annie");
        game.add("An-Marie");

        // Each time a player answers correctly the game moves to the next player, so with 6 players, the winner will be first player, after 31 consecutive correct answers.

        // Let everyone answer correctly at least 5 times.
        for (int i = 0; i < 30; i++) {
            game.wasCorrectlyAnswered();
        }

        assertThat(game.getCurrentPlayer()).isEqualTo(0);
        assertThat(game.getPurses()[0]).isEqualTo(5);

        // 6th correct answer of player 1!
        final boolean isCurrentPlayerLoser = game.wasCorrectlyAnswered();
        assertThat(isCurrentPlayerLoser).isFalse();

        assertThat(game.getPurses()[0]).isEqualTo(6);
        assertThat(game.getCurrentPlayer()).isEqualTo(1);
        assertThat(game.getPurses()[1]).isEqualTo(5);
    }

}