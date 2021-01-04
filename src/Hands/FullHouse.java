package Hands;

import Cards.Card;
import Cards.Rank;

import java.util.List;

public class FullHouse extends Hand {

    private Rank tripleRank;
    private Rank pairRank;

    FullHouse(List<Card> cards) {
        super(cards);
        tripleRank = cards.get(2).getRank();
        pairRank = tripleRank == cards.get(0).getRank() ? cards.get(3).getRank() : cards.get(0).getRank();
    }

    public Rank getTripleRank() {
        return tripleRank;
    }

    public Rank getPairRank() {
        return pairRank;
    }

    @Override
    public int getHandTypeStrength() {
        return 6;
    }

    public int isStronger(FullHouse fullHouse) { // fixme: use integer.compare for these
        if (tripleRank.getValue() > fullHouse.tripleRank.getValue()) {
            return 1;
        } else if (tripleRank.getValue() < fullHouse.tripleRank.getValue()) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Full house, %s's full of %s's", tripleRank.toString(), pairRank.toString());
    }
}
