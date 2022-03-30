package Com.Scheduler;

import Com.Events.KothEnd;
import Com.FirecraftKoth;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class Timer {
    private FirecraftKoth instance;
    private Player player;
    private int time;
    private int taskID;


    public Timer(FirecraftKoth instance) {
        this.instance = instance;
    }

    public void setTimer(int amount) {
        time = amount;
    }

    public void startTimer(int amount) {
        time = amount;
        startTimer();
    }

    /**
     * Main Timer for Koth when a player is in control of Koth
     * Fires every 20 Tick
     */
    public void startTimer() {
        player = instance.getKoth().getPlayer();
        if(player == null)
            return;

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(instance, new Runnable() {
            @Override
            public void run() {
                ActionBarAPI.sendActionBar(player, setColor("&6&l[KOTH] &fSekunder tilbage: " + time  + ""));
                time = time - 1;
                if(time < 0) {
                    ActionBarAPI.sendActionBar(player, setColor("&6&l[KOTH] &fDu overtog Koth!"), 100);
                    TimerFinish();
                    return;
                }
            }
        }, 0L, 20L);
    }

    /**
     * Fires when the Capture timer is finish.
     * Calls an event so the plugin knows Koth has been captured.
     */
    private void TimerFinish() {
        stopTimer();
        Bukkit.getServer().getPluginManager().callEvent(new KothEnd(player));
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
    private String setColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
