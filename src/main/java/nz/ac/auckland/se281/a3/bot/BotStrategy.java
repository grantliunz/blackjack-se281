package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Participant.Action;

public interface BotStrategy {

	/**
	 * Decides the action of the bot based on the current strategy and score
	 *
	 * @param score the current score of the bot
	 * @return the action decided
	 */
	Action action(int score);

	/**
	 * Decides the random bet amount of the bot based on the current strategy
	 *
	 * @return the integer amount to bet
	 */
	int bet();
}
