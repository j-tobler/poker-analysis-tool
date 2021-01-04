package Hands;

import Cards.Card;
import Cards.Rank;

import java.util.List;

public class Flush extends Hand {

    private Rank[] ranks;

    Flush(List<Card> cards) {
        super(cards);
        ranks = Card.getRanks(cards);
    }

    public Rank[] getRanks() {
        return ranks.clone();
    }

    @Override
    public int getHandTypeStrength() {
        return 5;
    }

    public int isStronger(Flush flush) {
        for (int i = 0; i < 5; i++) {
            if (ranks[i].getValue() > flush.ranks[i].getValue()) {
                return 1;
            } else if (ranks[i].getValue() < flush.ranks[i].getValue()) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Flush, %s high", ranks[0].toString());
    }
}
