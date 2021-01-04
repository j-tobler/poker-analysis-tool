package Hands;

import Cards.Card;
import Cards.Rank;

import java.util.List;

public class FourOfAKind extends Hand {

    private Rank quadRank;
    private Rank kickerRank;

    FourOfAKind(List<Card> cards) {
        super(cards);
        if (cards.get(0).getRank().getValue() == cards.get(1).getRank().getValue()) {
            quadRank = cards.get(0).getRank();
            kickerRank = cards.get(4).getRank();
        } else {
            quadRank = cards.get(1).getRank();
            kickerRank = cards.get(0).getRank();
        }
    }

    public Rank getQuadRank() {
        return quadRank;
    }

    public Rank getKickerRank() {
        return kickerRank;
    }

    @Override
    public int getHandTypeStrength() {
        return 7;
    }

    public int isStronger(FourOfAKind fourOfAKind) {
        if (quadRank.getValue() > fourOfAKind.quadRank.getValue()) {
            return 1;
        } else if (quadRank.getValue() < fourOfAKind.quadRank.getValue()) {
            return 0;
        }
        if (kickerRank.getValue() > fourOfAKind.kickerRank.getValue()) {
            return 1;
        } else if (kickerRank.getValue() < fourOfAKind.kickerRank.getValue()) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Four of a kind, %s's", quadRank.toString());
    }
}
