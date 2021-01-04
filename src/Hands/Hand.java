package Hands;

import Cards.Card;

import java.util.Collections;
import java.util.List;

public abstract class Hand {

    // the cards of the hand, in descending order of rank
    private List<Card> cards;

    Hand(List<Card> cards) {
        assert cards.size() == 5;
        Collections.sort(cards);
        this.cards = cards;
    }

    /* Returns the (actual) cards contained in this hand fixme */
    public List<Card> getCards() {
        return cards;
    }

    public abstract int getHandTypeStrength();

    public int isStronger(Hand hand) {
        if (getHandTypeStrength() > hand.getHandTypeStrength()) {
            return 1;
        } else if (getHandTypeStrength() < hand.getHandTypeStrength()) {
            return 0;
        }
        return -1;
    }
}
