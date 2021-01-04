package Hands;

import Cards.Card;
import java.util.ArrayList;
import java.util.List;

public class HandGenerator {

    private static final int MAX_CARDS_SIZE = 7;
    private static final int MIN_CARDS_SIZE = 5;
    private static List<Card> cards;

    public static Hand generateHand(List<Card> cards) {
        HandGenerator.cards = cards;
        int size = cards.size();
        assert size >= MIN_CARDS_SIZE && size <= MAX_CARDS_SIZE;

        List<Card> straightFlush = getStraightFlush();
        if (straightFlush != null) {
            return new StraightFlush(straightFlush);
        }

        List<Card> fourOfAKind = getFourOfAKind();
        if (fourOfAKind != null) {
            return new FourOfAKind(fourOfAKind);
        }

        List<Card> fullHouse = getFullHouse();
        if (fullHouse != null) {
            return new FullHouse(fullHouse);
        }

        List<Card> flush = getFlush();
        if (flush != null) {
            return new Flush(flush);
        }

        List<Card> straight = getStraight();
        if (straight != null) {
            return new Straight(straight);
        }

        List<Card> threeOfAKind = getThreeOfAKind();
        if (threeOfAKind != null) {
            return new ThreeOfAKind(threeOfAKind);
        }

        List<Card> twoPair = getTwoPair();
        if (twoPair != null) {
            return new TwoPair(twoPair);
        }

        List<Card> pair = getPair();
        if (pair != null) {
            return new Pair(pair);
        }

        return new HighCard(getHighCard());
    }

    private static List<Card> getStraightFlush() {
        List<Card> suited = getSuited();
        return suited == null ? null : getStraight(suited);
    }

    private static List<Card> getFourOfAKind() {
        List<Card> fourOfAKind = new ArrayList<>(5);
        List<Card> ranked = getRanked(4);
        if (ranked == null) {
            return null;
        }
        fourOfAKind.addAll(ranked);
        addKickers(fourOfAKind, fourOfAKind.get(0).getRank().getValue(), 1);
        return fourOfAKind;
    }

    private static List<Card> getFullHouse() {
        List<Card> fullHouse = new ArrayList<>(5);
        List<Card> triple = getRanked(3);
        if (triple == null) {
            return null;
        }
        List<Card> pair = getRanked(2, triple.get(0).getRank().getValue());
        if (pair == null) {
            return null;
        }
        fullHouse.addAll(triple);
        fullHouse.addAll(pair);
        return fullHouse;
    }

    private static List<Card> getFlush() {
        List<Card> suited = getSuited();
        return suited == null ? null : suited.subList(0, 5);
    }

    private static List<Card> getStraight() {
        return getStraight(cards);
    }

    private static List<Card> getStraight(List<Card> cards) {
        List<Card> straight = new ArrayList<>(5);
        int previousRank = 0;
        int i = 0;
        for (Card card : cards) {
            int rank = card.getRank().getValue();
            if (rank == previousRank) {
                continue;
            }
            if (rank != previousRank - 1) {
                i = 0;
            }
            straight.add(i++, card);
            if (i == 5) {
                return straight;
            }
            previousRank = rank;
        }
        return null;
    }

    private static List<Card> getThreeOfAKind() {
        List<Card> threeOfAKind = new ArrayList<>(5);
        List<Card> triple = getRanked(3);
        if (triple == null) {
            return null;
        }
        threeOfAKind.addAll(triple);
        addKickers(threeOfAKind, threeOfAKind.get(0).getRank().getValue(), 2);
        return threeOfAKind;
    }

    private static List<Card> getTwoPair() {
        List<Card> twoPair = new ArrayList<>(5);
        List<Card> pair = getRanked(2);
        if (pair == null) {
            return null;
        }
        List<Card> secondPair = getRanked(2, pair.get(0).getRank().getValue());
        if (secondPair == null) {
            return null;
        }
        twoPair.addAll(pair);
        twoPair.addAll(secondPair);
        int pairRank = pair.get(0).getRank().getValue();
        int secondPairRank = secondPair.get(0).getRank().getValue();
        // add kicker
        for (Card card : cards) {
            int rank = card.getRank().getValue();
            if (rank != pairRank && rank != secondPairRank) {
                twoPair.add(card);
                break;
            }
        }
        return twoPair;
    }

    private static List<Card> getPair() {
        List<Card> pairToReturn = new ArrayList<>(5);
        List<Card> pair = getRanked(2);
        if (pair == null) {
            return null;
        }
        pairToReturn.addAll(pair);
        addKickers(pairToReturn, pairToReturn.get(0).getRank().getValue(), 3);
        return pairToReturn;
    }

    private static List<Card> getHighCard() {
        return cards.subList(0, 5);
    }

    /* Returns a list of all cards belonging to any particular suit which contains 5 or more cards */
    private static List<Card> getSuited() {
        List<List<Card>> cardsBySuit = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            cardsBySuit.add(new ArrayList<>(MAX_CARDS_SIZE));
        }
        for (Card card : cards) {
            switch (card.getSuit()) {
                case CLUBS:
                    cardsBySuit.get(0).add(card);
                    break;
                case HEARTS:
                    cardsBySuit.get(1).add(card);
                    break;
                case SPADES:
                    cardsBySuit.get(2).add(card);
                    break;
                case DIAMONDS:
                    cardsBySuit.get(3).add(card);
                    break;
            }
        }
        for (List<Card> suitCategory : cardsBySuit) {
            if (suitCategory.size() >= 5) {
                return suitCategory;
            }
        }
        return null;
    }

    /* Returns the highest-ranked list of num cards with equal ranks */
    private static List<Card> getRanked(int num) {
        int count = 0, prevRank = 0;
        for (Card card : cards) {
            int rank = card.getRank().getValue();
            if (rank == prevRank) {
                if (++count == num - 1) {
                    int i = cards.indexOf(card);
                    return cards.subList(i + 1 - num, i + 1);
                }
            } else {
                count = 0;
                prevRank = rank;
            }
        }
        return null;
    }

    /* Returns the highest-ranked list of num cards with equal ranks, not including cards with ranks of excludeRank */
    private static List<Card> getRanked(int num, int excludeRank) {
        int count = 0, prevRank = 0;
        for (Card card : cards) {
            int rank = card.getRank().getValue();
            if (rank != excludeRank && rank == prevRank) {
                if (++count == num - 1) {
                    int i = cards.indexOf(card);
                    return cards.subList(i + 1 - num, i + 1);
                }
            } else {
                count = 0;
                prevRank = rank;
            }
        }
        return null;
    }

    /* Adds num of the highest ranked cards with rank not equal to invalidRank to the given list */
    private static void addKickers(List<Card> hand, int invalidRank, int num) {
        int count = 0;
        for (Card card : cards) {
            if (card.getRank().getValue() != invalidRank) {
                hand.add(card);
                if (++count == num) {
                    break;
                }
            }
        }
    }
}
