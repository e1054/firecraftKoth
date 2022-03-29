package Com.Koth;

import org.bukkit.entity.Player;

public class Koth {

    private Player player;
    private String faction;
    private boolean canBeTakenOver;

    public Koth() {
        player = null;
        faction = null;
        canBeTakenOver = true;
    }
    public Koth(Player player, String faction, Boolean canBeTakenOver) {
        this.player = player;
        this.faction = faction;
        this.canBeTakenOver = canBeTakenOver;
    }

    public void resetKoth() {
        player = null;
        faction = null;
        canBeTakenOver = false;
    }

    public void cancelKoth() {
        player = null;
        faction = null;
        canBeTakenOver = true;
    }



    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public Boolean getCanBeTakenOver() {
        return canBeTakenOver;
    }

    public void setCanBeTakenOver(Boolean temp) {
        this.canBeTakenOver = temp;
    }
}
