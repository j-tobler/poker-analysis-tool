package Hands;

import Cards.Card;
import Cards.Rank;
import java.util.List;

public class HighCard extends Hand {

    // the five ranks of the cards, in descending order
    private Rank[] ranks;

    HighCard(List<Card> cards) {
        super(cards);
        ranks = Card.getRanks(cards);
    }

    public Rank[] getRanks() {
        return ranks.clone();
    }

    @Override
    public int getHandTypeStrength() {
        return 0;
    }

    /**
     * Checks if this high card hand is stronger than the passed high card hand.
     * @param highCard the high card to be checked against.
     * @return 1 if this hand is stronger, 0 if the passed hand is stronger,
     * or -1 if the hands are identical in strength.
     */
    public int isStronger(HighCard highCard) {
        for (int i = 0; i < 5; i ++) {
            if (ranks[0].getValue() > highCard.ranks[0].getValue()) {
                return 1;
            } else if (ranks[0].getValue() < highCard.ranks[0].getValue()) {
                return 0;
            }
        }
        // identical hand
        return -1;
    }

    @Override
    public String toString() {
        return String.format("High card, %s", ranks[0].toString());
    }
}
