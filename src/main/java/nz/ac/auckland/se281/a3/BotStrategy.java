package nz.ac.auckland.se281.a3;

import nz.ac.auckland.se281.a3.Participant.Action;

public interface BotStrategy {
	Action action(int score);

	int bet();
}