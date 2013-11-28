package io.github.Dinner1111.ServerUtils;

import io.github.Dinner1111.ServerUtils.Misc.ConfigMethods;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Lists;

public class Startup {
	Plugin plg;
	ConfigMethods cm;
	List<String> colors = Lists.newArrayList("AQUA", "BLACK", "BLUE", "BOLD", "DARK_AQUA", "DARK_BLUE","DARK_GRAY", "DARK_GREEN", "DARK_PURPLE", "DARK_RED", "GOLD", "GRAY", "GREEN", "ITALIC", "LIGHT_PURPLE", "MAGIC", "RED", "RESET", "STRIKETHROUGH", "UNDERLINE", "WHITE", "YELLOW");
	public Startup(Plugin pl, ConfigMethods c) {
		plg = pl;
		cm = c;
	}
	public void splitter(boolean admins, boolean ops, boolean builders, boolean guests) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (admins) {
				if (!(plg.getConfig().getStringList("admins") == null)) {
					if (plg.getConfig().getStringList("admins").contains(p.getName())) {
						this.admins(p);
					}
				}
			}
			if (ops) {
				if (!(plg.getConfig().getStringList("ops") == null)) {
					if (plg.getConfig().getStringList("ops").contains(p.getName())) {
						this.ops(p);
					}
				}
			}
			if (builders) {
				if (!(plg.getConfig().getStringList("builders") == null)) {
					if (plg.getConfig().getStringList("builders").contains(p.getName())) {
						this.builders(p);
					}
				}
			}
			if (guests) {
				if (!(plg.getConfig().getStringList("guests") == null)) {
					if (plg.getConfig().getStringList("guests").contains(p.getName())) {
						this.guests(p);
					}
				}
			}
		}
	}
	public void admins(Player p) {
		/*
		 * Admin Prefix: \u269D
		 * Builder Prefix: \u2692
		 * 
		 */
		switch (p.getName()) {
		case "dinner1111":
			p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName());
			if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".display-color"))) {
				cm.getConfig().set("players." + p.getName() + ".display-color", "LIGHT_PURPLE");
			}
			if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".prefix-color"))) {
				cm.getConfig().set("players." + p.getName() + ".prefix-color", "GOLD");
			}
			cm.getConfig().set("players." + p.getName() + ".prefix", "null");
			cm.getConfig().set("players." + p.getName() + ".op-override", false);
			cm.getConfig().set("players." + p.getName() + ".deop-override", true);
			cm.getConfig().set("players." + p.getName() + ".is-muted", false);
			cm.getConfig().set("players." + p.getName() + ".mute-override", true);
			cm.getConfig().set("players." + p.getName() + ".is-broadcasting", false);
			cm.getConfig().set("players." + p.getName() + ".broadcast-override", true);
			cm.getConfig().set("players." + p.getName() + ".group", "admin");
			cm.saveConfig();
			break;
		case "pepsidawg00":
			p.setPlayerListName(ChatColor.AQUA + p.getName());
			if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".display-color"))) {
				cm.getConfig().set("players." + p.getName() + ".display-color", "AQUA");
			}
			if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".prefix-color"))) {
				cm.getConfig().set("players." + p.getName() + ".prefix-color", "GOLD");
			}
			cm.getConfig().set("players." + p.getName() + ".prefix", "null");
			cm.getConfig().set("players." + p.getName() + ".op-override", false);
			cm.getConfig().set("players." + p.getName() + ".deop-override", true);
			cm.getConfig().set("players." + p.getName() + ".is-muted", false);
			cm.getConfig().set("players." + p.getName() + ".mute-override", true);
			cm.getConfig().set("players." + p.getName() + ".is-broadcasting", false);
			cm.getConfig().set("players." + p.getName() + ".broadcast-override", true);
			cm.getConfig().set("players." + p.getName() + ".group", "admin");
			cm.saveConfig();
			break;
		case "xannallax33":
			p.setPlayerListName(ChatColor.RED + p.getName());
			if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".display-color"))) {
				cm.getConfig().set("players." + p.getName() + ".display-color", "RED");
			}
			if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".prefix-color"))) {
				cm.getConfig().set("players." + p.getName() + ".prefix-color", "GOLD");
			}
			cm.getConfig().set("players." + p.getName() + ".prefix", "null");
			cm.getConfig().set("players." + p.getName() + ".op-override", false);
			cm.getConfig().set("players." + p.getName() + ".deop-override", true);
			cm.getConfig().set("players." + p.getName() + ".is-muted", false);
			cm.getConfig().set("players." + p.getName() + ".mute-override", true);
			cm.getConfig().set("players." + p.getName() + ".is-broadcasting", false);
			cm.getConfig().set("players." + p.getName() + ".broadcast-override", true);
			cm.getConfig().set("players." + p.getName() + ".group", "admin");
			cm.saveConfig();
			break;
		}
	}
	public void ops(Player p) {
		if (p.getName().length() > 14) {
			p.setPlayerListName(ChatColor.RED + p.getName().substring(0, p.getName().length() - 2));
		}
		if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".display-color"))) {
			cm.getConfig().set("players." + p.getName() + ".display-color", "RED");
		}
		if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".prefix-color"))) {
			cm.getConfig().set("players." + p.getName() + ".prefix-color", "DARK_RED");
		}
		cm.getConfig().set("players." + p.getName() + ".prefix", "null");
		cm.getConfig().set("players." + p.getName() + ".op-override", false);
		cm.getConfig().set("players." + p.getName() + ".deop-override", true);
		cm.getConfig().set("players." + p.getName() + ".is-muted", false);
		cm.getConfig().set("players." + p.getName() + ".mute-override", true);
		cm.getConfig().set("players." + p.getName() + ".is-broadcasting", false);
		cm.getConfig().set("players." + p.getName() + ".broadcast-override", true);
		cm.getConfig().set("players." + p.getName() + ".group", "op");
		cm.saveConfig();
	}
	public void builders(Player p) {
		if (p.getName().length() > 14) {
			p.setPlayerListName(ChatColor.BLUE + p.getName().substring(0, p.getName().length() - 2));
		}
		if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".display-color"))) {
			cm.getConfig().set("players." + p.getName() + ".display-color", "BLUE");
		}
		if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".prefix-color"))) {
			cm.getConfig().set("players." + p.getName() + ".prefix-color", "GRAY");
		}
		cm.getConfig().set("players." + p.getName() + ".prefix", "null");
		cm.getConfig().set("players." + p.getName() + ".op-override", false);
		cm.getConfig().set("players." + p.getName() + ".deop-override", false);
		cm.getConfig().set("players." + p.getName() + ".is-muted", false);
		cm.getConfig().set("players." + p.getName() + ".mute-override", false);
		cm.getConfig().set("players." + p.getName() + ".is-broadcasting", false);
		cm.getConfig().set("players." + p.getName() + ".broadcast-override", false);
		cm.getConfig().set("players." + p.getName() + ".group", "builder");
		cm.saveConfig();
	}
	public void guests(Player p) {
		if (p.getName().length() > 14) {
			p.setPlayerListName(ChatColor.DARK_GRAY + p.getName().substring(0, p.getName().length() - 2));
		}
		if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".display-color"))) {
			cm.getConfig().set("players." + p.getName() + ".display-color", "DARK_GRAY");
		}
		if (!colors.contains(cm.getConfig().getString("players." + p.getName() + ".prefix-color"))) {
			cm.getConfig().set("players." + p.getName() + ".prefix-color", "null");
		}
		cm.getConfig().set("players." + p.getName() + ".prefix", "null");
		cm.getConfig().set("players." + p.getName() + ".op-override", true);
		cm.getConfig().set("players." + p.getName() + ".deop-override", false);
		cm.getConfig().set("players." + p.getName() + ".is-muted", false);
		cm.getConfig().set("players." + p.getName() + ".mute-override", true);
		cm.getConfig().set("players." + p.getName() + ".is-broadcasting", false);
		cm.getConfig().set("players." + p.getName() + ".broadcast-override", true);
		cm.getConfig().set("players." + p.getName() + ".group", "guest");
		cm.saveConfig();
	}
}