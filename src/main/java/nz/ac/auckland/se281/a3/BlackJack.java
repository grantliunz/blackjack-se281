package nz.ac.auckland.se281.a3;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.a3.bot.Bot;
import nz.ac.auckland.se281.a3.bot.BotStrategy;
import nz.ac.auckland.se281.a3.bot.BotStrategyFactory;
import nz.ac.auckland.se281.a3.dealer.Dealer;
import nz.ac.auckland.se281.a3.dealer.TargetHighestBidder;
import nz.ac.auckland.se281.a3.dealer.TargetTopWinner;

/**
 * Unless it is specified in the JavaDoc, you cannot change any methods.
 * <p>
 * You can add new methods and/or new instance fields
 */
public class BlackJack {

	private List<Player> players;
	private Dealer dealer;
	private Deck deck;

	/**
	 * Initializes the blackjack game with the deck of cards that are being used by
	 * creating the list of players
	 *
	 * @param deck the deck of cards being used
	 */
	public BlackJack(Deck deck) {
		this.deck = deck;
		players = new ArrayList<>();
		players.add(new Human("Player1")); // add the Human player first.
	}

	/**
	 * A protected constructor is for testing reasons and is not used.
	 *
	 * @param cards the cards in the game
	 */
	protected BlackJack(Card... cards) {
		this(new Deck(cards));

	}

	/**
	 * The empty constructor that calls another constructor with a Deck instance
	 */
	public BlackJack() {
		this(new Deck());
	}

	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Gets the bot strategy from user input that is chosen from three options
	 *
	 * @return the chosen bot strategy
	 */
	private String getBotStrategy() {
		System.out.println("Choose Bot strategy: random (R) - low risk (LR) - high risk (HR)");
		String result = Main.scanner.next();
		while (!result.equals("R") && !result.equals("LR") && !result.equals("HR") && !result.equals("A")) {
			System.out.println("please type \"R\", \"LR\",  \"HR\"");
			result = Main.scanner.next();
		}
		return result;
	}

	/**
	 * Starts the main game and loops through each round while the player still
	 * wants to play
	 */
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
	 * This method initializes the Bots, as well as setting their strategies Task 1
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
	 * This method initializes the Dealer, as well as setting its strategy Task2
	 */
	protected void initDealer() {
		// set the initial strategy using the Strategy pattern
		dealer = new Dealer("Dealer", players);
		dealer.setStrategy(new TargetHighestBidder());
	}

	/**
	 * This method prints and updates the results (wins and losses) of the players
	 * you should Task 2 and Task 3
	 *
	 * @param round the current round counter
	 */
	protected void printAndUpdateResults(int round) {

		boolean dealerTargetWinner = false;

		// Loops through each player and checks win condition
		for (Player player : players) {
			if (checkIfWon(player)) {
				player.roundWon();
				System.out.printf("Round %d: %s won %d chips%n", round, player.getName(), player.getHand().getBet());
			} else {
				player.roundLost();
				System.out.printf("Round %d: %s lost %d chips%n", round, player.getName(), player.getHand().getBet());
			}
			// Sees if dealer strategy needs to change
			if (player.getNetWins() >= 2) {
				dealerTargetWinner = true;
			}
		}

		// changes dealer strategy
		if (dealerTargetWinner) {
			dealer.setStrategy(new TargetTopWinner());
		} else {
			dealer.setStrategy(new TargetHighestBidder());

		}

	}

	/**
	 * This method should print the statistic of the game when it ends
	 */
	protected void printGameStatistics() {
		System.out.printf("%s won %d times and lost %d times%n", players.get(0).getName(), players.get(0).getWins(),
				players.get(0).getLosses());
		System.out.printf("%s won %d times and lost %d times%n", players.get(1).getName(), players.get(1).getWins(),
				players.get(1).getLosses());
		System.out.printf("%s won %d times and lost %d times%n", players.get(2).getName(), players.get(2).getWins(),
				players.get(2).getLosses());

	}

	/**
	 * checks if the player has won/lost via bust, blackjack or outscoring the
	 * dealer
	 *
	 * @param player the player being checked
	 * @return if the player has won
	 */
	private boolean checkIfWon(Player player) {

		// Player loses is they bust or dealer blackjacks
		if (player.getHand().isBust() || dealer.getHand().isBlackJack()) {
			return false;
		}

		// Player wins if they blackjack or dealer busts
		if (player.getHand().isBlackJack() || dealer.getHand().isBust()) {
			return true;
		}

		// player wins if they outscore dealer
		return player.getHand().getScore() > dealer.getHand().getScore();

	}
}
