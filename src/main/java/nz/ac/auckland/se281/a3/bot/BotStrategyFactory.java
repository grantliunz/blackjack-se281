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
        return switch (type) {
            case "R" -> new RandomStrategy();
            case "LR" -> new LowRiskStrategy();
            case "HR" -> new HighRiskStrategy();
            default -> null;
        };
    }

}
