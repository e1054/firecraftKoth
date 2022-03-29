package Com.Listener;

import Com.Events.KothCancel;
import Com.Events.KothEnd;
import Com.Events.KothStart;
import Com.FirecraftKoth;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class KothHandler implements Listener {
    private FirecraftKoth instance;

    public KothHandler(FirecraftKoth instance) {
        this.instance = instance;
    }

    @EventHandler
    public void kothStart(KothStart event) {
        if (!instance.getConfig().getBoolean("Koth.Enable")) {
            event.getPlayer().sendMessage(setColor("&6&l[KOTH] &cKoth er midlertidigt slået fra!"));
            return;
        }
        if (instance.getKoth().getFaction() != null || instance.getKoth().getPlayer() != null)
            return;
        if (!instance.getKoth().getCanBeTakenOver())
            return;
        instance.getKoth().setPlayer(event.getPlayer());
        instance.getKoth().setFaction(event.getFaction());
        instance.getKoth().setCanBeTakenOver(false);
        instance.getTimer().startTimer(instance.getConfig().getInt("Koth.Capture-Time"));
        if (instance.getConfig().getBoolean("Koth.Broadcast.Start")) {
            if (event.getFaction() == null)
                Bukkit.getServer().broadcastMessage(setColor("&6&l[KOTH] &e" + event.getPlayer().getName() + " &fprøver at overtage koth i spawn."));
            else
                Bukkit.getServer().broadcastMessage(setColor("&6&l[KOTH] &e" + event.getPlayer().getName() + " &ffra &e" + event.getFaction() + " &fprøver at overtage koth i spawn."));
        }
    }

    @EventHandler
    public void kothEnd(KothEnd event) {
        if (!event.getPlayer().equals(instance.getKoth().getPlayer()))
            return;
        event.getPlayer().sendMessage(setColor("&6&l[KOTH] &fDu gennemførte din overtagelse af Koth!"));
        instance.getFireWork().createFirework(event.getPlayer().getLocation(), event.getPlayer().getWorld());
        instance.getKoth().resetKoth();
        instance.getKothCooldown().startTimer(instance.getConfig().getInt("Koth.Cooldown-Time"));
        RewardHandler(event.getPlayer());
        if (instance.getConfig().getBoolean("Koth.Broadcast.End")) {
            Bukkit.getServer().broadcastMessage(setColor("&6&l[KOTH] &e" + event.getPlayer().getName() + " &fhar overtaget koth i spawn."));
        }
    }

    @EventHandler
    public void kothCancel(KothCancel event) {
        if (!event.getPlayer().equals(instance.getKoth().getPlayer()))
            return;
        event.getPlayer().sendMessage(setColor("&6&l[KOTH] &fDu fejlede din overtagelse af koth!"));
        instance.getKoth().cancelKoth();
        instance.getTimer().stopTimer();
        if (instance.getConfig().getBoolean("Koth.Broadcast.Cancel")) {
            Bukkit.getServer().broadcastMessage(setColor("&6&l[KOTH] &e" + event.getPlayer().getName() + " &ffejlede overtagelsen af koth i spawn."));
        }
    }

    private void RewardHandler(Player player) {
        try {
            ItemStack drops = new ItemStack(Material.valueOf(instance.getConfig().getString("Koth.Reward")), 1);
            player.getInventory().addItem(drops);
        } catch (Exception e) {}

    }

    private String setColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
