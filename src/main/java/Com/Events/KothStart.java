package Com.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KothStart extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private String faction;
    private boolean cancelled;

    public KothStart(Player player, String faction) {
        this.player = player;
        this.faction = faction;
    }

    public Player getPlayer() {
        return player;
    }

    public String getFaction() {
        return faction;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
