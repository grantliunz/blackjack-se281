package nz.ac.auckland.se281.a3.bot;

public class BotStrategyFactory {
    public static BotStrategy createStrategy(String type) {
        // Determine the bot strategy based on input string
        return switch (type) {
            case "R" -> new RandomStrategy();
            case "LR" -> new LowRiskStrategy();
            case "HR" -> new HighRiskStrategy();
            default -> null;
        };
    }

}
