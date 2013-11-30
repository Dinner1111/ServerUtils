package io.github.Dinner1111.ServerUtils.Misc.Whitelister;

import io.github.Dinner1111.ServerUtils.PluginManager.Manager;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Lists;

public class Whitelister implements Listener {
	Plugin plg;
	Manager manager;
	public Whitelister(Plugin pl, Manager m) {
		plg = pl;
		manager = m;
	}
	@EventHandler
	public void listen(PlayerLoginEvent e) {
		if (!manager.checkEnabled("whitelister")) return;
		List<String> whitelistees = Lists.newArrayList();
		try { whitelistees = plg.getConfig().getStringList("whitelisted"); } catch (Exception ex) { }
		if (!whitelistees.contains(e.getPlayer().getName()))
			whitelistees.add(e.getPlayer().getName());
		plg.getConfig().set("whitelisted", whitelistees);
	}
}
