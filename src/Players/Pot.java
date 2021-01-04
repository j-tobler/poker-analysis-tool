package Players;

import java.util.List;

public class Pot {

    int chips;

    public Pot() {
        chips = 0;
    }

    public void transferChips(Player player) {
        player.stack += chips;
        chips = 0;
    }

    public void collectBlinds(Player bigBlindPlayer, Player smallBlindPlayer, int[] blindsAmount) {
        bigBlindPlayer.transferChips(blindsAmount[0], this);
        smallBlindPlayer.transferChips(blindsAmount[1], this);

    }

    public void collectAntes(List<Player> players, int anteAmount) {
        players.forEach(player -> player.transferChips(anteAmount, this));
    }
}
