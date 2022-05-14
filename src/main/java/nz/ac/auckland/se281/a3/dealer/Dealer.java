package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant;
import nz.ac.auckland.se281.a3.Player;

import java.util.List;

/**
 * You should change this class for Task 2
 */
public class Dealer extends Participant {
    private DealerStrategy strategy;
    private List<Player> players;

    public Dealer(String name, List<Player> players) {
        super(name);
        this.players = players;
    }

    @Override
    public Action decideAction(Hand hand) {
        return strategy.action(getHand().getScore(), players);
    }

    public void setStrategy(DealerStrategy strategy) {
        this.strategy = strategy;
    }

}
