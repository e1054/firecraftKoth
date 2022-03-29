package Com.Listener;

import Com.Events.KothCancel;
import Com.Events.KothStart;
import Com.FirecraftKoth;
import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class RegionHandler implements Listener {
    private FirecraftKoth instance;
    List<Player> playersInLine = new ArrayList<Player>();

    public RegionHandler(FirecraftKoth instance) {
        this.instance = instance;
    }


    @EventHandler
    public void onKothEnter(RegionEnterEvent event) {
        if (!event.getRegion().getId().equalsIgnoreCase("koth"))
            return;

        if (instance.getKoth().getPlayer() == null)
            Bukkit.getServer().getPluginManager().callEvent(new KothStart(event.getPlayer(), "test"));
         else
            playersInLine.add(event.getPlayer());



    }

    @EventHandler
    public void onKothLeave(RegionLeaveEvent event) {
        if (!event.getRegion().getId().equalsIgnoreCase("koth"))
            return;
        if (instance.getKoth().getPlayer() == event.getPlayer()) {
            Bukkit.getServer().getPluginManager().callEvent(new KothCancel(event.getPlayer()));

            if (playersInLine.size() < 1)
                return;

            Bukkit.getServer().getPluginManager().callEvent(new KothStart(playersInLine.get(0), "test"));
            playersInLine.remove(0);
        }
        else
            if(playersInLine.contains(event.getPlayer()))
                playersInLine.remove(event.getPlayer());
    }
}
