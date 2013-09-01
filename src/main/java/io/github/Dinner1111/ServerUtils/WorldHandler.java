package io.github.Dinner1111.ServerUtils;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class WorldHandler implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
        if (cmd.getName().equalsIgnoreCase("register")) {
            Player p = (Player) sender;
            if (p.getWorld().getName().equalsIgnoreCase("default")) {
                
            }
        }
        return true;
    }
}
