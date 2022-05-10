package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetTopWinner implements DealerStrategy {

	@Override
	public Action action(int score, List<Player> players) {
		Player player1 = players.get(0);
		Player bot1 = players.get(1);
		Player bot2 = players.get(2);

		// Decide which player is the target
		Player target = player1;
		if (player1.getNetWins() < bot1.getNetWins()) {
			target = bot1;
		} else if (player1.getNetWins() < bot2.getNetWins()) {
			target = bot2;
		}

		// Checks to see action of dealer
		if (target.getHand().isBust()) {
			return Action.HOLD;
		} else if (target.getHand().getScore() == 21 && target.getHand().getCards().size() == 2) {
			if (score >= 17) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		} else {
			if (target.getHand().getScore() > score) {
				return Action.HIT;
			} else {
				return Action.HOLD;
			}
		}
	}

}
