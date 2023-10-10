import code.Game;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

public class RollTest {
    Game game;


    @Before
    public void setUp() {
        game = new Game();
        game.addPlayer("Marek");
    }


    @Test
    public void normalRollTest() {
        int startingPosition = game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace();
        game.roll(3);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(), startingPosition + 3);
        startingPosition += 3;
        game.roll(2);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(), startingPosition + 2);
    }

    @Test
    public void negativeRollTest() {
        assertThrows(RuntimeException.class, () -> game.roll(-1));
    }

    @Test
    public void zeroRollTest() {
        int startingPosition = game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace();
        game.roll(0);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(), startingPosition);
    }

    @Test
    public void overflowRollTest() {
        game.getPLAYERS().get(0).setPlace(0);
        game.roll(13);
        assertEquals(game.getPLAYERS().get(0).getPlace(), 0);
        game.roll(999);
        assert(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace()<12);
    }
}
