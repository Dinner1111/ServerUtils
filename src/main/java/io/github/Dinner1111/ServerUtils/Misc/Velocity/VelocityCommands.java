package io.github.Dinner1111.ServerUtils.Misc.Velocity;

import io.github.Dinner1111.ServerUtils.PluginManager.Manager;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VelocityCommands implements CommandExecutor {
	Manager manager;
	public VelocityCommands(Manager m) {
		manager = m;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
		if (!manager.checkEnabled("velocity")) return true;
		if (cmd.getName().equalsIgnoreCase("vel")) {
			if (sender instanceof Player) {
				if (args.length == 4 && (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("mult"))) {
					double x, y, z;
					try {
						x = Integer.parseInt(args[1]);
						y = Integer.parseInt(args[2]);
						z = Integer.parseInt(args[3]);
					} catch (Exception e) {
						sender.sendMessage(ChatColor.RED + "Invalid arguments.");
						sender.sendMessage(ChatColor.RED + "Usage: /vel [set|add|mult] [x] [y] [z]");
						return true;
					}
					Vector vec = new Vector(x, y, z);
					if (args[0].equalsIgnoreCase("set")) {
						((Player) sender).setVelocity(vec);
						return true;
					} else if (args[0].equalsIgnoreCase("add")) {
						((Player) sender).setVelocity(((Player) sender).getVelocity().add(vec));
						return true;
					} else if (args[0].equalsIgnoreCase("mult")) {
						((Player) sender).setVelocity(((Player) sender).getVelocity().multiply(vec));
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Invalid arguments.");
						sender.sendMessage(ChatColor.RED + "Usage: /vel [set|add|mult] [x] [y] [z]");
						return true;
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Invalid arguments.");
					sender.sendMessage(ChatColor.RED + "Usage: /vel [set|add|mult] [x] [y] [z]");
					return true;
				}
			}
		}
		return false;
	}
}
