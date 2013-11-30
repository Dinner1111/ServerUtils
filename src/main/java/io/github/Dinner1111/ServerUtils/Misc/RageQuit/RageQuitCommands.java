package io.github.Dinner1111.ServerUtils.Misc.RageQuit;

import io.github.Dinner1111.ChatThemes.ChatThemes;
import io.github.Dinner1111.ChatThemes.ChatThemes.ThemeType;
import io.github.Dinner1111.ChatThemes.ThemeColors;
import io.github.Dinner1111.ServerUtils.PluginManager.Manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RageQuitCommands implements CommandExecutor {
	Plugin plg;
	Manager manager;
	public RageQuitCommands(Plugin pl, Manager m) {
		plg = pl;
		manager = m;
	}
	int task;
	int n = 0;
	ChatThemes ct = new ChatThemes(plg);
	ThemeColors theme = ct.ThemeColor(ThemeType.COOL_BLUE);
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
		if (!manager.checkEnabled("ragequit")) return true;
		if (cmd.getName().equalsIgnoreCase("rq")) {
			if (sender.hasPermission("RageQuit.Rage")) {
				if (sender instanceof Player) {
					if (args.length == 0) {
						Player p = (Player) sender;
						Bukkit.getServer().broadcastMessage(p.getDisplayName() + theme.color3 + " has " + theme.color1 + "rage quit" + theme.color3 + "!");
						p.kickPlayer(theme.color3 + "You " + theme.color1 + "rage quit" + theme.color3 + "!");
						Bukkit.getLogger().info(p.getDisplayName() + " rage quit. Hah.");
						return true;
					}
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("-c") && sender.hasPermission("RageQuit.ConsoleRage")) {
							consoleRQ();
							return true;
						} else {
							sender.sendMessage(theme.color3 + "Invalid flag.");
							sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/rq [-c]");
							return true;
						}
					} else {
						sender.sendMessage(theme.color3 + "Too many arguments.");
						sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/rq [-c]");
						return true;
					}
				} else {
					consoleRQ();
					return true;
				}
			}
		}
		return false;
	}
	public void consoleRQ() {
		task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
            public void run() {
                switch (n) {
                case 0:
                    Bukkit.getServer().broadcastMessage(theme.color3 + "Oh no! " + ChatColor.GOLD + "\u269D" + ChatColor.RED + "Console" + theme.color3 + " seems to be building up rage!");
                    n++;
                    break;
                case 1:
                	Bukkit.getServer().broadcastMessage(theme.color3 + "Oh my... " + ChatColor.GOLD + "\u269D" + ChatColor.RED + "Console" + theme.color3 + " is boiling with rage...");
                    n++;
                    break;
                case 2:
                	Bukkit.getServer().broadcastMessage(theme.color3 + "OH NO, EVERYONE RUN, " + ChatColor.GOLD + "\u269D" + ChatColor.RED + "Console" + theme.color3 + " IS GOING TO RAGE!!!");
                    n++;
                    break;
                case 3:
                	Bukkit.getServer().broadcastMessage(theme.color3 + "NOOOOOOOOO " + ChatColor.GOLD + "\u269D" + ChatColor.RED + "Console" + theme.color3 + " HAS RAGE QUIT!!!!!");
                    n++;
                    break;
                case 4:
                    for (Player p : Bukkit.getOnlinePlayers())
                        p.kickPlayer(ChatColor.GOLD + "\u269D" + ChatColor.DARK_RED + "Console " + theme.color3 + "has rage quit!");
                    Bukkit.getServer().shutdown();
                }
            }
        }, 0, 40);
	}
}