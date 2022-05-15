package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

import java.util.List;

public class TargetHighestBidder implements DealerStrategy {

	@Override
	public Action action(int score, List<Player> players) {

		//Gets the players from input list
		Hand player1 = players.get(0).getHand();
		Hand bot1 = players.get(1).getHand();
		Hand bot2 = players.get(2).getHand();

		// Decide which player is the target based on player bids
		Hand target = player1;
		if (bot1.getBet() > target.getBet()) {
			target = bot1;
		}
		if (bot2.getBet() > target.getBet()) {
			target = bot2;
		}

		//Implement dealer logic to hit or hold
		if (target.isBust()) {
			return Action.HOLD;
		} else if (target.isBlackJack()) {
			if (score >= 17) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
			//Compares score to player
		} else {
			if (target.getScore() > score) {
				return Action.HIT;
			} else {
				return Action.HOLD;
			}
		}

	}
}
