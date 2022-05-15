package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Participant.Action;

import java.util.Random;

public class RandomStrategy implements BotStrategy {

	@Override
	public Action action(int score) {
		float randomNumber = new Random().nextFloat();
		if (randomNumber <= 0.5) {
			return Action.HIT;
		}

		return Action.HOLD;
	}

	@Override
	public int bet() {
		Random rand = new Random();
		return rand.nextInt(100) + 1;
	}

}
