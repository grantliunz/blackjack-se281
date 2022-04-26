package nz.ac.auckland.se281.a3;

import java.util.Random;

import nz.ac.auckland.se281.a3.Participant.Action;

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
		return rand.nextInt(102) - 1;
	}

}
