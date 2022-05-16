package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public interface DealerStrategy {

	/**
	 * decides the action of the dealer based on the current strategy and player
	 * hands
	 *
	 * @param score   the current score of the dealer
	 * @param players the list of players
	 * @return the dealer action
	 */
	Action action(int score, List<Player> players);

}
