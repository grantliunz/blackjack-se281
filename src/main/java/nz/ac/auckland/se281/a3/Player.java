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
	 * Updates the current win counter if the player won the round
	 */
	public void roundWon() {
		wins += 1;
	}

	/**
	 * Updates the current loss counter if the player lost the round
	 */
	public void roundLost() {
		losses += 1;
	}

	public int getNetWins() {
		return wins - losses;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}


}
