package nz.ac.auckland.se281.a3.bot;

public class BotStrategyFactory {
	/**
	 * creates and returns the strategy of the bot based on input string
	 *
	 * @param type the string deciding the type
	 * @return the bot strategy
	 */
	public static BotStrategy createStrategy(String type) {
		// Determine the bot strategy based on input string
		switch (type) {
		case "R":
			return new RandomStrategy();
		case "LR":
			return new LowRiskStrategy();
		case "HR":
			return new HighRiskStrategy();
		default:
			// Default return should not be reached
			return null;
		}
	}

}
