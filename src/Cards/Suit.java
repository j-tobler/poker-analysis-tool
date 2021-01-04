package Cards;

public enum Suit {
    CLUBS       ('c'),
    HEARTS      ('h'),
    SPADES      ('s'),
    DIAMONDS    ('d');

    private char symbol;

    Suit(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
