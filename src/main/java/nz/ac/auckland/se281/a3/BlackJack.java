package nz.ac.auckland.se281.a3;

import nz.ac.auckland.se281.a3.bot.Bot;
import nz.ac.auckland.se281.a3.bot.BotStrategy;
import nz.ac.auckland.se281.a3.bot.BotStrategyFactory;
import nz.ac.auckland.se281.a3.dealer.Dealer;
import nz.ac.auckland.se281.a3.dealer.TargetHighestBidder;
import nz.ac.auckland.se281.a3.dealer.TargetTopWinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Unless it is specified in the JavaDoc, you cannot change any methods.
 * <p>
 * You can add new methods and/or new instance fields
 */
public class BlackJack {

    private List<Player> players;
    private Dealer dealer;
    private Deck deck;

    private int round = 0;

    public BlackJack(Deck deck) {
        this.deck = deck;
        players = new ArrayList<>();
        players.add(new Human("Player1")); // add the Human player first.
    }

    /**
     * Thi constructor is for testing reasons
     *
     * @param cards
     */
    protected BlackJack(Card... cards) {
        this(new Deck(cards));

    }

    public BlackJack() {
        this(new Deck());
    }

    public List<Player> getPlayers() {
        return players;
    }

    private String getBotStrategy() {
        System.out.println("Choose Bot strategy: random (R) - low risk (LR) - high risk (HR)");
        String result = Main.scanner.next();
        while (!result.equals("R") && !result.equals("LR") && !result.equals("HR") && !result.equals("A")) {
            System.out.println("please type \"R\", \"LR\",  \"HR\"");
            result = Main.scanner.next();
        }
        return result;
    }

    // do not change this method
    public void start() {
        initBots();
        initDealer();
        String res;
        int round = 0;
        do {
            round++;
            for (Participant p : players) {
                p.play(deck, round);
            }
            dealer.play(deck, round);
            printAndUpdateResults(round); // after each game print result and update scoreboard
            System.out.println("Do you want to play again?");
            res = Main.scanner.next();
            while (!res.equals("yes") && !res.equals("no")) {
                System.out.println("please type either \"yes\" or \"no\"");
                res = Main.scanner.next();
            }
        } while (res.equals("yes"));
        printGameStatistics(); // when the user terminates the game print the statistics
    }

    /**
     * TODO This method initializes the Bots, you should change this method for
     * Task1
     */
    protected void initBots() {
        Bot bot1 = new Bot("Bot1");
        Bot bot2 = new Bot("Bot2");
        String botStrategyString = getBotStrategy(); // UNCOMMENT THIS
        // create and set Bots strategy here
        BotStrategy botStrategy = BotStrategyFactory.createStrategy(botStrategyString);
        bot1.setStrategy(botStrategy);
        bot2.setStrategy(botStrategy);
        players.add(bot1);
        players.add(bot2);
    }

    /**
     * TODO This method initializes the Dealer, you should change this method for
     * Task2
     */
    protected void initDealer() {
        // set the initial strategy using the Strategy pattern
        dealer = new Dealer("Dealer", players);
        dealer.setStrategy(new TargetHighestBidder());
    }

    /**
     * TODO This method prints and updates the results (wins and losses) you should
     * change this method for Task 2 and Task 3
     */
    protected void printAndUpdateResults(int round) {

        boolean dealerTargetWinner = false;


        for (Player player : players) {
            if (checkIfWon(player)) {
                player.roundWon();
                System.out.println(String.format("Round %d: %s won %d chips", round, player.getName(), player.getHand().getBet()));
            } else {
                player.roundLost();
                System.out.println(String.format("Round %d: %s lost %d chips", round, player.getName(), player.getHand().getBet()));
            }
            if (player.getNetWins() >= 2) {
                dealerTargetWinner = true;
            }
        }
        round++;


        if (dealerTargetWinner) {
            dealer.setStrategy(new TargetTopWinner());
        } else {
            dealer.setStrategy(new TargetHighestBidder());

        }

    }

    /**
     * TODO This method should print the statistic of the game when it ends
     */
    protected void printGameStatistics() {

    }

    /**
     * checks if the player has won/lost via bust, blackjack or outscoring the
     * dealer
     *
     * @param player the player being checked
     * @return if the player has won
     */
    private boolean checkIfWon(Player player) {
        if (player.getHand().isBust() || dealer.getHand().isBlackJack()) {
            return false;
        }

        if (player.getHand().isBlackJack() || dealer.getHand().isBust()) {
            return true;
        }

        if (player.getHand().getScore() > dealer.getHand().getScore()) {
            return true;
        }

        return false;

    }
}
