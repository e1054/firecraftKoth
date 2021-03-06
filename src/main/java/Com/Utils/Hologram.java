package Com.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;


public class Hologram {
    private ArmorStand hologramTitle;
    private ArmorStand hologram1;
    private ArmorStand hologram2;
    private ArmorStand hologram3;

    /**
     * Makes a set Hologram at a specific location.
     * The Hologram will display the Cooldown for Koth.
     * @param loc The location of the Hologram
     */
    public void makeKothHologram(Location loc) {
        // Title
        World world = loc.getWorld();
        hologramTitle = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
        hologramTitle.setVisible(false);
        hologramTitle.setGravity(false);
        hologramTitle.setCustomNameVisible(true);
        hologramTitle.setCustomName(setColor("&6&lKOTH"));
        //First line
        hologram1 = (ArmorStand) world.spawnEntity(loc.add(0, -0.5, 0), EntityType.ARMOR_STAND);
        hologram1.setVisible(false);
        hologram1.setGravity(false);
        hologram1.setCustomNameVisible(true);
        hologram1.setCustomName(setColor("&8&m-|---------------|-|---------------|-"));
        //Second line
        hologram2 = (ArmorStand) world.spawnEntity(loc.add(0, -0.5, 0), EntityType.ARMOR_STAND);
        hologram2.setVisible(false);
        hologram2.setGravity(false);
        hologram2.setCustomNameVisible(true);
        hologram2.setCustomName(setColor("&fKoth kan overtages &6Nu&f."));
        // Third line
        hologram3 = (ArmorStand) world.spawnEntity(loc.add(0, -0.5, 0), EntityType.ARMOR_STAND);
        hologram3.setVisible(false);
        hologram3.setGravity(false);
        hologram3.setCustomNameVisible(true);
        hologram3.setCustomName(setColor("&8&m-|---------------|-|---------------|-"));
    }

    /**
     * Will remove the Hologram made by makeKothHologram()
     * This method will run before the plugin shutsdown.
     */
    public void removeKothHologram() {
        //Removes all holograms
        for (Hologram hologram : HologramsAPI.getHolograms(this)) hologram.delete();
    }

    /**
     * Changes the timer on the second line of the Hologram to the amount of time ontil Koth can be Captured.
     * @param time The amount of minutes
     */
    public void setKothTime(int time) {
        if (time > 0)
            hologram2.setCustomName(setColor("&fKoth kan overtages igen om &6" + time + " Minutter&f."));
        else
            hologram2.setCustomName(setColor("&fKoth kan overtages &6Nu&f."));
    }


    private String setColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
