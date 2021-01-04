package CardCollections;

import Cards.Card;
import Cards.Rank;
import Cards.Suit;
import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    void removeFromDeck(Card card) {
        boolean deckContainedCard = cards.removeIf(card::equals);
        assert deckContainedCard;
    }

    void removeFromDeck(List<Card> cards) {
        cards.forEach(this::removeFromDeck);
    }
}
