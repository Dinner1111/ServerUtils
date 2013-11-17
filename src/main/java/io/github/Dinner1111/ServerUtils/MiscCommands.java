package io.github.Dinner1111.ServerUtils;

import io.github.Dinner1111.ServerUtils.ChatThemes.ThemeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.google.common.collect.Lists;

public class MiscCommands implements CommandExecutor {
	Plugin plg;
	SharedVariables sv;
	int task;
	CannoneerRunnable cr;
	int i;
	Map<Player, CannoneerRunnable> map = new HashMap<Player, CannoneerRunnable>();
	Map<Player, Player> love = new HashMap<Player, Player>();
	ChatThemes ct = new ChatThemes(plg);
	ConfigMethods cm;
	boolean bday = false;
	boolean arrow = false;
	public MiscCommands(Plugin pl, SharedVariables s, CannoneerRunnable run, ConfigMethods c) {
		plg = pl;
		sv = s;
		cr = run;
		cm = c;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
		ThemeColors theme = ct.ThemeColor(ThemeType.valueOf(cm.getConfig().getString("players." + ((Player) sender).getName() + ".theme")));
        if (cmd.getName().equalsIgnoreCase("a")) {
        	if (sender.hasPermission("Utils.AdminChat")) {
        		if (args.length >= 1) {
        			String m = "";
        			for (String str : args) {
    					m += str + " ";
    				}
    				m = m.substring(0, m.length() - 1);
        			Player s = null;
	        		for (Player p : Bukkit.getOnlinePlayers()) {
	        			if (p.isOp() || p.hasPermission("Utils.AdminChat.Listener")) {
	        				if (sender instanceof Player) {
	        					s = (Player) sender;
	        					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "A" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET + s.getDisplayName() + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + m);
	        				} else {
	        					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "A" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + m);
	        				}
	        			}
	        		}
	        		if (sender instanceof Player) {
	        			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "A" + ChatColor.DARK_GRAY + "] " + s.getDisplayName() + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + m);
	        		} else {
	        			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "A" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + "*" + ChatColor.RED + "Console" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + m);
	        		}
	        		return true;
        		} else {
        			sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/a [message]");
        			return true;
        		}
        	}
        }
        if (cmd.getName().equalsIgnoreCase("silent")) {
        	if (sender.hasPermission("Utils.Silent")) {
        		if (args.length == 2) {
        			Player target = Bukkit.getPlayer(args[1]);
        			if (target != null) {
        				if (args[0].equalsIgnoreCase("op")) {
	        				for (Player p : Bukkit.getOnlinePlayers()) {
	        					if (p.isOp()) {
	        						p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " silently opped " + ChatColor.AQUA + target.getName() + ChatColor.GRAY + ".");
	        					}
	        				}
	        				target.setOp(true);
	        				return true;
	        			}
        				if (args[0].equalsIgnoreCase("deop")) {
        					target.setOp(false);
        					for (Player p : Bukkit.getOnlinePlayers()) {
	        					if (p.isOp()) {
	        						p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " silently deopped " + ChatColor.AQUA + target.getName() + ChatColor.GRAY + ".");
	        					}
	        				}
        					return true;
        				}
        				if (args[0].equalsIgnoreCase("unwhitelist")) {
        					target.setWhitelisted(false);
        					for (Player p : Bukkit.getOnlinePlayers()) {
        						if (p.isOp()) {
        							p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " silently unwhitelisted " + ChatColor.AQUA + target.getName() + ChatColor.GRAY + ".");
        						}
        					}
        					return true;
        				}
        				if (args[0].equalsIgnoreCase("whitelist")) {
        					target.setWhitelisted(true);
        					for (Player p : Bukkit.getOnlinePlayers()) {
        						if (p.isOp()) {
        							p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " silently whitelisted " + ChatColor.AQUA + target.getName() + ChatColor.GRAY + ".");
        						}
        					}
        					return true;
        				}
        				if (args[0].equalsIgnoreCase("kick")) {
        					target.kickPlayer(ChatColor.GRAY + "You have been kicked.");
        					for (Player p : Bukkit.getOnlinePlayers()) {
        						if (p.isOp()) {
        							p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " silently kicked " + ChatColor.AQUA + target.getName() + ChatColor.GRAY + ".");
        						}
        					}
        					return true;
        				}
        				if (args[0].equalsIgnoreCase("ban")) {
        					target.setWhitelisted(false);
        					target.setOp(false);
        					target.setBanned(true);
        					target.kickPlayer(ChatColor.GRAY + "You have been banned.");
        					for (Player p : Bukkit.getOnlinePlayers()) {
        						if (p.isOp()) {
        							p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " silently unwhitelisted " + ChatColor.AQUA + target.getName() + ChatColor.GRAY + ".");
        						}
        					}
        					return true;
        				}
        			}
        		} else {
        			if (args.length >= 1) {
        				sender.sendMessage(ChatColor.GRAY + "Too few arguments.");
        			} else {
        				sender.sendMessage(ChatColor.GRAY + "Too many arguments.");
        			}
        			sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/silent " + ChatColor.DARK_AQUA + "[op|deop|unwhitelist|whitelist|kick|ban] [player]");
        			return true;
        		}
        	}
        }
        if (cmd.getName().equalsIgnoreCase("announcer")) {
        	if (sender.hasPermission("Utils.Announcer")) {
        		if (args.length > 1) {
        			if (args[0].equalsIgnoreCase("delay")) {
        				if (args.length == 2) {
        					long delay = 0;
        					try { delay = Integer.parseInt(args[1]) * 20; }
        					catch (Exception e) {
        						sender.sendMessage(ChatColor.GRAY + "You must enter an integer delay.");
        						return true;
        					}
        					plg.getConfig().set("announcement.delay", delay);
        					plg.saveConfig();
        					for (Player p : Bukkit.getOnlinePlayers()) {
        						if (p.isOp()) {
        							p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET + ((Player) sender).getDisplayName() + ChatColor.GRAY + " changed the anouncement delay to " + ChatColor.AQUA + (delay / 20) + ChatColor.GRAY + " minutes.");
        						}
        					}
        					return true;
        				} else {
        					sender.sendMessage(ChatColor.GRAY + "Too many arguments.\nUsage: " + ChatColor.AQUA + "/announcer <[delay] [time]> [message]");
        					return true;
        				}
        			} else {
        				String[] s = args;
        				String message = "";
        				for (int i = 0; i < s.length; i++) {
        					message +=  s[i] + " ";
        				}
        				message = message.substring(0, message.length() - 1);
        				plg.getConfig().set("announcement.message", message);
        				plg.saveConfig();
        				for (Player p : Bukkit.getOnlinePlayers()) {
    						if (p.isOp()) {
    							p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET + ((Player) sender).getDisplayName() + ChatColor.GRAY + " changed the anouncement to \"" + ChatColor.AQUA + message + ChatColor.GRAY + "\"");
    						}
    					}
        				return true;
        			}
        		} else {
        			plg.getConfig().set("announcement.message", args[0]);
    				plg.saveConfig();
    				for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET + ((Player) sender).getDisplayName() + ChatColor.GRAY + " changed the anouncement to \"" + ChatColor.AQUA + args[0] + ChatColor.GRAY + "\"");
						}
					}
    				return true;
        		}
        	}
        }
		if (cmd.getName().equalsIgnoreCase("hide")) {
			if (sender.hasPermission("Utils.Hide")) {
				if (args.length == 1 || args.length == 0) {
					Player target;
					if (args.length == 1) {
						target = Bukkit.getPlayer(args[0]);
					} else {
						target = Bukkit.getPlayer(sender.getName());
					}
					List<String> hiddenPlayers = plg.getConfig().getStringList("hiddenPlayers");
					if (!hiddenPlayers.contains(target.getName())) {
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.hidePlayer(target);
						}
						target.sendMessage(ChatColor.GRAY + "You are now " + ChatColor.AQUA + "hidden" + ChatColor.GRAY + ".");
						hiddenPlayers.add(target.getName());
						plg.getConfig().set("hiddenPlayers", hiddenPlayers);
						plg.saveConfig();
						return true;
					} else {
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.showPlayer(target);
						}
						target.sendMessage(ChatColor.GRAY + "You are now " + ChatColor.AQUA + "visible" + ChatColor.GRAY + ".");
						hiddenPlayers.remove(target.getName());
						plg.getConfig().set("hiddenPlayers", hiddenPlayers);
						plg.saveConfig();
						return true;
					}
				} else {
					sender.sendMessage(ChatColor.GRAY + "Too many arguments.");
					sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/hide [player]");					
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("run")) {
			if (sender.hasPermission("Utils.Run")) {
				if (args.length > 0) {
					String s = "";
					for (int i = 0; i < args.length; i++) {
						s += args[i] + " ";
					}
					s = s.substring(0, s.length() - 1);
					try {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
					} catch (Exception e) {
						sender.sendMessage(ChatColor.GRAY + "Invalid command.");
						sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/run [command]");
						return true;
					}
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET + ((Player) sender).getDisplayName() + ChatColor.GRAY + " dispatched " + ChatColor.AQUA + "/" + s + ChatColor.GRAY + " successfully.");
						}
					}
					return true;
				} else {
					sender.sendMessage(ChatColor.GRAY + "Not enough arguments.");
					sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/run [command]");
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("railgun")) {
			if (sender.hasPermission("Utils.Railgun")) {
				if (args.length == 0) {
					Player p = (Player) sender;
					if (!(map.containsKey(p))) {
						map.put(p, new CannoneerRunnable(plg, p));
						if (map.get(p).getIsRunning()) {
							map.get(p).setIsRunning(false);
							map.get(p).cancel();
							p.sendMessage(ChatColor.GRAY + "No more pew?");
						} else {
							p.sendMessage(ChatColor.GRAY + "Pew pew pew!");
							map.get(p).runTaskTimer(plg, 0, 2);
						}
						return true;
					}
				} else {
					sender.sendMessage(ChatColor.GRAY + "Too many arguments.");
					sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/railgun");
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("love")) {
			if (sender.hasPermission("Utils.Love")) {
				if (sender instanceof Player) {
					if (args.length == 1) {
						Player target = null;
						try { target = Bukkit.getPlayer(args[0]); } catch (Exception e) {
							sender.sendMessage(ChatColor.AQUA + args[0] + " is not online!");
							return true;
						}
						final Player[] lovers = { ((Player) sender), target };
						if (love.get(((Player) sender)) == null) {
							love.put(((Player) sender), target);
							Bukkit.getServer().broadcastMessage(((Player) sender).getDisplayName() + ChatColor.GRAY + " is in love with " + target.getDisplayName() + ChatColor.GRAY + "! How cute!");
							i = Bukkit.getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() { 
								public void run() {
									for (Player p : lovers) {
										if (p.isOnline()) {
											Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
											FireworkMeta fwm = fw.getFireworkMeta();
											fwm.setPower(1);
											fwm.addEffect(FireworkEffect.builder()
												.with(Type.BALL)
												.withColor(Color.RED)
												.withFade(Color.FUCHSIA)
												.withFlicker()
												.build());
											fw.setFireworkMeta(fwm);
											/*Packet38EntityStatus packet = new Packet38EntityStatus(p.getEntityId(), (byte) 12);
											for (Player pl : Bukkit.getOnlinePlayers())
												((CraftPlayer) pl).getHandle().playerConnection.sendPacket(packet);*/
										}
									}
								}
							}, 100, 100);
							return true;
						} else {
							love.remove(((Player) sender));
							Bukkit.getServer().broadcastMessage(((Player) sender).getDisplayName() + ChatColor.GRAY + " is no longer in love with " + target.getDisplayName() + ChatColor.GRAY + "!");
							Bukkit.getScheduler().cancelTask(i);
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.GRAY + "Too many arguments.");
						sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/love [player]");
						return true;
					}
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("whirl")) {
			if (args.length == 1) {
				Player p;
				try { p = Bukkit.getPlayer(args[0]); } catch (Exception e) { 
					sender.sendMessage(ChatColor.AQUA + args[0] + ChatColor.GRAY + " is not online.");
					return true;
				}
				Vector vec = new Vector(0, 7, 0);
				p.setVelocity(vec);
				int n = 0;
				while (n > 150) {
					n++;
					Vector vel = p.getVelocity();
					Location loc = p.getLocation();
					loc.setYaw(p.getLocation().getYaw() + 2);
					p.teleport(loc);
					p.setVelocity(vel);
				}
				n = 0;
				while (n > 3) {
					n++;
					Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					FireworkMeta fwm = fw.getFireworkMeta();
					fwm.setPower(1);
					fwm.addEffects(FireworkEffect.builder()
							.with(Type.BURST)
							.flicker(true)
							.trail(true)
							.withColor(Color.BLUE)
							.withColor(Color.NAVY)
							.withFade(Color.BLUE)
							.build());
					fw.setFireworkMeta(fwm);
				}
				p.getWorld().createExplosion(p.getLocation(), .1F);
				if (p.getBedSpawnLocation() != null) {
					p.teleport(p.getBedSpawnLocation());
				} else {
					p.teleport(p.getWorld().getSpawnLocation());
				}
				Bukkit.broadcastMessage(((Player) sender).getDisplayName() + ChatColor.GRAY + " used whirl attack on " + p.getDisplayName());
				return true;
			} else {
				sender.sendMessage(ChatColor.GRAY + "Invalid arguments.");
				sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/whirl [player]");
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("purge")) {
			if (sender.hasPermission("Utils.Purge")) {
				if (args.length == 0) {
					for (Entity e : ((Player) sender).getWorld().getEntities()) {
						if (!(e instanceof Villager) && !(e instanceof Player)) {
							e.getWorld().createExplosion(e.getLocation(), .1F);
							e.remove();
						}
					}
					sender.sendMessage(theme.color3 + "Entities removed.");
				} else if (args.length == 1 && args[0].equalsIgnoreCase("-a")) {
					for (World w : Bukkit.getWorlds()) {
						for (Entity e : w.getEntities()) {
							if (!(e instanceof Villager) && !(e instanceof Player)) {
								e.getWorld().createExplosion(e.getLocation(), .1F);
								e.remove();
							}
						}
					}
				} else {
					sender.sendMessage(theme.color3 + "Invalid arguments.");
					sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/purge [-a]");
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("morph")) {
			if (sender.hasPermission("Utils.Morph")) {
				if (args.length == 0) {
	            	Inventory gui = Bukkit.createInventory(((Player) sender), 9, theme.color4 + " --    " + theme.color2 + "Morphous Gui");
	                ItemStack circle = new ItemStack(Material.HARD_CLAY);
	                //MaterialData md = circle.getData();
	                //md.setData(data);
	                //circle.setData(data);
	                ItemMeta meta = circle.getItemMeta();
	                meta.setDisplayName(theme.color3 + "Circle");
	                circle.setItemMeta(meta);
	                ItemStack list = new ItemStack(Material.BOOK);
	                meta = list.getItemMeta();
	                meta.setDisplayName(theme.color3 + "List Online Ops");
	                list.setItemMeta(meta);
	                //gui.setItem(2, op);
	                gui.setItem(6, list);
	                ((Player) sender).openInventory(gui);
	                return true;
				} else {
					sender.sendMessage(theme.color3 + "Too many arguments.");
					sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/gyro");
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("bday")) {
			if (sender.hasPermission("Utils.BDay")) {
				if (args.length == 1) {
					if (!bday) {
						Player user = null;
						try { user = Bukkit.getPlayer(args[0]); } catch (Exception e) {
							sender.sendMessage(theme.color1 + args[0] + theme.color3 + " is not online.");
							return true;
						}
						final Player p = user;
						i = Bukkit.getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
							public void run() {
								Location[] locs = new Location[7];
								int x = (int) p.getLocation().getX();
								int y = (int) p.getLocation().getY() - 3;
								int z = (int) p.getLocation().getZ();
								locs[0] = new Location(p.getWorld(), x, y, z - 7);
								locs[1] = new Location(p.getWorld(), x + 5, y, z - 5);
								locs[2] = new Location(p.getWorld(), x - 5, y, z - 5);
								locs[3] = new Location(p.getWorld(), x + 7, y, z);
								locs[4] = new Location(p.getWorld(), x, y, z + 7);
								locs[5] = new Location(p.getWorld(), x - 5, y, z + 5);
								locs[6] = new Location(p.getWorld(), x + 5, y, z + 5);
								Random r = new Random();
								for (int i = 0; i >= locs.length; i++) {
									Firework fw = (Firework) p.getWorld().spawnEntity(locs[i], EntityType.FIREWORK);
									FireworkMeta fwm = fw.getFireworkMeta();
									fwm.setPower(1);
									fwm.addEffects(FireworkEffect.builder()
											.with(Type.BURST)
											.withColor(getColor(256))
											.withFade(getColor(256))
											.flicker(true)
											.build());
									fw.setFireworkMeta(fwm);
								}
							}
						}, 0, 140);
						bday = !bday;
						sender.sendMessage(theme.color3 + "Happy birthday!");
						return true;
					} else {
						Bukkit.getScheduler().cancelTask(i);
						bday = !bday;
						sender.sendMessage(theme.color3 + "No more celebrations?");
						return true;
					}
				} else {
					sender.sendMessage(theme.color3 + "Invalid arguments.");
					sender.sendMessage(theme.color3 + "Usage: " + theme.color1 + "/bday [player]");
					return true;
				}
			}
		}
		return false;
	}
	public Color getColor(int n) {
		List<Color> colors = Lists.newArrayList(Color.AQUA, Color.BLUE, Color.FUCHSIA, Color.LIME, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.TEAL, Color.YELLOW);
		return colors.get(n);
	}
}