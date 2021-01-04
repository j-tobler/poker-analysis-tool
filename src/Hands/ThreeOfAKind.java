package Hands;

import Cards.Card;
import Cards.Rank;

import java.util.List;

public class ThreeOfAKind extends Hand {

    private Rank tripleRank;
    private Rank[] kickerRanks;

    ThreeOfAKind(List<Card> cards) {
        super(cards);
        // extract triple rank and kickers
        Rank[] ranks = Card.getRanks(cards);
        kickerRanks = new Rank[2];
        if (ranks[0] == ranks[1]) {
            // cards must be organised as such: [a a a b c]
            tripleRank = ranks[0];
            kickerRanks[0] = ranks[3];
            kickerRanks[1] = ranks[4];
        } else if (ranks[1] == ranks[2]) {
            // cards must be organised as such: [b a a a c]
            tripleRank = ranks[1];
            kickerRanks[0] = ranks[0];
            kickerRanks[1] = ranks[4];
        } else {
            // cards must be organised as such: [b c a a a]
            tripleRank = ranks[2];
            kickerRanks[0] = ranks[0];
            kickerRanks[1] = ranks[1];
        }
    }

    public Rank getTripleRank() {
        return tripleRank;
    }

    public Rank[] getKickerRanks() {
        return kickerRanks.clone();
    }

    @Override
    public int getHandTypeStrength() {
        return 3;
    }

    public int isStronger(ThreeOfAKind threeOfAKind) {
        if (tripleRank.getValue() > threeOfAKind.tripleRank.getValue()) {
            return 1;
        } else if (tripleRank.getValue() < threeOfAKind.tripleRank.getValue()) {
            return 0;
        }
        for (int i = 0; i < 2; i++) {
            if (kickerRanks[i].getValue() > threeOfAKind.kickerRanks[i].getValue()) {
                return 1;
            } else if (kickerRanks[i].getValue() < threeOfAKind.kickerRanks[i].getValue()) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Three of a kind, %s's", tripleRank.toString());
    }
}
