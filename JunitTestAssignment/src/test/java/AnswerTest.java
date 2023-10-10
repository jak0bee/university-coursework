import code.Game;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AnswerTest {
    Game game;


    @Before
    public void setUp() {
        game = new Game();
        game.addPlayer("Marek");
    }


    @Test
    public void rightAnswerTest() {
        int startingPurse = game.getPLAYERS().get(0).getPurse();
        game.wasCorrectlyAnswered();
        assertEquals(startingPurse + 1, game.getPLAYERS().get(0).getPurse());
    }

    @Test
    public void wrongAnswerTest() {
        int startingPurse = game.getPLAYERS().get(0).getPurse();
        game.wrongAnswer();
        assertEquals(startingPurse, game.getPLAYERS().get(0).getPurse());
    }
}
