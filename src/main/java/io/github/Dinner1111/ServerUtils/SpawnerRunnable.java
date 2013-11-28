package io.github.Dinner1111.ServerUtils;

import java.util.Map;

import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnerRunnable extends BukkitRunnable {
	Map<String, Map<EntityType, Double>> spawns;
	@Override
	public void run() {
		
	}
	public void setMap(Map<String, Map<EntityType, Double>> map) { spawns = map; }
}
