package io.github.Dinner1111.ServerUtils;

import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CannoneerRunnable extends BukkitRunnable {
    Plugin plg;
    Player p;
    boolean isRunning;
    public CannoneerRunnable(Plugin pl, Player sender) {
        plg = pl;
        p = sender;
    }
    void setIsRunning(boolean b) { isRunning = b; }
    boolean getIsRunning() { return isRunning; }
    @Override
    public void run() {
        double pitch = ((p.getLocation().getPitch() + 90) * Math.PI) / 180, yaw  = ((p.getLocation().getYaw() + 90)  * Math.PI) / 180;
        double x = Math.sin(pitch) * Math.cos(yaw), y = Math.sin(pitch) * Math.sin(yaw), z = Math.cos(pitch);
        Vector vec = new Vector(x, z, y);
        Random r = new Random();
        int vel = r.nextInt(10);
        switch (vel) {
        case 6:
            pitch = pitch + 3;
            yaw = yaw + 3;
            break;
        case 7:
            pitch = pitch + 3;
            yaw = yaw - 3;
            break;
        case 8:
            pitch = pitch - 3;
            yaw = yaw + 3;
            break;
        case 9:
            pitch = pitch - 3;
            yaw = yaw - 3;
            break;
        case 10:
            pitch = pitch - 3;
            break;
        default:
            break;
        }
        for (int i = 1; i <= 3; i++)
        	p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT).setVelocity(vec.multiply(2));
    }
}