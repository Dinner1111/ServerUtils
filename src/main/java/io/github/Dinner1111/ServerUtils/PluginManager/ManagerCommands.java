package io.github.Dinner1111.ServerUtils.PluginManager;

import io.github.Dinner1111.ChatThemes.ChatThemes;
import io.github.Dinner1111.ChatThemes.ThemeColors;
import io.github.Dinner1111.ChatThemes.ChatThemes.ThemeType;
import io.github.Dinner1111.ServerUtils.Misc.ConfigMethods;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ManagerCommands implements CommandExecutor {
	Manager manager;
	Plugin plg;
	ConfigMethods cm;
	ChatThemes ct = new ChatThemes(plg);
	public ManagerCommands(Plugin pl, ConfigMethods c, Manager m) {
		plg = pl;
		manager = m;
		cm = c;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
		ThemeColors theme;
		if (sender instanceof Player) {
			theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + ((Player) sender).getName() + ".theme")));
		} else {
			theme = ct.ThemeColor(ThemeType.COOL_BLUE);
		}
		if (cmd.getName().equalsIgnoreCase("manager")) {
			if (args.length == 2) {
				if (manager.checkPluginName(args[1])) {
					if (args[0].equalsIgnoreCase("enable")) {
						if (!manager.checkEnabled(args[1])) {
							manager.enablePlugin(args[1]);
							sender.sendMessage(theme.color3 + "Plugin enabled.");
						} else {
							sender.sendMessage(theme.color3 + "The plugin is already enabled.");
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("disable")) {
						if (!manager.checkEnabled(args[1])) {
							manager.disablePlugin(args[1]);
							sender.sendMessage(theme.color3 + "Plugin disabled.");
						} else {
							sender.sendMessage(theme.color3 + "The plugin is already disabled.");
						}
						return true;
					} 
					if (args[0].equalsIgnoreCase("check")) {
						if (manager.checkEnabled(args[1])) sender.sendMessage(theme.color3 + "The plugin is enabled.");
						else sender.sendMessage(theme.color3 + "The plugin is not enabled.");
						return true;
					}
				} else {
					sender.sendMessage(theme.color3 + "Invalid plugin.");
					return true;
				}
			} else {
				sender.sendMessage(theme.color3 + "Too many arguments.");
				sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/manager [enable|disable|check] [name]");
				return true;
			}
		}
		return false;
	}
}