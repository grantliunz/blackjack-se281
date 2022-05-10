package nz.ac.auckland.se281.a3;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {

	int netWins = 0;

	public Player(String name) {
		super(name);
	}

	public abstract int makeABet();

	/**
	 * 
	 */
	public void roundWon() {
		netWins += 1;
	}

	/**
	 * 
	 */
	public void roundLost() {
		netWins -= 1;
	}

	public int getNetWins() {
		return netWins;
	}

}
