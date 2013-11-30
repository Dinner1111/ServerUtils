package io.github.Dinner1111.ServerUtils.ProjectBuilder;

import java.util.logging.Level;

import io.github.Dinner1111.ChatThemes.ChatThemes;
import io.github.Dinner1111.ChatThemes.ChatThemes.ThemeType;
import io.github.Dinner1111.ChatThemes.ThemeColors;
import io.github.Dinner1111.ServerUtils.Misc.ConfigMethods;
import io.github.Dinner1111.ServerUtils.Misc.SharedVariables;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Scripts implements CommandExecutor {
	Plugin plg;
	SharedVariables sv;
	ScriptStorage sc;
	ChatThemes ct = new ChatThemes(plg);
	ConfigMethods cm;
	public CommandSender commandSender;
	public Scripts(Plugin pl, ConfigMethods c, SharedVariables s, ScriptStorage sc) {
		plg = pl;
		cm = c;
		sv = s;
		this.sc = sc;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
		ThemeColors theme;
		if (sender instanceof Player) {
			theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + ((Player) sender).getName() + ".theme")));
		} else {
			theme = ct.ThemeColor(ThemeType.COOL_BLUE);
		}
		if (cmd.getName().equalsIgnoreCase("script")) {
			if (args.length > 1) {
				if (args[0].equalsIgnoreCase("builder")) {
					if (args.length == 3) {
						switch (args[1].toLowerCase()) {
						case "create":
							if (sc.checkScriptName(args[2])) {
								sc.createScript(args[2]);
								sender.sendMessage(theme.color3 + "Script \'" + theme.color1 + args[2] + theme.color3 + "\' + created.");
								return true;
							} else {
								sender.sendMessage(theme.color3 + "Script \'" + theme.color1 + args[2] + theme.color3 + "\' + already exists.");
								return true;
							}
						case "save":
							sc.saveScript();
							sender.sendMessage(theme.color3 + "Scripts saved.");
							return true;
						default:
							sender.sendMessage(theme.color3 + "Invalid arguments");
							sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/script builder [create|command|sender|save|delete]");
							return true;
						}
					}
					if (args.length == 4) {
						switch (args[1].toLowerCase()) {
						case "delete":
							switch (args[2].toLowerCase()) {
							case "script":
								if (sc.deleteScript(args[3])) {
									sender.sendMessage(theme.color3 + "Script deleted.");
									return true;
								} else {
									sender.sendMessage(theme.color3 + "Script \'" + theme.color1 + args[3] + theme.color3 + "\' + does not exist.");
									return true;
								}
							case "command":
								sc.addScriptCommand(args[2], args[3]);
								sender.sendMessage(theme.color3 + "Command added.");
								return true;
							case "sender":
								if (sc.checkScriptSender(args[2])) {
									sc.setScriptSender(args[2], args[3]);
									sender.sendMessage(theme.color3 + "Script sender set.");
									return true;
								} else {
									return true;
								}
							default:
								sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/script builder [delete|command|sender]");
								return true;
							}
						default:
							sender.sendMessage(theme.color3 + "Invalid arguments.");
							sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/script builder [delete] [script]");
							return true;
						}
					}
					if (args.length == 5) {
						switch (args[1].toLowerCase()) {
						case "delete":
							switch (args[2].toLowerCase()) {
							case "command":
								if (sc.deleteCommand(args[3], args[4])) {
									sender.sendMessage(theme.color3 + "Command deleted.");
									return true;
								} else {
									sender.sendMessage(theme.color3 + "Command \'" + theme.color1 + args[4] + theme.color3 + "\' + does not exist.");
									return true;
								}
							default:
								sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/script builder delete [script] [command]");
								return true;
							}
						default:
							sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/script builder [delete] [script] [command]");
							return true;
						}
					}
					sender.sendMessage(theme.color3 + "Invalid arguments.");
					sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/script builder [create|command|sender|save|delete]");
					return true;
				} else {
					if (sc.checkScript(args[0])) {
						try { sc.runScript(args[0]); } catch (Exception e) {
							commandSender = sender;
							sv.setSender(commandSender);
							sender.sendMessage(theme.color3 + "Script could not be run.");
							Bukkit.getLogger().log(Level.SEVERE, "Could not run script " + args[0] + "!");
							return true;
						}
						sender.sendMessage(theme.color3 + "Script run.");
						return true;
					} else {
						sender.sendMessage(theme.color3 + "Cannot find script \'" + theme.color1 + args[0] + theme.color3 + ".\'");
						return true;
					}
				}
			}
		}
		return false;
	}
}