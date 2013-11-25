package io.github.Dinner1111.ServerUtils;

import io.github.Dinner1111.ServerUtils.ChatThemes.ThemeType;
import io.github.Dinner1111.ServerUtils.Misc.CaseManager;
import io.github.Dinner1111.ServerUtils.Misc.SharedVariables;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Lists;

public class ServerUtilsCommands implements CommandExecutor {
	boolean muted = false;
	Player firstSender;
	int task;
	Plugin plg;
	boolean isHidden;
	boolean isBroadcasting = false;
	SharedVariables sv;
	CaseManager manager;
	Startup start;
	ConfigMethods cm;
	SpawnerRunnable sr;
	ChatThemes ct = new ChatThemes(plg);
	Map<String, Map<EntityType, Double>> spawns = new HashMap<String, Map<EntityType, Double>>();
	List<EntityType> mobs = Lists.newArrayList(EntityType.BAT, EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.ENDER_DRAGON, EntityType.GHAST, EntityType.GIANT, EntityType.MAGMA_CUBE, EntityType.PIG_ZOMBIE, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.WOLF, EntityType.ZOMBIE, EntityType.WITCH, EntityType.PRIMED_TNT);
	
	public ServerUtilsCommands(Plugin pl, SharedVariables shared, Startup s, CaseManager m, ConfigMethods c) {
		plg = pl;
		sv = shared;
		start = s;
		manager = m;
		cm = c;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
		ThemeColors theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + ((Player) sender).getName() + ".theme")));
		Inventory gui = Bukkit.createInventory(((Player) sender), 9, theme.color4 + " --    " + theme.color2 + "Operator Gui");
		if (cmd.getName().equalsIgnoreCase("util-op")) {
            if (sender.hasPermission("Utils.Util.Op")) {
            	if (sender instanceof Player) {
            		if (args.length == 0) {
		                ItemStack op = new ItemStack(Material.NETHER_STAR);
		                ItemMeta meta = op.getItemMeta();
		                meta.setDisplayName(theme.color3 + "Op Online Players");
		                op.setItemMeta(meta);
		                ItemStack list = new ItemStack(Material.BOOK);
		                meta = list.getItemMeta();
		                meta.setDisplayName(theme.color3 + "List Online Ops");
		                list.setItemMeta(meta);
		                gui.setItem(2, op);
		                gui.setItem(6, list);
		                ((Player) sender).openInventory(gui);
		                return true;
            		} else {
            			sender.sendMessage(theme.color3 + "Too many arguments.");
            			sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-op");
            			return true;
            		}
            	} else {
            		sender.sendMessage(theme.color3 + "You must be a player to use this command.");
            		return true;
            	}
            }
        }
        if (cmd.getName().equalsIgnoreCase("util-deop")) {
            if (sender.hasPermission("Utils.Util.Deop")) {
            	if (sender instanceof Player) {
            		if (args.length == 0) {
		                ItemStack deop = new ItemStack(Material.NETHER_STAR);
		                ItemMeta meta = deop.getItemMeta();
		                meta.setDisplayName(theme.color3 + "Deop Online Players");
		                deop.setItemMeta(meta);
		                ItemStack list = new ItemStack(Material.BOOK);
		                meta = list.getItemMeta();
		                meta.setDisplayName(theme.color3 + "List Deopped Players");
		                list.setItemMeta(meta);
		                gui.setItem(2, deop);
		                gui.setItem(6, list);
		                ((Player) sender).openInventory(gui);
		                return true;
            		} else {
            			sender.sendMessage(theme.color3 + "Too many arguments.");
            			sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-deop");
            			return true;
            		}
            	} else {
            		sender.sendMessage(theme.color3 + "You must be a player to use this command.");
            		return true;
            	}
            }
        }
        if (cmd.getName().equalsIgnoreCase("util-unwhitelist")) {
            if (sender.hasPermission("Utils.Util.Unwhitelist")) {
            	if (sender instanceof Player) {
            		if (args.length == 0) {
		                ItemStack unwhitelist = new ItemStack(Material.NETHER_STAR);
		                ItemMeta meta = unwhitelist.getItemMeta();
		                meta.setDisplayName(theme.color3 + "Unwhitelist Online Players");
		                unwhitelist.setItemMeta(meta);
		                ItemStack list = new ItemStack(Material.BOOK);
		                meta = list.getItemMeta();
		                meta.setDisplayName(theme.color3 + "List Whitelisted Players");
		                list.setItemMeta(meta);
		                gui.setItem(2, unwhitelist);
		                gui.setItem(6, list);
		                ((Player) sender).openInventory(gui);
		                return true;
            		} else {
            			sender.sendMessage(theme.color3 + "Too many arguments.");
            			sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-unwhitelist");
            			return true;
            		}
            	} else {
            		sender.sendMessage(theme.color3 + "You must be a player to use this command.");
            		return true;
            	}
            }
        }
        if (cmd.getName().equalsIgnoreCase("util-kick")) {
            if (sender.hasPermission("Utils.Util.Kick")) {
            	if (sender instanceof Player) {
            		if (args.length == 0) {
		                ItemStack kick = new ItemStack(Material.NETHER_STAR);
		                ItemMeta meta = kick.getItemMeta();
		                meta.setDisplayName(theme.color3 + "Kick Online Players");
		                kick.setItemMeta(meta);
		                ItemStack whitelistKick = new ItemStack(Material.BOOK);
		                meta = whitelistKick.getItemMeta();
		                meta.setDisplayName(theme.color3 + "Unwhitelist and Kick");
		                whitelistKick.setItemMeta(meta);
		                ItemStack whitelistBanKick = new ItemStack(Material.BOOK);
		                meta = whitelistBanKick.getItemMeta();
		                meta.setDisplayName(theme.color3 + "Unwhitelist, Ban, and Kick");
		                whitelistBanKick.setItemMeta(meta);
		                gui.setItem(1, whitelistKick);
		                gui.setItem(4, kick);
		                gui.setItem(7, whitelistBanKick);
		                ((Player) sender).openInventory(gui);
		                return true;
            		} else {
            			sender.sendMessage(theme.color3 + "Too many arguments.");
            			sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-kick");
            			return true;
            		}
            	} else {
            		sender.sendMessage(theme.color3 + "You must be a player to use this command.");
            		return true;
            	}
            }
        }
        if (cmd.getName().equalsIgnoreCase("util-world")) {
        	if (args.length == 2) {
        		if (args[0].equalsIgnoreCase("create")) {
		            if (sender.hasPermission("Utils.Util.World.Create")) {
		            	String worldName = args[1];
		                if (worldName.contains(".")) {
		                	sender.sendMessage(theme.color3 + "You may not have '" + theme.color1 + "." + theme.color3 + "' in your world name.");
		                	sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-world [world name]");
		                    return true;
		                }
		                for (Player ops : Bukkit.getOnlinePlayers()) {
		                	if (ops.isOp()) {
		                 		ops.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + theme.color1 + sender.getName() + theme.color3 + " is creating world " + theme.color1 + worldName + theme.color3 + ".");
		                  	}
		                }
		                Bukkit.getServer().dispatchCommand(sender, "mv create " + args[1] + " normal -t flat -g NullTerrain");
		                return true;
		            }
	        	}
        		if (sender instanceof Player) {
        			Player p = (Player) sender;
	        		if (args[0].equalsIgnoreCase("color")) {
	        			if (sender.hasPermission("Utils.Util.World.Color")) {
		        			List<ChatColor> colors = Lists.newArrayList(ChatColor.AQUA, ChatColor.BLACK, ChatColor.BLUE, ChatColor.BOLD, ChatColor.DARK_AQUA, ChatColor.DARK_BLUE, ChatColor.DARK_GRAY, ChatColor.DARK_GREEN, ChatColor.DARK_PURPLE, ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.GRAY, ChatColor.GREEN, ChatColor.ITALIC, ChatColor.LIGHT_PURPLE, ChatColor.MAGIC, ChatColor.RED, ChatColor.RESET, ChatColor.STRIKETHROUGH, ChatColor.UNDERLINE, ChatColor.WHITE, ChatColor.YELLOW);
		       				try {
		        				if (colors.contains(ChatColor.valueOf(args[1].toUpperCase()))) {
			       					plg.getConfig().set("worlds." + p.getWorld().getName() + ".color", args[1].toUpperCase());
			       					plg.saveConfig();
			       					p.sendMessage(theme.color3 + "Color set to " + ChatColor.valueOf(args[1].toUpperCase()) + args[1] + theme.color3 + ".");
			       					return true;
		       					}
		       				} catch (Exception e) {
		        				p.sendMessage(theme.color3 + "Invalid color.");
		        				p.sendMessage(theme.color3 + "Valid colors: " + ChatColor.AQUA + "aqua" + theme.color3 + ", " + ChatColor.BLACK + "black" + theme.color3 + ", " + ChatColor.BLUE + "blue" + theme.color3 + ", " + ChatColor.RESET + "" + ChatColor.BOLD + "bold" + theme.color3 + ", " + ChatColor.DARK_AQUA + "dark_aqua" + theme.color3 + ", " + ChatColor.DARK_BLUE + "dark_blue" + theme.color3 + ", " + ChatColor.DARK_GRAY + "dark_gray" + theme.color3 + ", " + ChatColor.DARK_GREEN + "dark_green" + theme.color3 + ", " + ChatColor.DARK_PURPLE + "dark_purple" + theme.color3 + ", " + ChatColor.DARK_RED + "dark_red" + theme.color3 + ", " + ChatColor.GOLD + "gold" + theme.color3 + ", " + ChatColor.GRAY + "gray" + theme.color3 + ", " + ChatColor.GREEN + "green" + theme.color3 + ", " + ChatColor.RESET + "" + ChatColor.ITALIC + "italic" + theme.color3 + ", " + ChatColor.LIGHT_PURPLE + "light_purple"  + theme.color3 + ", " + ChatColor.RESET + "" + ChatColor.MAGIC + "magic" + theme.color3 + ", " + ChatColor.RED + "red" + theme.color3 + ", " + ChatColor.RESET + "reset" + theme.color3 + ", " + ChatColor.RESET + "" + ChatColor.STRIKETHROUGH + "strikethrough" + theme.color3 + ", " + ChatColor.RESET + "" + ChatColor.UNDERLINE + "underline" + theme.color3 + ", " + ChatColor.WHITE + "white" + theme.color3 + ", " + ChatColor.YELLOW + "yellow" + theme.color3 + ".");
		        				return true;
		        			}
	        			}
	        		}
	        		if (args[0].equalsIgnoreCase("alias")) {
	        			if (p.hasPermission("Utils.Util.World.Alias")) {
	        				plg.getConfig().set("worlds." + p.getWorld().getName() + ".alias", args[1]);
	        				plg.saveConfig();
	        				p.sendMessage(theme.color3 + "Alias set to " + theme.color1 + args[1]);
	        				return true;
	        			}
	        		}
	        		if (args[0].equalsIgnoreCase("monsters")) {
	        			if (p.hasPermission("Utils.Util.World.Monsters")) {
		        			if (args[1].equalsIgnoreCase("true")) {
		        				plg.getConfig().set("worlds." + p.getWorld().getName() + ".mobs.monsters", true);
		        				plg.saveConfig();
		        				p.sendMessage(theme.color3 + "Spawns for " + theme.color1 + "monsters" + theme.color3 + " are now " + theme.color1 + "true" + theme.color3 + ".");
		        				return true;
		        			} else if (args[1].equalsIgnoreCase("false")) {
		        				plg.getConfig().set("worlds." + p.getWorld().getName() + ".mobs.monsters", false);
		        				plg.saveConfig();
		        				p.sendMessage(theme.color3 + "Spawns for " + theme.color1 + "monsters" + theme.color3 + " are now " + theme.color1 + "false" + theme.color3 + ".");
		        				return true;
		        			} else {
		        				p.sendMessage(theme.color3 + "Invalid boolean value.");
		        				p.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-world monsters [true|false]");
		        				return true;
		        			}
	        			}
	        		}
	        		if (args[0].equalsIgnoreCase("animals")) {
	        			if (p.hasPermission("Utils.Util.World.Animals")) {
		        			if (args[1].equalsIgnoreCase("true")) {
		        				plg.getConfig().set("worlds." + p.getWorld().getName() + ".mobs.animals", true);
		        				plg.saveConfig();
		        				p.sendMessage(theme.color3 + "Spawns for " + theme.color1 + "animals" + theme.color3 + " are now " + theme.color1 + "true" + theme.color3 + ".");
		        				return true;
		        			} else if (args[1].equalsIgnoreCase("false")) {
		        				plg.getConfig().set("worlds." + p.getWorld().getName() + ".mobs.animals", false);
		        				plg.saveConfig();
		        				p.sendMessage(theme.color3 + "Spawns for " + theme.color1 + "animals" + theme.color3 + " are now " + theme.color1 + "false" + theme.color3 + ".");
		        				return true;
		        			} else {
		        				p.sendMessage(theme.color3 + "Invalid boolean value.");
		        				p.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-world animals [true|false]");
		        				return true;
		        			}
	        			}
	        		}
	        		if (args[0].equalsIgnoreCase("private")) {
	        			if (p.hasPermission("Utils.Util.World.Private")) {
		        			if (args[1].equalsIgnoreCase("true")) {
		        				plg.getConfig().set("worlds." + p.getWorld().getName() + ".private", true);
		        				plg.saveConfig();
		        				p.sendMessage(theme.color3 + "Spawns for " + theme.color1 + "animals" + theme.color3 + " are now " + theme.color1 + "true" + theme.color3 + ".");
		        				return true;
		        			} else if (args[1].equalsIgnoreCase("false")) {
		        				plg.getConfig().set("worlds." + p.getWorld().getName() + ".private", false);
		        				plg.saveConfig();
		        				for (Player ops : Bukkit.getOnlinePlayers()) {
		        					if (plg.getConfig().getString("worlds." + p.getWorld().getName() + ".alias") != null) {
		        						ops.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + p.getDisplayName() + theme.color3 + "set the world " + theme.color1 + p.getWorld().getName() + theme.color3 + "\'s access to " + theme.color1 + "private" + theme.color3 + " with the owner as " + theme.color1 + args[2] + theme.color3 + ".");
		        					} else {
		        						ops.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + p.getDisplayName() + theme.color3 + "set the world " + theme.color1 + plg.getConfig().getString("worlds." + p.getWorld().getName() + ".alias") + theme.color3 + "\'s access to " + theme.color1 + "private" + theme.color3 + " with the owner as " + theme.color1 + args[2] + theme.color3 + ".");
		        					}
		        				}
		        				return true;
		        			} else {
		        				p.sendMessage(theme.color3 + "Invalid boolean value.");
		        				p.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-world private [edit|true|false]");
		        				return true;
		        			}
	        			}
	        		}
	        		if (args[0].equalsIgnoreCase("mode")) {
	        			if (p.hasPermission("Utils.Util.World.Mode")) {
	        				if (args[1].equals("0") || args[1].equalsIgnoreCase("survival")) {
	        					plg.getConfig().set("worlds." + p.getWorld().getName() + ".mode", "Survival");
	        					p.sendMessage(theme.color3 + "World mode set to " + theme.color1 + "Survival");
	        					return true;
	        				} else if (args[1].equals("1") || args[1].equalsIgnoreCase("creative")) {
	        					plg.getConfig().set("worlds." + p.getWorld().getName() + ".mode", "Creative");
	        					p.sendMessage(theme.color3 + "World mode set to " + theme.color1 + "Creative");
	        					return true;
	        				} else if (args[1].equals("2") || args[1].equalsIgnoreCase("adventure")) {
	        					plg.getConfig().set("worlds." + p.getWorld().getName() + ".mode", "Adventure");
	        					p.sendMessage(theme.color3 + "World mode set to " + theme.color1 + "Adventure");
	        					return true;
	        				} else {
	        					p.sendMessage(theme.color3 + "Inavlid mode.");
	        					p.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-world mode [1|2|3]");
	        					return true;
	        				}
	        			}
	        		}
	        		if (args[0].equalsIgnoreCase("weather")) {
	        			if (p.hasPermission("Utils.Util.World.Weather")) {
	        				if (args[1].equalsIgnoreCase("true")) {
	        					plg.getConfig().set("worlds." + p.getWorld().getName() + ".weather", true);
	        				}
	        			}
	        		}
        		}
        	} else if (args.length == 1 || args.length == 2) {
        		if (args[0].equalsIgnoreCase("settings")) {
        			if (sender.hasPermission("Utils.Util.World.Settings")) {
        				sender.sendMessage(theme.color4 + "===============" + theme.color1 + " World Settings " + theme.color4 + "==============="
            					+ theme.color1 + "\nAlias" + theme.color4 + " - " + theme.color3 + "Set a display name for your world."
            					+ theme.color1 + "\nColor" + theme.color4 + " - " + theme.color3 + "Set a color for the name or display name of your world.");
        				sender.sendMessage(theme.color3 + "You can edit the settings with " + theme.color1 + "/util-world [setting] [value]" + theme.color3 + ".");
        				return true;
        			}
        		}
        		if (args[0].equalsIgnoreCase("info")) {
        			if (sender.hasPermission("Utils.Util.World.Info")) {
        				String[] s = new String[6];
        				String w = null;
        				if (sender instanceof Player) {
        					if (args.length == 1) {
        						w = ((Player) sender).getWorld().getName();
        					}
        				} else {
        					if (args.length == 2) {
        						try { w = Bukkit.getWorld(args[1]).getName(); } catch (Exception e) {
        							sender.sendMessage(theme.color3 + "Invalid world.");
        							return true;
        						}
        					}
        				}
        				if (!(plg.getConfig().getString("worlds." + w + ".alias") == null)) {
        					s[0] = plg.getConfig().getString("worlds." + w + ".alias");
        				}
        				if (!(plg.getConfig().getString("worlds." + w + ".color") == null)) {
        					s[1] = plg.getConfig().getString("worlds." + w + ".color").toLowerCase();
        					if (s[1].contains("_")) { s[1].replace("_", " "); }
        				}
        				if (plg.getConfig().getBoolean("worlds." + w + ".monsters")) {
        					s[2] = plg.getConfig().getBoolean("worlds." + w + ".monsters") ? "true" : "false";
        				}
        				if (plg.getConfig().getBoolean("worlds." + w + ".animals")) {
        					s[3] = plg.getConfig().getBoolean("worlds." + w + ".animals") ? "true" : "false";
        				}
        				if (!(plg.getConfig().getString("worlds." + w + ".mode") == null)) {
        					s[4] = plg.getConfig().getString("worlds." + w + ".mode");
        				}
        				if (plg.getConfig().getBoolean("worlds." + w + ".private")) {
        					s[5] = plg.getConfig().getBoolean("worlds." + w + ".private") ? "true" : "false";
        				}
        				if (plg.getConfig().getBoolean("worlds." + w + ".weather")) {
        					s[6] = plg.getConfig().getBoolean("worlds." + w + ".weather") ? "true" : "false";
        				}
        				sender.sendMessage(theme.color4 + "===============" + theme.color1 + " World Info " + theme.color4 + "==============="
        					+ theme.color1 + "\nName" + theme.color4 + " - " + w
        					+ theme.color1 + "\nAlias" + theme.color4 + " - " + theme.color3 + s[0]
        					+ theme.color1 + "\nColor" + theme.color4 + " - " + theme.color3 + s[1]
        					+ theme.color1 + "\nMonsters" + theme.color4 + " - " + theme.color3 + s[2]
        					+ theme.color1 + "\nAnimals" + theme.color4 + " - " + theme.color3 + s[3]
        					+ theme.color1 + "\nMode" + theme.color4 + " - " + theme.color3 + s[4]
        					+ theme.color1 + "\nPrivate" + theme.color4 + " - " + theme.color3 + s[5]
        					+ theme.color1 + "\nWeather" + theme.color4 + " - " + theme.color3 + s[6]);
        				return true;
        			}
        		}
        	} else {
        		sender.sendMessage(theme.color3 + "Invalid arguments.");
        		sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-world [create|color|alias|monsters|animals]");
        		return true;
        	}
        }
        if (cmd.getName().equalsIgnoreCase("util-list")) {
            if (sender.hasPermission("Utils.Util.List")) {
                if (args.length == 0) {
                    sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility List " + theme.color4 + "[" + theme.color2 + "Page " + ChatColor.GOLD + "1" + theme.color4 + "/" + ChatColor.GOLD + "3" + theme.color4 + "] "+ theme.color4 + "==============="
                        + theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Help" + theme.color4 + " - " + theme.color3 + "/util-list help"
                        + theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Op" + theme.color4 + " - " + theme.color3 + "/util-list op"
                        + theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Deop" + theme.color4 + " - " + theme.color3 + "/util-list deop"
                        + theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Unwhitelist" + theme.color4 + " - " + theme.color3 + "/util-list unwhitelist"
                        + theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Kick" + theme.color4 + " - " + theme.color3 + "/util-list kick");
                } else if (args.length == 1) {
                	if (args[0].equalsIgnoreCase("2")) {
                		sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility List " + theme.color4 + "[" + theme.color2 + "Page " + ChatColor.GOLD + "2" + theme.color4 + "/" + ChatColor.GOLD + "3" + theme.color4 + "] "+ theme.color4 + "==============="
                			+ theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "World" + theme.color4 + " - " + theme.color3 + "/util-list world"
                			+ theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Mute" + theme.color4 + " - " + theme.color3 + "/util-list mute"
                			+ theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Broadcast" + theme.color4 + " - " + theme.color3 + "/util-list broadcast"
                			+ theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Kill" + theme.color4 + " - " + theme.color3 + "/util-list kill"
                			+ theme.color1 + "\nUtil" + theme.color4 + "-" + theme.color1 + "Restart" + theme.color4 + " - " + theme.color3 + "/util-list restart");
                	} else if (args[0].equalsIgnoreCase("3")) {
                		sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Misc Utility List " + theme.color4 + "[" + theme.color2 + "Page " + ChatColor.GOLD + "2" + theme.color4 + "/" + ChatColor.GOLD + "3" + theme.color4 + "] "+ theme.color4 + "==============="
                			+ theme.color1 + "\nUtility" + theme.color4 + "-" + theme.color1 + "AdminChat" + theme.color4 + " - " + theme.color3 + "/util-list adminchat"
                			+ theme.color1 + "\nUtility" + theme.color4 + "-" + theme.color1 + "Silent" + theme.color4 + " - " + theme.color3 + "/util-list silent"
                			+ theme.color1 + "\nUtility" + theme.color4 + "-" + theme.color1 + "Announcer" + theme.color4 + " - " + theme.color3 + "/util-list announcer");
                	} else if (args[0].equalsIgnoreCase("help")) {
                        sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Help" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Displays useful tips and reminders." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-help" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Help");
                    } else if (args[0].equalsIgnoreCase("op")) {
                        sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Op" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Grants op permissions to all online players that are not an op." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-op" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Op");
                    } else if (args[0].equalsIgnoreCase("deop")) {
                        sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Deop" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Deops all non op players, except for the command sender." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-deop" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Deop");
                    } else if (args[0].equalsIgnoreCase("unwhitelist")) {
                        sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Unwhitelist" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Unwhitelists all non op players that are online." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-unwhitelist" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Unwhitelist");
                    } else if (args[0].equalsIgnoreCase("kick")) {
                        sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Kick" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Kicks all non op players." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-kick [-u]" + theme.color1 + "\nFlags" + theme.color4 + " - " + theme.color3 + "-u" + theme.color4 + " - " + theme.color3 + "Unwhitelists and kicks players." + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Kick");
                    } else if (args[0].equalsIgnoreCase("world")) {
                        sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "World" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Generates a new void world with the name inputed." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-world [world name]" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.World");
                    } else if (args[0].equalsIgnoreCase("mute")) {
                    	sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Mute" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Mutes all non op players." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-mute : Type again to unmute." + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Mute");
                    } else if (args[0].equalsIgnoreCase("broadcast")) {
                    	sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Broadcast" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Server-wide broadcast. Mutes all non op players." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-broadcast : Type again to exit the mode." + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Broadcast");
                    } else if (args[0].equalsIgnoreCase("kill")) {
                    	sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Kill" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Kills all non op players." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-kill" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Kill");
                    } else if (args[0].equalsIgnoreCase("restart")) {
                    	sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Restart" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Stops the server in the given amount of seconds." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/util-restart [seconds]" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Util.Restart");
                    } else if (args[0].equalsIgnoreCase("adminchat")) {
                    	sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "AdminChat" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Allows you to speak in a private admin chat." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/a [message]" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.AdminChat");
                	} else if (args[0].equalsIgnoreCase("silent")) {
                		sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Silent" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Silently executes a specified command." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/silent [op|deop|unwhitelist|whitelist|kick|ban]" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Silent");
                	} else if (args[0].equalsIgnoreCase("announcer")) {
                		sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Utility" + theme.color4 + " - " + theme.color1 + "Kill" + theme.color4 + " ===============" + theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + "Schedules an announcement to broadcast every specified minutes." + theme.color1 + "\nUsage" + theme.color4 + " - " + theme.color3 + "/announcer [minute delay] [message]" + theme.color1 + "\nPermission" + theme.color4 + " - " + theme.color3 + "Utils.Announcer");
                	} else if (args[0].equalsIgnoreCase("hide")) {
                		sender.sendMessage(theme.color4 + "===============" );
                	} else {
                        sender.sendMessage(theme.color3 + "Invalid help subject.");
                        sender.sendMessage(theme.color3 + "Usage: " + theme.color1 +  "/util-list [subject]");
                        return true;
                    }
                } else {
                	sender.sendMessage(theme.color3 + "Too many arguments.");
                    sender.sendMessage(theme.color3 + "Usage: " + theme.color1 +  "/util-list [subject]");
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("util-mute")) {
            if (sender.hasPermission("Utils.Util.Mute")) {
                if (args.length == 0) {
                    if (!muted) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.isOp()) {
                            	if (sender instanceof Player) {
                            		p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] "  + ((Player) sender).getDisplayName() + theme.color3 + " has muted all online players.");
                            	}
                            } else {
                            	if (!(sender instanceof Player)) {
                            		p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + theme.color3 + " has muted you.");
                            	}
                            }
                        }
                        muted = !muted;
                        sv.setIsMuted(muted);
                    } else {
                        muted = !muted;
                        sv.setIsMuted(muted);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                        	if (p.isOp()) {
                            	if (sender instanceof Player) {
                            		p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] "  + ((Player) sender).getDisplayName() + theme.color3 + " has unmuted all online players.");
                            	}
                            } else {
                            	if (!(sender instanceof Player)) {
                            		p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + theme.color3 + " has unmuted you.");
                            	}
                            }
                        }
                    }
                } else {
                    sender.sendMessage(theme.color3 + "Too many arguments.");
                    sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-mute");
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("util-broadcast")) {
            if (sender.hasPermission("Utils.Util.Broadcast")) {
                if (args.length == 0) {
                    if (!isBroadcasting) {
                    	if (sender instanceof Player) {
                    		Bukkit.getServer().broadcastMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) sender).getDisplayName() + theme.color3 + " is now broadcasting!");
                    	} else {
                    		Bukkit.getServer().broadcastMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + theme.color3 + " is now broadcasting!");
                    	}
                        isBroadcasting = !isBroadcasting;
                        sv.setIsBroadcasting(isBroadcasting);
                        firstSender = (Player) sender;
                    } else {
                       	if (sender instanceof Player) {
                       		Bukkit.getServer().broadcastMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) sender).getDisplayName() + theme.color3 + " is no longer broadcasting.");
                       	} else {
                       		Bukkit.getServer().broadcastMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + theme.color3 + " is no longer broadcasting.");
                       	}
                       	isBroadcasting = !isBroadcasting;
                       	sv.setIsBroadcasting(isBroadcasting);
                     return true;
                    }
                } else {
                    sender.sendMessage(theme.color3 + "Too many arguments.");
                    sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-broadcast [message]");
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("util-kill")) {
        	if (sender.hasPermission("Utils.Util.Kill")) {
        		if (args.length == 0) {
        			for (Player target : Bukkit.getOnlinePlayers()) {
        				if (sender instanceof Player) {
	        				if (target == (Player) sender || target.isOp()) {
	        					target.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) sender).getDisplayName() + theme.color3 + " has slain all online players.");
	        				} else {
	        					target.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) sender).getDisplayName() + theme.color3 + " has slain you.");
	        					target.setHealth(0);
	        				}
        				} else {
        					if (target.isOp()) {
	        					target.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + theme.color3 + " has slain all online players.");
	        				} else {
	        					target.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + theme.color3 + " has slain you.");
	        					target.setHealth(0);
	        				}
        				}
        			}
        			return true;
        		} else {
        			sender.sendMessage(theme.color3 + "Too many arguments.");
        			sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-kill");
        			return true;
        		}
        	}
        }
		if (cmd.getName().equalsIgnoreCase("util-version")) {
			if (sender.hasPermission("Utils.Util.Version")) {
				if (args.length == 0) {
					String authors = plg.getDescription().getAuthors().toString();
					if (authors.contains("[") || authors.contains("]")) {
						authors = authors.replace("[", "").replace("]", "");
					}
					sender.sendMessage(theme.color4 + "===============" + theme.color1 + " Version Info " + theme.color4 + "==============="
						+ theme.color1 + "\nFull Name" + theme.color4 + " - " + theme.color3 + plg.getDescription().getFullName()
						+ theme.color1 + "\nDescription" + theme.color4 + " - " + theme.color3 + plg.getDescription().getDescription()
						+ theme.color1 + "\nAuthors" + theme.color4 + " - " + theme.color3 + authors
						+ theme.color1 + "\nMain Class" + theme.color4 + " - " + theme.color3 + plg.getDescription().getMain());
					return true;
				} else {
					sender.sendMessage(theme.color3 + "Too many arguments.");
					sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-version");
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("util-reload")) {
			if (sender.hasPermission("Utils.Util.Reload")) {
				if (args.length == 0) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + p.getName() + ".theme")));
							p.sendMessage(theme.color4 + "[" + theme.color2 +"ServerUtils" + theme.color4 + "] " + theme.color3 + "Reloading configs from disk...");
						}
					}
					Bukkit.getConsoleSender().sendMessage(theme.color4 + "[" + theme.color2 +"ServerUtils" + theme.color4 + "] " + theme.color3 + "Reloading configs from disk...");
					plg.saveDefaultConfig();
					plg.reloadConfig();
					cm.saveDefaultConfig();
					cm.reloadConfig();
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + p.getName() + ".theme")));
							p.sendMessage(theme.color4 + "[" + theme.color2 +"ServerUtils" + theme.color4 + "] " + theme.color3 + "Reload complete. Items reloaded:"
								+ theme.color4 + "\n  - " + theme.color1 + "plugin.yml"
								+ theme.color4 + "\n  - " + theme.color1 + "config.yml"
								+ theme.color4 + "\n  - " + theme.color1 + "players.yml");
						}
					}
					Bukkit.getConsoleSender().sendMessage(theme.color4 + "[" + theme.color2 +"ServerUtils" + theme.color4 + "] " + theme.color3 + "Reload complete. Items reloaded:"
						+ theme.color4 + "\n  - " + theme.color1 + "plugin.yml"
						+ theme.color4 + "\n  - " + theme.color1 + "config.yml"
						+ theme.color4 + "\n  - " + theme.color1 + "players.yml");
					return true;
				} else if (args.length == 1) {
					if (args[0].startsWith("-n")) {
						manager.reloadCustomDisplayNames(args);
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("-l")) {
						manager.displayConfigLists(args, ((Player) sender));
					}
				} else {
					sender.sendMessage(theme.color3 + "Too many arguments.");
					sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-reload");
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("util-alert")) {
			if (sender.hasPermission("Utils.Util.Alert")) {
				if (args.length != 0) {
					String s = "";
					for (int i = 0; i < args.length; i++) 
						s += args[i] + " ";
					s = s.substring(0, s.length() - 1);
					if (sender instanceof Player) {
						Bukkit.getConsoleSender().sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) sender).getDisplayName() + theme.color3 + " has sent out an alert.");
					} else {
						Bukkit.getConsoleSender().sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + theme.color3 + " has sent out an alert.");
					}
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							if (sender instanceof Player) {
								p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ((Player) sender).getDisplayName() + theme.color3 + " has sent out an alert.");
							} else {
								p.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + theme.color3 + " has sent out an alert.");
							}
						}
						p.sendMessage(theme.color4 + "===============" + theme.color1 + ChatColor.ITALIC + "" + ChatColor.BOLD + " Alert " + theme.color4 + "===============" + "\n ");
						p.sendMessage(theme.color3 + s + "\n ");
						p.sendMessage(theme.color4 + "===============" + theme.color1 + ChatColor.ITALIC + "" + ChatColor.BOLD + " Alert " + theme.color4 + "===============");
					}
					Bukkit.getConsoleSender().sendMessage(theme.color4 + "===============" + theme.color1 + " Alert " + theme.color4 + "===============" + "\n ");
					Bukkit.getConsoleSender().sendMessage(theme.color3 + s + "\n ");
					Bukkit.getConsoleSender().sendMessage(theme.color4 + "===============" + theme.color1 + " Alert " + theme.color4 + "===============");
					return true;
				} else {
					sender.sendMessage(theme.color3 + "Too few arguments.");
					sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-alert [message]");
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("util-config")) {
			if (sender.hasPermission("Utils.Util.Config")) {
				if (args.length == 2) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						if (cm.getConfig().getConfigurationSection("players." + p.getName()).getKeys(false).contains(args[0])) {
							try { cm.getConfig().set("players." + p.getName() + "." + args[0], args[1]); } catch (Exception e) {
								sender.sendMessage(theme.color1 + args[1] + theme.color3 + " is not a valid value for " + theme.color1 + args[0] + theme.color3 + ".");
								sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-config [player] [configuration section] [value]");
								return true;
							}
							for (Player op : Bukkit.getOnlinePlayers()) {
								if (op.hasPermission("Utils.AdminChat.Listen"))
									op.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + theme.color1 + args[0] + theme.color3 + " was set to " + theme.color1 + args[1] + theme.color3 + " for player " + theme.color1 + p.getDisplayName() + theme.color3 + ".");
							}
							cm.saveConfig();
							cm.reloadConfig();
							return true;
						} else {
							sender.sendMessage(theme.color1 + args[0] + theme.color3 + " is not a valid configuration section.");
							sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-config [player] [configuration section] [value]");
							return true;
						}
					}
				} else if (args.length == 3) {
					Player p = null;
					try { p = Bukkit.getPlayer(args[0]); } catch (Exception e) {
						sender.sendMessage(theme.color1 + args[0] + theme.color3 + " is not online.");
						sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-config [player] [configuration section] [value]");
						return true;
					}
					if (cm.getConfig().getConfigurationSection("players." + p.getName()).getKeys(false).contains(args[1])) {
						try { cm.getConfig().set("players." + p.getName() + "." + args[1], args[2]); } catch (Exception e) {
							sender.sendMessage(theme.color1 + args[1] + theme.color3 + " is not a valid value for " + theme.color1 + args[0] + theme.color3 + ".");
							sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-config [player] [configuration section] [value]");
							return true;
						}
						for (Player op : Bukkit.getOnlinePlayers()) {
							if (op.hasPermission("Utils.AdminChat.Listen"))
								op.sendMessage(theme.color4 + "[" + theme.color2 + "ServerUtils" + theme.color4 + "] " + theme.color3 + "Configuration section " + theme.color1 + args[1] + theme.color3 + " was set to " + theme.color1 + args[2] + theme.color3 + " for player " + theme.color1 + p.getDisplayName() + theme.color3 + ".");
						}
						cm.saveConfig();
						cm.reloadConfig();
						return true;
					} else {
						sender.sendMessage(theme.color1 + args[0] + theme.color3 + " is not a valid configuration section.");
						sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-config [player] [configuration section] [value]");
						return true;
					}
				} else {
					sender.sendMessage(theme.color3 + "Invalid arguments.");
					sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/util-config [player] [configuration section] [value]");
					return true;
				}
			}
		}
		return false;
	}
}
