package io.github.Dinner1111.ServerUtils.Listeners;

import io.github.Dinner1111.ServerUtils.ChatThemes;
import io.github.Dinner1111.ServerUtils.ConfigMethods;
import io.github.Dinner1111.ServerUtils.ThemeColors;
import io.github.Dinner1111.ServerUtils.ChatThemes.ThemeType;
import io.github.Dinner1111.ServerUtils.Misc.SharedVariables;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Lists;

public class LeaveListener implements Listener {
	SharedVariables sv;
	ConfigMethods cm;
	Plugin plg;
	ChatThemes ct = new ChatThemes(plg);
	List<String> colors = Lists.newArrayList("AQUA", "BLACK", "BLUE", "BOLD", "DARK_AQUA", "DARK_BLUE","DARK_GRAY", "DARK_GREEN", "DARK_PURPLE", "DARK_RED", "GOLD", "GRAY", "GREEN", "ITALIC", "LIGHT_PURPLE", "MAGIC", "RED", "RESET", "STRIKETHROUGH", "UNDERLINE", "WHITE", "YELLOW");
	public LeaveListener(Plugin pl, SharedVariables s, ConfigMethods c) {
		plg = pl;
		sv = s;
		cm = c;
	}
	@EventHandler
	public void listener(PlayerQuitEvent e) {
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
        format += theme.color3 + " has left the game.";
		e.setQuitMessage(format);
	}
	@EventHandler
	public void kickListener(PlayerKickEvent e) {
		ThemeColors theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + e.getPlayer().getName() + ".theme")));
		e.setLeaveMessage(sv.getKicker().getDisplayName() + theme.color3 + " kicked " + e.getPlayer().getDisplayName() + theme.color4 + " - " + theme.color3 + e.getReason());
	}
}