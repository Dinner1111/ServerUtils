package io.github.Dinner1111.ServerUtils.Listeners;

import io.github.Dinner1111.ChatThemes.ChatThemes;
import io.github.Dinner1111.ChatThemes.ThemeColors;
import io.github.Dinner1111.ChatThemes.ChatThemes.ThemeType;
import io.github.Dinner1111.ServerUtils.Startup;
import io.github.Dinner1111.ServerUtils.Misc.ConfigMethods;
import io.github.Dinner1111.ServerUtils.Misc.SharedVariables;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Lists;

public class MuteListener implements Listener {
	Plugin pl;
	SharedVariables sv;
	Startup start;
	ConfigMethods cm;
	ChatThemes ct = new ChatThemes(pl);
	Entity[] arrows = new Entity[49];
	int n = 0;
	List<String> colors = Lists.newArrayList("AQUA", "BLACK", "BLUE", "BOLD", "DARK_AQUA", "DARK_BLUE","DARK_GRAY", "DARK_GREEN", "DARK_PURPLE", "DARK_RED", "GOLD", "GRAY", "GREEN", "ITALIC", "LIGHT_PURPLE", "MAGIC", "RED", "RESET", "STRIKETHROUGH", "UNDERLINE", "WHITE", "YELLOW");
	public MuteListener(Plugin plg, SharedVariables shared, Startup s, ConfigMethods c) {
		pl = plg;
		sv = shared;
		start = s;
		cm = c;
	}
	@EventHandler
	public void playerFormat(AsyncPlayerChatEvent e) {
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
        if (!pl.getConfig().getConfigurationSection("worlds").getKeys(false).contains(e.getPlayer().getWorld().getName())) {
            pl.getConfig().set("worlds." + e.getPlayer().getWorld().getName() + ".alias", "null");
            pl.getConfig().set("worlds." + e.getPlayer().getWorld().getName() + ".color", "null");
        }
        ThemeColors theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + e.getPlayer().getName() + ".theme")));
        if (cm.getConfig().getBoolean("players." + e.getPlayer().getName() + ".is-broadcasting")) {
            if (cm.getConfig().getBoolean("players." + e.getPlayer().getName()+ ".broadcast-override")) {
                e.setFormat(theme.color4 + "[" + theme.color2 + "Broadcast" + theme.color4 + "] " + theme.color3 + e.getMessage());
            } else {
                e.setCancelled(true);
            }
        }
        if (cm.getConfig().getBoolean("players." + e.getPlayer().getName() + ".is-muted")) {
            if (!cm.getConfig().getBoolean("players." + e.getPlayer().getName() + ".mute-override")) {
                e.setCancelled(true);
            }
        }
        String format = "";
        format += theme.color4 + "[";
        if (colors.contains(pl.getConfig().getString("worlds." + e.getPlayer().getWorld().getName() + ".color"))) {
            format += ChatColor.valueOf(pl.getConfig().getString("worlds." + e.getPlayer().getWorld().getName() + ".color"));
        } else {
            format += theme.color3;
        }
        if (!pl.getConfig().getString("worlds." + e.getPlayer().getWorld().getName() + ".alias").equals("null")) {
            format += pl.getConfig().getString("worlds." + e.getPlayer().getWorld().getName() + ".alias");
        } else {
            format += e.getPlayer().getWorld().getName();
        }
        format += theme.color4 + "] ";
        try { format += ChatColor.valueOf(cm.getConfig().getString("players." + e.getPlayer().getName() + ".prefix-color")); } catch (Exception exc) {
            format += theme.color3;
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
            format += theme.color4;
        }
        format += e.getPlayer().getName() + theme.color3 + ": " + ChatColor.WHITE;
        Bukkit.broadcastMessage(format + e.getMessage());
		e.setCancelled(true);
	}
}