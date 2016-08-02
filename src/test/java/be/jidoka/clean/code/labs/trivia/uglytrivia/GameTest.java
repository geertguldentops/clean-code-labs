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

        assertThat(game.getPlaces()[0]).isEqualTo(0);
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

}