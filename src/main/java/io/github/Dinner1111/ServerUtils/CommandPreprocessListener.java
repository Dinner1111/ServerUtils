package io.github.Dinner1111.ServerUtils;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Lists;

public class CommandPreprocessListener implements Listener {
	Plugin plg;
	SharedVariables sv;
	Startup start;
	public CommandPreprocessListener(Plugin pl, SharedVariables s, Startup st) {
		plg = pl;
		sv = s;
		start = st;
	}
	@EventHandler
	public void commandListener(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().startsWith("/op")) {
			if (e.getMessage().contains(" ")) {
				String[] s = e.getMessage().split(" ");
				if (s[0].equalsIgnoreCase("/op")) {
					if (!(plg.getConfig().getStringList("ops") == null)) {
						List<String> ops = Lists.newArrayList(plg.getConfig().getStringList("ops"));
						ops.add(s[1]);
						plg.getConfig().set("ops", ops);
						start.splitter(false, true, false, false);
					}
				}
			}
		}
		if (e.getMessage().startsWith("/kick")) {
			sv.setKicker(e.getPlayer());
		}
	}
}
