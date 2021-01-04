package Hands;

import Cards.Card;
import Cards.Rank;

import java.util.List;

public class Straight extends Hand {

    private Rank highCardRank;

    Straight(List<Card> cards) {
        super(cards);
        highCardRank = cards.get(0).getRank();
    }

    public Rank getHighCardRank() {
        return highCardRank;
    }

    @Override
    public int getHandTypeStrength() {
        return 4;
    }

    public int isStronger(Straight straight) {
        if (highCardRank.getValue() > straight.highCardRank.getValue()) {
            return 1;
        } else if (highCardRank.getValue() < straight.highCardRank.getValue()) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Straight, %s high", highCardRank.toString());
    }
}
