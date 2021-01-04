package Cards;

public enum Rank {
    ACE     ('A', 14),
    KING    ('K', 13),
    QUEEN   ('Q', 12),
    JACK    ('J', 11),
    TEN     ('T', 10),
    NINE    ('9', 9),
    EIGHT   ('8', 8),
    SEVEN   ('7', 7),
    SIX     ('6', 6),
    FIVE    ('5', 5),
    FOUR    ('4', 4),
    TRE     ('3', 3),
    DEUCE   ('2', 2);

    private char symbol;
    private int value;

    Rank(char symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
