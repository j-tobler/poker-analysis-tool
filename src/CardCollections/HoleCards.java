package CardCollections;

import Cards.Card;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoleCards {

    private List<Card> cards;

    public HoleCards(List<Card> cards, Deck deck) {
        assert cards.size() == 2;
        Collections.sort(cards);
        deck.removeFromDeck(cards);
        this.cards = cards;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    private boolean isSuited() {
        return cards.get(0).getSuit() == cards.get(1).getSuit();
    }

    public boolean isStronger(HoleCards holeCards, int players) {
        return getWinRate(players) > holeCards.getWinRate(players);
    }

    public Double getWinRate(int players) {
        assert players >= 2 && players <= 9;
        File file = getFile(players);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine(); // read title line
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(" ");
                String holeCards = lineData[1];
                if (holeCards.equals(this.toString())) {
                    return Double.parseDouble(lineData[2]);
                }
            }
        } catch (IOException e) {
            System.out.println(String.format("Error reading from file %s.\nStack trace:", file.getName()));
            e.printStackTrace();
            System.exit(1);
        }
        assert false;
        return 0.0;
    }

    private static File getFile(int players) {
        return new File(String.format("%sPlayers", String.valueOf(players)));
    }

    @Override
    public String toString() {
        return cards.get(0).getRank().toString() +
                cards.get(1).getRank().toString() +
                (isSuited() ? "s" : "o");
    }
}
