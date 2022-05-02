package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Participant;
import nz.ac.auckland.se281.a3.Participant.Action;

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
