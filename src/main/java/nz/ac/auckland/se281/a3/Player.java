package nz.ac.auckland.se281.a3;

/**
 * You can (and should) add new fields and/or methods
 */
public abstract class Player extends Participant {

    private int wins = 0;
    private int losses = 0;


    public Player(String name) {
        super(name);
    }

    public abstract int makeABet();

    /**
     *
     */
    public void roundWon() {
        wins += 1;
    }

    /**
     *
     */
    public void roundLost() {
        losses += 1;
    }

    public int getNetWins() {
        return wins - losses;
    }

}
