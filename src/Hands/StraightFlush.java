package Hands;

import Cards.Card;
import Cards.Rank;
import java.util.List;

public class StraightFlush extends Hand {

    private Rank highCardRank;

    StraightFlush(List<Card> cards) {
        super(cards);
        highCardRank = cards.get(0).getRank();
    }

    public Rank getHighCardRank() {
        return highCardRank;
    }

    @Override
    public int getHandTypeStrength() {
        return 8;
    }

    public int isStronger(StraightFlush straightFlush) {
        if (highCardRank.getValue() > straightFlush.highCardRank.getValue()) {
            return 1;
        } else if (highCardRank.getValue() < straightFlush.highCardRank.getValue()) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Straight flush, %s High", highCardRank.toString());
    }
}
