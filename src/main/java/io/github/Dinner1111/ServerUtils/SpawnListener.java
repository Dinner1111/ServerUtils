package io.github.Dinner1111.ServerUtils;
 
import java.util.List;
 
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
 
import com.google.common.collect.Lists;
 
public class SpawnListener implements Listener {
    Plugin plg;
    List<EntityType> aggressive = Lists.newArrayList(EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.GHAST, EntityType.GIANT, EntityType.MAGMA_CUBE, EntityType.PIG_ZOMBIE, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.WITCH, EntityType.WITHER, EntityType.ZOMBIE);
    List<EntityType> passive = Lists.newArrayList(EntityType.BAT, EntityType.CHICKEN, EntityType.COW, EntityType.HORSE, EntityType.IRON_GOLEM, EntityType.OCELOT, EntityType.PIG, EntityType.SHEEP, EntityType.SNOWMAN, EntityType.SQUID, EntityType.VILLAGER);
    public SpawnListener(Plugin pl) {
        plg = pl;
    }
    @EventHandler
    public void listen(CreatureSpawnEvent e) {
        String worldName = e.getLocation().getWorld().getName();
        if (plg.getConfig().getString("worlds." + worldName) != null) {
            if (plg.getConfig().getBoolean("worlds." + worldName + ".mobs.monsters")) {
            	if (plg.getConfig().getString("worlds." + worldName + ".mobs.monsters") != null) {
                    if (aggressive.contains(e.getEntityType())) {
                        e.setCancelled(true);
                    }
                }
            }
            if (plg.getConfig().getString("worlds." + worldName + ".mobs.animals") != null) {
                if (!plg.getConfig().getBoolean("worlds." + e.getLocation().getWorld().getName() + ".mobs.animals")) {
                    if (passive.contains(e.getEntityType())) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}