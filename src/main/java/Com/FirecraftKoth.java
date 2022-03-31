package Com;

import Com.Koth.Koth;
import Com.Listener.KothHandler;
import Com.Listener.RegionHandler;
import Com.Scheduler.KothCooldown;
import Com.Scheduler.Timer;
import Com.Utils.FireWork;
import Com.Utils.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class FirecraftKoth extends JavaPlugin {
    private Koth koth;
    private Timer timer;
    private KothCooldown kothCooldown;
    private FireWork fireWork;
    private Hologram hologram;


    @Override
    public void onEnable() {
        hologram.removeKothHologram();
        koth = new Koth();
        timer = new Timer(this);
        kothCooldown = new KothCooldown( this);
        fireWork = new FireWork();
        hologram = new Hologram();

        Bukkit.getServer().getPluginManager().registerEvents(new KothHandler(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RegionHandler(this), this);

        hologram.makeKothHologram(new Location(getServer().getWorld(getConfig().getString("Koth.Hologram.World")), getConfig().getInt("Koth.Hologram.X"), getConfig().getInt("Koth.Hologram.Y"), getConfig().getInt("Koth.Hologram.Z")));

        this.saveDefaultConfig();

    }

    @Override
    public Koth getKoth() {return koth;}
    public Timer getTimer() {return timer;}
    public KothCooldown getKothCooldown() {return kothCooldown;}
    public FireWork getFireWork() {return fireWork;}
    public Hologram getHologram() {return hologram;}
}
