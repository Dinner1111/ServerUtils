package io.github.Dinner1111.ServerUtils.Misc;

import io.github.Dinner1111.ServerUtils.Startup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CaseManager {
	Startup start;
	Plugin plg;
	public CaseManager(Startup s, Plugin pl) {
		start = s;
		plg = pl;
	}
	public void reloadCustomDisplayNames(String[] args) {
		switch (args[0].toLowerCase()) {
		case "-n":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading custom display names...");
				}
			}
			start.splitter(true, true, true, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		case "-n--a":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading admin display names...");
				}
			}
			start.splitter(true, false, false, false);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins");
				}
			}
			break;
		case "-n--o":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading operator display names...");
				}
			}
			start.splitter(false, true, false, false);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops");
				}
			}
			break;
		case "-n--b":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading builder display names...");
				}
			}
			start.splitter(false, false, true, false);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders");
				}
			}
			break;
		case "-n--g":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading guest display names...");
				}
			}
			start.splitter(false, false, false, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		case "-n--ao":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading admin and operator display names...");
				}
			}
			start.splitter(true, true, false, false);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops");
				}
			}
			break;
		case "-n--ab":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading admin and builder display names...");
				}
			}
			start.splitter(true, false, true, false);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders");
				}
			}
			break;
		case "-n--ag":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading admin and guest display names...");
				}
			}
			start.splitter(true, false, false, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		case "-n--ob":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading operator and builder display names...");
				}
			}
			start.splitter(false, true, true, false);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders");
				}
			}
			break;
		case "-n--og":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading operator and builder display names...");
				}
			}
			start.splitter(false, true, false, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		case "-n--bg":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading builder and guest display names...");
				}
			}
			start.splitter(false, false, true, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		case "-n--aob":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading admin, operator, and builder display names...");
				}
			}
			start.splitter(true, true, true, false);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders");
				}
			}
			break;
		case "-n--aog":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading admin, operator, and guest display names...");
				}
			}
			start.splitter(true, true, false, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		case "-n--abg":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading admin, builder, and guest display names...");
				}
			}
			start.splitter(true, false, true, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		case "-n--aobg":
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading custom display names...");
				}
			}
			start.splitter(true, true, true, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		default:
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reloading custom display names...");
				}
			}
			start.splitter(true, true, true, true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Reload complete. Items reloaded:"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Admins"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Ops"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Builders"
							+ ChatColor.DARK_GRAY + "\n   - " + ChatColor.AQUA + "Splitter" + ChatColor.DARK_GRAY + "." + ChatColor.AQUA + "Guests");
				}
			}
			break;
		}
	}
	public void displayConfigLists(String[] args, Player p) {
		try { plg.getConfig().get(args[1]); } catch (Exception e) { 
			return;
		}
		String s = ChatColor.AQUA + plg.getConfig().get(args[1]).toString();
		if (s.contains("[") || s.contains("]")) {
			s = s.replace("[", ChatColor.DARK_GRAY + "[" + ChatColor.AQUA).replace("]", ChatColor.DARK_GRAY + "]" + ChatColor.AQUA);
		}
		if (s.contains(",")) {
			s = s.replace(",", ChatColor.GRAY + "," + ChatColor.AQUA);
		}
		if (s.contains("{") || s.contains("}")) {
			s = s.replace("{", ChatColor.DARK_GRAY + "{" + ChatColor.AQUA).replace("}", ChatColor.DARK_GRAY + "}" + ChatColor.AQUA);
		}
		String[] players = s.split(", ");
		if (players[0].contains("[")) {
			players[0].replace("[", "");
		}
		if (players[players.length - 1].contains("]")) {
			players[players.length - 1].replace("]", "");
		}
		for (int i = 0; i < (plg.getConfig().getConfigurationSection(args[1]).getKeys(true).size() - 1); i++) {
			if (plg.getConfig().getConfigurationSection(args[1]).getKeys(true).contains(players[i])) {
				try { 
					Player pl = (Player) Bukkit.getOfflinePlayer(players[i]);
					s = s.replace(players[i], pl.getDisplayName());
				} catch (Exception e) { }
			}
		}
		p.sendMessage(s);
	}
	public String weaponFinder(String weapon) {
		if (weapon.startsWith("wood") || weapon.startsWith("stone") || weapon.startsWith("gold") || weapon.startsWith("iron") || weapon.startsWith("diamond")) {
			String[] material = weapon.split("_");
			switch (material[0]) {
			case "wood":
				switch (material[1]) {
				case "sword":
					return "Wooden Sword.";
				case "axe":
					return "Wooden Axe.";
				case "hoe":
					return "Wooden Hoe.";
				case "spade":
					return "Wooden Shovel.";
				case "pickaxe":
					return "Wooden Pickaxe.";
				default:
					return ".";
				}
			case "stone":
				switch (material[1]) {
				case "sword":
					return "Stone Sword.";
				case "axe":
					return "Stone Axe.";
				case "hoe":
					return "Stone Hoe.";
				case "spade":
					return "Stone Shovel.";
				case "pickaxe":
					return "Stone Pickaxe.";
				default:
					return ".";
				}
			case "iron":
				switch (material[1]) {
				case "sword":
					return "Iron Sword.";
				case "axe":
					return "Iron Axe.";
				case "hoe":
					return "Iron Hoe.";
				case "spade":
					return "Iron Shovel.";
				case "pickaxe":
					return "Iron Pickaxe.";
				default:
					return ".";
				}
			case "gold":
				switch (material[1]) {
				case "sword":
					return "Golden Sword.";
				case "axe":
					return "Golden Axe.";
				case "hoe":
					return "Golden Hoe.";
				case "spade":
					return "Golden Shovel.";
				case "pickaxe":
					return "Golden Pickaxe.";
				default:
					return ".";
				}
			case "diamond":
				switch (material[1]) {
				case "sword":
					return "Diamond Sword.";
				case "axe":
					return "Diamond Axe.";
				case "hoe":
					return "Diamond Hoe.";
				case "spade":
					return "Diamond Shovel.";
				case "pickaxe":
					return "Diamond Pickaxe.";
				default:
					return ".";
				}
			default:
				return ".";
			}
		} else {
			return ".";
		}
	}
	public String messageFinder(String weaponType) {
		switch (weaponType.toLowerCase()) {
		case "wood sword":
			return " was clobbered to death by ";
		case "stone sword":
			return " was pounded by ";
		case "gold sword":
			return " was sliced open by ";
		case "diamond sword":
			return " was cut open by ";
		default:
			return " was slain by ";
		}
	}
}
