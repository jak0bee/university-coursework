
package code;

/**
 * The Player class represents a single player in the game and their properties.
 */
public class Player {
    private final String NAME;
    private int place;
    private int purse;
    private boolean inPenaltyBox;


    public Player(String name) {
        this.NAME = name;
        this.place = 0;
        this.purse = 0;
        this.inPenaltyBox = false;
    }


    public String getName() {
        return NAME;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPurse() {
        return purse;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
