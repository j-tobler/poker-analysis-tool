import Players.Player;
import Players.Pot;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // initialise
        int numPlayers = getInitialPlayers();
        int stacks = getInitialStacks();
        int[] blinds = getBlinds();
        int position = getInitialPosition(numPlayers);
        Pot pot = new Pot();
        List<Player> players = new ArrayList<>(numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            // players[0] is our player, players[1] is the player to our left, and so on
            players.add(new Player((position + i) % numPlayers));
        }
        int buttonIndex = (-position) % numPlayers;
        Player buttonPlayer = players.get(buttonIndex);
        Player bigBlindPlayer = players.get((buttonIndex + 1) % numPlayers);
        Player smallBlindPlayer = players.get((buttonIndex + 2) % numPlayers);

        // run
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type 'y' to change blinds.");
            if (scanner.next().equals("y")) {
                blinds = getBlinds();
            }


            // post blinds
            System.out.println("Press return to maintain blinds");
            if (!scanner.next().equals("")) {
                blinds = getBlinds();
            }

            pot.collectBlinds(smallBlindPlayer, bigBlindPlayer, blinds);
        }
    }

    private static int getInitialPlayers() {
        System.out.println("Enter the number of players (2-9)");
        while (true) {
            int players = getInteger();
            if (players > 1 && players < 10) {
                return players;
            }
            System.out.println("Number of players must exist in the range 2-9");
        }
    }

    private static int getInitialStacks() {
        System.out.println("Enter stack sizes");
        return getInteger();
    }

    private static int[] getBlinds() {
        int[] blinds = new int[2];
        System.out.println("Enter big blind amount\n> ");
        blinds[0] = getInteger();
        System.out.println("Enter small blind amount\n> ");
        blinds[1] = getInteger();
        return blinds;
    }

    private static int getInitialPosition(int players) {
        System.out.println("Enter initial position (positions start at 0)");
        while (true) {
            int position = getInteger();
            if (position >= 0 && position < players) {
                return position;
            }
            if (position < 0) {
                System.out.println("Initial position must be a positive integer");
            } else {
                System.out.println("Initial position must not exceed the number of players - 1");
            }
        }
    }
    
    private static int getInteger() {
        int num;
        while (true) {
            try (Scanner scanner = new Scanner(System.in)) {
                num = Integer.parseInt(scanner.nextLine());
                System.out.println();
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer");
            }
        }
    }
}
