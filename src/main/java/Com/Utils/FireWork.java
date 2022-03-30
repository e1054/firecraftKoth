package Com.Utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireWork {
    /**
     * Makes a set Firewaork that spawns above a location the a world.
     * The Firework will be Red, Yellow and Orange.
     * @param loc The location to fire the Firework
     * @param world The World to fire the firework
     */
    public void createFirework(Location loc, World world) {
        Firework fw = world.spawn(loc, Firework.class);
        FireworkMeta ftm = (FireworkMeta) fw.getFireworkMeta();
        ftm.addEffects(FireworkEffect.builder().withColor(Color.RED).withColor(Color.YELLOW).withColor(Color.ORANGE).with(FireworkEffect.Type.BALL).withFlicker().build());
        ftm.setPower(1);
        fw.setFireworkMeta(ftm);

    }
}
