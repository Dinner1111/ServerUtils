package io.github.Dinner1111.ServerUtils;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class BlockListener implements Listener {
	Plugin pl;
	
	public BlockListener(Plugin plg) {
		pl = plg;
	}
	@EventHandler
	public void listen(BlockBreakEvent e) {
		if (!pl.getConfig().getStringList("worlds." + e.getBlock().getWorld().getName() + ".owners").contains(e.getPlayer()) && !e.getPlayer().isOp()) {
			if (pl.getConfig().getStringList("worlds." + e.getBlock().getWorld().getName() + ".owners").size() != 0) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "This world is protected.");
			}
		}
	}
	@EventHandler
	public void placeListen(BlockPlaceEvent e) {
		if (!pl.getConfig().getStringList("worlds." + e.getBlock().getWorld().getName() + ".owners").contains(e.getPlayer()) && !e.getPlayer().isOp()) {
			if (pl.getConfig().getStringList("worlds." + e.getBlock().getWorld().getName() + ".owners").size() != 0) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "This world is protected.");
			}
		}
	}
}
