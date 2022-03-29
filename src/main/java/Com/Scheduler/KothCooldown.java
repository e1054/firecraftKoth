package Com.Scheduler;

import Com.Events.KothEnd;
import Com.FirecraftKoth;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class KothCooldown {
    private FirecraftKoth instance;
    private Player player;
    private int time;
    private int taskID;

    private int cooldown;


    public KothCooldown(FirecraftKoth instance) {
        this.instance = instance;
    }

    public void setTimer(int amount) {
        time = amount+1;
    }

    public void startTimer(int amount) {
        time = amount+1;
        startTimer();
    }

    /**
     * Main cooldown for Koth
     * Fires every minut
     */
    public void startTimer() {
        player = instance.getKoth().getPlayer();
        if(player != null)
            return;

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(instance, new Runnable() {
            @Override
            public void run() {
                time = time - 1;
                instance.getHologram().setKothTime(time);
                if(time <= 0) {
                    TimerFinish();
                    return;
                }
            }
        }, 0L,60*20L);
    }
    private void TimerFinish() {
        stopTimer();
        instance.getKoth().setCanBeTakenOver(true);
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
    private String setColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
