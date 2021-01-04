package Cards;

import java.util.List;

public class Card implements Comparable<Card> {

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    /* Returns the ranks of the given cards, maintaining original order */
    public static Rank[] getRanks(List<Card> cards) {
        int len = cards.size();
        Rank[] rankList = new Rank[len];
        for (int i = 0; i < len; i++) {
            rankList[i] = cards.get(i).rank;
        }
        return rankList;
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(otherCard.rank.getValue(), rank.getValue());
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Card) {
            Card cardObj = (Card) obj;
            return rank == cardObj.rank && suit == cardObj.suit;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return rank.getValue() * suit.hashCode();
    }
}