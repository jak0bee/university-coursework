import code.Game;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class QuestionCategoryTest {
    Game game;


    @Before
    public void setUp() {
        game = new Game();
        game.addPlayer("Marek");
    }

    @Test
    public void rockTest() {
        String expected = "Rock";
        testCategory(expected, 3);
        testCategory(expected, 7);
        testCategory(expected, 11);

    }

    @Test
    public void scienceTest() {
        String expected = "Science";
        testCategory(expected, 1);
        testCategory(expected, 5);
        testCategory(expected, 9);
    }

    @Test
    public void popTest() {
        String expected = "Pop";
        testCategory(expected, 0);
        testCategory(expected, 4);
        testCategory(expected, 8);
    }

    @Test
    public void sportsTest() {
        String expected = "Sports";
        testCategory(expected, 2);
        testCategory(expected, 6);
        testCategory(expected, 10);
    }

    public void testCategory(String expected, int place) {
        game.getPLAYERS().get(0).setPlace(place);
        assertEquals(expected, game.currentCategory());
    }
}
