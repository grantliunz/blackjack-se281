package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Participant.Action;

public class LowRiskStrategy implements BotStrategy {

	@Override
	public Action action(int score) {
		if (score >= 17) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}
	}

	@Override
	public int bet() {
		Random rand = new Random();
		return rand.nextInt(41) + 10;
	}

}
