package Players;

public class Player {

    int stack;
    private int position;


    public Player(int position) {
        stack = 0;
        this.position = position;
    }

    public void transferChips(int amount, Pot pot) {
        pot.chips += amount;
        stack -= amount;
    }

    public void incrementPosition(int players) {
        position = (position + 1) % players;
    }
}
