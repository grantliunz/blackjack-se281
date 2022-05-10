package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Participant.Action;

public interface BotStrategy {

	/**
	 * 
	 * @param score
	 * @return
	 */
	Action action(int score);

	/**
	 * 
	 * @return
	 */
	int bet();
}
