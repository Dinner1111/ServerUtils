package io.github.Dinner1111.ServerUtils;

import io.github.Dinner1111.ServerUtils.ChatThemes.ThemeType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class InventoryClickListener implements Listener {
	ConfigMethods cm;
	Plugin plg;
	ChatThemes ct = new ChatThemes(plg);
	public InventoryClickListener(Plugin pl, ConfigMethods c) {
		cm = c;
		plg = pl;
	}
	@EventHandler
	public void listen(InventoryClickEvent e) {
		ThemeColors theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + e.getWhoClicked().getName() + ".theme")));
		if (e.getCurrentItem().hasItemMeta()) {
			if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
				theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + e.getWhoClicked().getName() + ".theme")));
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "Op Online Players")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (!p.isOp()) {
							if (!cm.getConfig().getBoolean("players." + e.getWhoClicked().getName() + ".op-override")) {
								p.setOp(true);
							}
						} else {
							p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) e.getWhoClicked()).getDisplayName() + theme.color3 + " opped all online players.");
						}
					}
					((Player) e.getWhoClicked()).closeInventory();
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "List Online Ops")) {
					String format = theme.color3 + "Online operators: ";
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							format += p.getDisplayName() + ", ";
						}
					}
					format = format.substring(0, format.length() - 2);
					((Player) e.getWhoClicked()).closeInventory();
					((Player) e.getWhoClicked()).sendMessage(format);
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "Deop Online Players")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							if (!cm.getConfig().getBoolean("players." + e.getWhoClicked().getName() + ".deop-override")) {
								p.setOp(false);
							}
						}
					}
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							p.sendMessage(theme.color4 + "[" + theme.color3 + "ServerUtils" + theme.color4 + "] " + ((Player) e.getWhoClicked()).getDisplayName() + theme.color3 + " deopped all online players.");
						}
					}
					((Player) e.getWhoClicked()).closeInventory();
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "List Deopped Players")) {
					String format = theme.color3 + "Online operators: ";
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (!p.isOp()) {
							format += p.getDisplayName() + ", ";
						}
					}
					format = format.substring(0, format.length() - 2);
					((Player) e.getWhoClicked()).closeInventory();
					((Player) e.getWhoClicked()).sendMessage(format);
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "Unwhitelist Online Players")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (!p.isOp()) {
							p.setWhitelisted(false);
						} else {
							p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) e.getWhoClicked()).getDisplayName() + theme.color3 + " unwhitelisted all online players.");
						}
					}
					((Player) e.getWhoClicked()).closeInventory();
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "List Whitelisted Players")) {
					String format = theme.color3 + "Whitelisted players: ";
					for (Player p : Bukkit.getOnlinePlayers()) {
						format += p.getDisplayName() + ", ";
					}
					format = format.substring(0, format.length() - 2);
					((Player) e.getWhoClicked()).closeInventory();
					((Player) e.getWhoClicked()).sendMessage(format);
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "Kick Online Players")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (!p.isOp()) {
							p.kickPlayer(theme.color3 + "Kicked by an operator.");
						} else {
							p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) e.getWhoClicked()).getDisplayName() + theme.color3 + " kicked all online players.");
						}
					}
					((Player) e.getWhoClicked()).closeInventory();
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "Unwhitelist and Kick")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (!p.isOp()) {
							p.kickPlayer(theme.color3 + "Unwhitelisted and kicked by an operator.");
							p.setWhitelisted(false);
						} else {
							p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) e.getWhoClicked()).getDisplayName() + theme.color3 + " unwhitelisted and kicked all online players.");
						}
					}
					((Player) e.getWhoClicked()).closeInventory();
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + "Unwhitelist, Ban, and Kick")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (!p.isOp()) {
							p.kickPlayer(theme.color3 + "Kicked and banned by an operator.");
							p.setWhitelisted(false);
							p.setBanned(true);
						} else {
							p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) e.getWhoClicked()).getDisplayName() + theme.color3 + " unwhitelisted, banned, and kicked all online players.");
						}
					}
					((Player) e.getWhoClicked()).closeInventory();
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(theme.color3 + ""));
			}
		}
	}
}
