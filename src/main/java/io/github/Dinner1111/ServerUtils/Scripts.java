package io.github.Dinner1111.ServerUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Scripts implements CommandExecutor {
	Plugin plg;
	public Scripts(Plugin pl) {
		plg = pl;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
		if (cmd.getName().equalsIgnoreCase("script")) {
			if (args.length == 1) {
				if (sender instanceof Player) {
					if (args[0].equalsIgnoreCase("world")) {
						Bukkit.dispatchCommand(sender, "mvm set animals false");
						Bukkit.dispatchCommand(sender, "mvm set monsters false");
						Bukkit.dispatchCommand(sender, "mvm set weather false");
						Bukkit.dispatchCommand(sender, "mvm set mode 1");
						Bukkit.dispatchCommand(sender, "butcher -a");
						Bukkit.dispatchCommand(sender, "time day -l");
					}
				} else {
					sender.sendMessage(ChatColor.GRAY + "You must be in a world to use this script.");
					return true;
				}
			}
		}
		return false;
	}
}
