package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetHighestBidder implements DealerStrategy {

	@Override
	public Action action(int score, List<Player> players) {

		Hand player1 = players.get(0).getHand();
		Hand bot1 = players.get(1).getHand();
		Hand bot2 = players.get(2).getHand();

		// Decide which player is the target
		Hand target = player1;
		if (player1.getBet() < bot1.getBet()) {
			target = bot1;
		} else if (player1.getBet() < bot2.getBet()) {
			target = bot2;
		}

		if (target.isBust()) {
			return Action.HOLD;
		} else if (target.getScore() == 21 && target.getCards().size() == 2) {
			if (score >= 17) {
				return Action.HIT;
			} else {
				return Action.HOLD;
			}
		} else {
			if (target.getScore() > score) {
				return Action.HIT;
			} else {
				return Action.HOLD;
			}
		}

	}
}
