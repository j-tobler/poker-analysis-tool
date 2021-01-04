package Hands;

import Cards.Card;
import Cards.Rank;

import java.util.List;

public class TwoPair extends Hand {

    private Rank[] pairRanks;
    private Rank kickerRank;

    TwoPair(List<Card> cards) {
        super(cards);
        // extract the pair ranks and kicker rank from the cards
        pairRanks = new Rank[2];
        Rank[] ranks = Card.getRanks(cards);
        if (ranks[0] != ranks[1]) {
            // cards must be organised as such: [c a a b b]
            kickerRank = ranks[0];
            pairRanks[0] = ranks[1];
            pairRanks[1] = ranks[3];
        } else if (ranks[2] != ranks[3]) {
            // cards must be organised as such: [a a c b b]
            kickerRank = ranks[2];
            pairRanks[0] = ranks[0];
            pairRanks[1] = ranks[3];
        } else {
            // cards must be organised as such: [a a b b c]
            kickerRank = ranks[4];
            pairRanks[0] = ranks[0];
            pairRanks[1] = ranks[2];
        }
    }

    public Rank getKickerRank() {
        return kickerRank;
    }

    public Rank[] getPairRanks() {
        return pairRanks.clone();
    }

    @Override
    public int getHandTypeStrength() {
        return 2;
    }

    public int isStronger(TwoPair twoPair) {
        for (int i = 0; i < 2; i++) {
            if (pairRanks[i].getValue() > twoPair.pairRanks[i].getValue()) {
                return 1;
            } else if (pairRanks[i].getValue() < twoPair.pairRanks[i].getValue()) {
                return 0;
            }
        }
        if (kickerRank.getValue() > twoPair.kickerRank.getValue()) {
            return 1;
        } else if (kickerRank.getValue() < twoPair.kickerRank.getValue()) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Two pair, %s's and %s's", pairRanks[0].toString(), pairRanks[1].toString());
    }
}
