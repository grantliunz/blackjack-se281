package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Participant.Action;

import java.util.Random;

public class HighRiskStrategy implements BotStrategy {

	@Override
	public Action action(int score) {
		if (score >= 19) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}
	}

	@Override
	public int bet() {
		Random rand = new Random();
		return rand.nextInt(51) + 50;
	}

}
