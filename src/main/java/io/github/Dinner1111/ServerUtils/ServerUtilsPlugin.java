package io.github.Dinner1111.ServerUtils;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerUtilsPlugin extends JavaPlugin {
    MuteListener muteListen = new MuteListener();
    WorldListener worldListen = new WorldListener();
    
    @Override
    public void onEnable() {
        getLogger().info("ServerUtils loaded.");
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(muteListen, this);
        pm.registerEvents(worldListen, this);
        getCommand("util-op").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-deop").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-unwhitelist").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-kick").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-help").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-world").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-list").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-mute").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-broadcast").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-kill").setExecutor(new ServerUtilsCommands(this));
        getCommand("util-restart").setExecutor(new ServerUtilsCommands(this));
        getCommand("a").setExecutor(new ServerUtilsCommands(this));
        getCommand("silent").setExecutor(new ServerUtilsCommands(this));
        getCommand("announcer").setExecutor(new ServerUtilsCommands(this));
    }
    @Override
    public void onDisable() {
        getLogger().info("ServerUtils unloaded.");
    }
}
