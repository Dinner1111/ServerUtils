package io.github.Dinner1111.ServerUtils.Misc.BetterCreepers;

import io.github.Dinner1111.ServerUtils.PluginManager.Manager;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class ExplosionListener implements Listener {
	Manager manager;
	public ExplosionListener(Manager m) {
		manager = m;
	}
    @EventHandler
    public void spawnFirework(EntityExplodeEvent e) {
    	if (!manager.checkEnabled("bettercreepers")) return;
        if (e.getEntity() instanceof Creeper) {
            Entity c = e.getEntity();
            Firework f = (Firework) c.getWorld().spawnEntity(c.getLocation(), EntityType.FIREWORK);
            FireworkMeta fMeta = f.getFireworkMeta();
            fMeta.setPower(1);
            fMeta.addEffect(FireworkEffect.builder() 
                    .with(Type.CREEPER)
                    .withColor(Color.LIME, Color.AQUA, Color.GREEN)
                    .build());
            f.setFireworkMeta(fMeta);
        }
    }
}