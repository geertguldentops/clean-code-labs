package be.jidoka.clean.code.labs.trivia.uglytrivia;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void shouldCreate50PopQuestions_onCreateGame() {
        final Game game = new Game();

        assertThat(game.getPopQuestions())
                .hasSize(50)
                .containsSequence("Pop Question 1", "Pop Question 2", "Pop Question 3", "Pop Question 4", "Pop Question 5");
    }

    @Test
    public void shouldCreate50ScienceQuestions_onCreateGame() {
        final Game game = new Game();

        assertThat(game.getScienceQuestions())
                .hasSize(50)
                .containsSequence("Science Question 8", "Science Question 9", "Science Question 10", "Science Question 11");
    }

    @Test
    public void shouldCreate50SportsQuestions_onCreateGame() {
        final Game game = new Game();

        assertThat(game.getSportsQuestions())
                .hasSize(50)
                .containsSequence("Sports Question 22", "Sports Question 23", "Sports Question 24", "Sports Question 25", "Sports Question 26");
    }

    @Test
    public void shouldCreate50RockQuestions_onCreateGame() {
        final Game game = new Game();

        assertThat(game.getRockQuestions())
                .hasSize(50)
                .containsSequence("Rock Question 45", "Rock Question 46", "Rock Question 47", "Rock Question 48", "Rock Question 49");
    }

    @Test
    public void shouldAddInitialPlaceAndPurseAndPenaltyBoxForPlayerMultiplePlayers_onMultipleAdds_withPlayerNames() {
        final Game game = new Game();
        game.add("Jos");
        game.add("Jan");
        game.add("Jef");

        assertThat(game.getPlaces()).hasSize(6).containsOnly(0);
        assertThat(game.getPurses()).hasSize(6).containsOnly(0);
        assertThat(game.getInPenaltyBox()).hasSize(6).containsOnly(false);
    }

}