package nz.ac.auckland.se281.a3;

public class BotStrategyFactory {
	public static BotStrategy createStrategy(String type) {
		switch (type) {
		case "R":
			return new RandomStrategy();

		case "LR":
			return new LowRiskStrategy();

		case "HR":
			return new HighRiskStrategy();

		default:
			System.err.println("Strategy Error");
			System.exit(0);
		}
		return null;
	}

}
