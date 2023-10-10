
package code;

import java.util.Random;

/**
 * Game rules:<p>
 * The game consists of a number of players (between 2 and 6), each having a game piece on the game board.
 * Each game piece starts at the beginning of the board and moves around as the game progresses.<p>
 * Game Flow:<p>
 * 1. Each player is added to the game before the game starts.<p>
 * 2. The game begins with the first player rolling the dice.<p>
 * 3. The player moves their game piece forward by a number of places equal to the roll of the dice.<p>
 * 4. A question is asked of the player based on the category of the place they landed on.<p>
 * 5. If the player answers the question correctly, they earn a gold coin. If they answer wrong they are sent to the penalty box.<p>
 * 6. A player can get out of the penalty box by rolling an even number.<p>
 * 7. The turn then moves to the next player.<p>
 * 8. The game ends once a player has collected 6 gold coins.
 */
public class GameRunner {
    public static void main(String[] args) {
        Game aGame = new Game();

        aGame.addPlayer("Chet");
        aGame.addPlayer("Pat");
        aGame.addPlayer("Sue");

        if (aGame.howManyPlayers() < 2)
            System.out.println("Not enough players to start a new game!");
        else {
            System.out.println("We have " + aGame.howManyPlayers() + " players in the game.");

            Random rand = new Random();
            boolean notAWinner;
            do {
                aGame.roll(rand.nextInt(5) + 1);
                if (rand.nextInt(9) == 7)
                    notAWinner = aGame.wrongAnswer();
                else
                    notAWinner = aGame.wasCorrectlyAnswered();

            } while (notAWinner);
        }
    }
}
