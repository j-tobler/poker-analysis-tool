package CardCollections;

import Cards.Card;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Card> cards;

    public Board() {
        cards = new ArrayList<>(5);
    }

    public void dealFlop (List<Card> flopCards, Deck deck) {
        assert flopCards.size() == 3;
        deck.removeFromDeck(flopCards);
        cards.addAll(flopCards);
    }

    public void dealTurn (Card turnCard, Deck deck) {
        deck.removeFromDeck(turnCard);
        cards.add(turnCard);
    }

    public void dealRiver (Card riverCard, Deck deck) {
        deck.removeFromDeck(riverCard);
        cards.add(riverCard);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
