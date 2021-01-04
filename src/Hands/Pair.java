package Hands;

import Cards.Card;
import Cards.Rank;

import java.util.List;

public class Pair extends Hand {

    private Rank pairRank;
    private Rank[] kickerRanks;

    Pair(List<Card> cards) {
        super(cards);
        // extract the pair rank and kicker ranks from the cards
        kickerRanks = new Rank[3];       // necessary?
        Rank[] ranks = Card.getRanks(cards);
        Rank rank;
        int i = 0;
        while ((rank = ranks[i]) != ranks[i + 1]) {
            kickerRanks[i++] = rank;
        }
        pairRank = rank;
        i++;
        while (++i < 5) {
            kickerRanks[i - 2] = ranks[i];
        }
    }

    public Rank getPairRank() {
        return pairRank;
    }

    public Rank[] getKickerRanks() {
        return kickerRanks.clone();
    }

    @Override
    public int getHandTypeStrength() {
        return 1;
    }

    public int isStronger(Pair pair) {
        if (pairRank.getValue() > pair.pairRank.getValue()) {
            return 1;
        } else if (pairRank.getValue() < pair.pairRank.getValue()) {
            return 0;
        }
        for (int i = 0; i < 3; i++) {
            if (kickerRanks[i].getValue() > pair.kickerRanks[i].getValue()) {
                return 1;
            } else if (kickerRanks[i].getValue() < pair.kickerRanks[i].getValue()) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Pair, %s's", pairRank.toString());
    }
}
