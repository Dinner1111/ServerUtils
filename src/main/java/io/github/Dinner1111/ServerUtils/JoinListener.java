package io.github.Dinner1111.ServerUtils;

import io.github.Dinner1111.ServerUtils.ChatThemes.ThemeType;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Lists;

public class JoinListener implements Listener {
	Plugin plg;
	Startup start;
	ConfigMethods cm;
	ChatThemes ct = new ChatThemes(plg);
	List<String> colors = Lists.newArrayList("AQUA", "BLACK", "BLUE", "BOLD", "DARK_AQUA", "DARK_BLUE","DARK_GRAY", "DARK_GREEN", "DARK_PURPLE", "DARK_RED", "GOLD", "GRAY", "GREEN", "ITALIC", "LIGHT_PURPLE", "MAGIC", "RED", "RESET", "STRIKETHROUGH", "UNDERLINE", "WHITE", "YELLOW");
	public JoinListener(Plugin pl, Startup s, ConfigMethods c) {
		plg = pl;
		start = s;
		cm = c;
	}
	@EventHandler
	public void listen(PlayerJoinEvent e) {
		if (!cm.getConfig().getConfigurationSection("players").getKeys(false).contains(e.getPlayer().getName())) {
			cm.getConfig().set("players." + e.getPlayer().getName() + ".display-color", "DARK_GRAY");
			cm.getConfig().set("players." + e.getPlayer().getName() + ".theme", "COOL_BLUE");
			cm.getConfig().set("players." + e.getPlayer().getName() + ".prefix", "null");
			cm.getConfig().set("players." + e.getPlayer().getName() + ".prefix-color", "null");
			cm.getConfig().set("players." + e.getPlayer().getName() + ".op-override", true);
			cm.getConfig().set("players." + e.getPlayer().getName() + ".deop-override", false);
			cm.getConfig().set("players." + e.getPlayer().getName() + ".is-muted", false);
			cm.getConfig().set("players." + e.getPlayer().getName() + ".mute-override", false);
			cm.getConfig().set("players." + e.getPlayer().getName() + ".is-broadcasting", false);
			cm.getConfig().set("players." + e.getPlayer().getName() + ".broadcast-override", false);
			cm.getConfig().set("players." + e.getPlayer().getName() + ".group", "guest");
			cm.saveConfig();
		}
		ThemeColors theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + e.getPlayer().getName() + ".theme")));
		String format = "";
		if (colors.contains(cm.getConfig().getString("players." + e.getPlayer().getName() + ".prefix-color"))) {
			format += ChatColor.valueOf(cm.getConfig().getString("players." + e.getPlayer().getName() + ".prefix-color"));
		} else {
			format += ChatColor.DARK_GRAY;
		}
		if (!cm.getConfig().getString("players." + e.getPlayer().getName() + ".prefix").equals("null")) {
			format += cm.getConfig().getString("players." + e.getPlayer().getName() + ".prefix") + " ";
		}
		if (cm.getConfig().getString("players." + e.getPlayer().getName() + ".group").equals("admin")) {
			format += ChatColor.GOLD + "\u269D";
		} else if (cm.getConfig().getString("players." + e.getPlayer().getName() + ".group").equals("op")) {
			format += ChatColor.DARK_RED + "\u269D";
		} else if (cm.getConfig().getString("players." + e.getPlayer().getName() + ".group").equals("builder")) {
			format += ChatColor.GRAY + "\u2692";
		}
		if (colors.contains(cm.getConfig().getString("players." + e.getPlayer().getName() + ".display-color"))) {
			format += ChatColor.valueOf(cm.getConfig().getString("players." + e.getPlayer().getName() + ".display-color"));
		} else {
			format += ChatColor.DARK_GRAY;
		}
		format += e.getPlayer().getName();
		e.getPlayer().setDisplayName(format);
		e.getPlayer().setCustomName(format);
        format += theme.color3 + " has joined the game.";
		e.setJoinMessage(format);
		if (!(plg.getConfig().getStringList("hiddenPlayers") == null)) {
			List<String> hidden = plg.getConfig().getStringList("hiddenPlayers");
			for (String s : hidden) {
				if (s != null) {
					if (Bukkit.getOfflinePlayer(s).isOnline()) {
						e.getPlayer().hidePlayer(Bukkit.getPlayer(s));
					}
				}
			}
		}
	}
}