package io.github.Dinner1111.ServerUtils;

import io.github.Dinner1111.ServerUtils.ChatThemes.ThemeType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Threads extends BukkitRunnable {
	Plugin plg;
	ChatThemes ct = new ChatThemes(plg);
	ConfigMethods cm;
	public Threads(Plugin pl, ConfigMethods c) {
		plg = pl;
		cm = c;
	}
	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			ThemeColors theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + p.getName() + ".theme")));
			p.sendMessage(theme.color4 + "[" + theme.color2 + "Announcement" + theme.color4 + "] " + theme.color3 + plg.getConfig().getString("announcement.message"));
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Announcement" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + plg.getConfig().getString("announcement.message"));
	}
}