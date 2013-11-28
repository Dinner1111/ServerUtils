package io.github.Dinner1111.ServerUtils;

import io.github.Dinner1111.ServerUtils.Listeners.DeathListener;
import io.github.Dinner1111.ServerUtils.Listeners.InventoryClickListener;
import io.github.Dinner1111.ServerUtils.Listeners.JoinListener;
import io.github.Dinner1111.ServerUtils.Listeners.LeaveListener;
import io.github.Dinner1111.ServerUtils.Listeners.MuteListener;
import io.github.Dinner1111.ServerUtils.Misc.CannoneerRunnable;
import io.github.Dinner1111.ServerUtils.Misc.CaseManager;
import io.github.Dinner1111.ServerUtils.Misc.ConfigMethods;
import io.github.Dinner1111.ServerUtils.Misc.MiscCommands;
import io.github.Dinner1111.ServerUtils.Misc.SharedVariables;
import io.github.Dinner1111.ServerUtils.Misc.BetterCreepers.ExplosionListener;
import io.github.Dinner1111.ServerUtils.Misc.RageQuit.RageQuitCommands;
import io.github.Dinner1111.ServerUtils.Misc.Velocity.VelocityCommands;
import io.github.Dinner1111.ServerUtils.Misc.Whitelister.Whitelister;
import io.github.Dinner1111.ServerUtils.ProjectBuilder.Scripts;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerUtilsPlugin extends JavaPlugin {
	SharedVariables shared = new SharedVariables();
	ConfigMethods config = new ConfigMethods("players.yml", "players.yml", this);
	Threads threads = new Threads(this, config);
	Startup start = new Startup(this, config);
	CannoneerRunnable cr = new CannoneerRunnable(this, null);
	CaseManager cm = new CaseManager(start, this);
	DeathListener deathListen = new DeathListener(cm);
	MuteListener muteListen = new MuteListener(this, shared, start, config);
	JoinListener joinListen = new JoinListener(this, start, config);
	LeaveListener leaveListen = new LeaveListener(this, shared, config);
	SpawnerRunnable sr = new SpawnerRunnable();
	InventoryClickListener invListen = new InventoryClickListener(this, config);
	
	@Override
	public void onEnable() {
		getLogger().info("ServerUtils loaded.");
		if (!(new File(getDataFolder(), "config.yml")).exists()) {
            saveDefaultConfig();
		}
		if (!(new File(getDataFolder(), "players.yml")).exists()) {
            config.saveDefaultConfig();
		}
        reloadConfig();
        start.splitter(true, true, true, true);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(muteListen, this);
		pm.registerEvents(joinListen, this);
		pm.registerEvents(leaveListen, this);
		pm.registerEvents(deathListen, this);
		pm.registerEvents(invListen, this);
		pm.registerEvents(new ExplosionListener(), this);
		pm.registerEvents(new Whitelister(this), this);
		getCommand("util-op").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-deop").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-unwhitelist").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-kick").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-help").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-world").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-list").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-mute").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-broadcast").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-kill").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-spawn").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-protect").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-version").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-reload").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-alert").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("pepsi").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("util-config").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config, sr));
		getCommand("script").setExecutor(new Scripts(this));
		getCommand("silent").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("announcer").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("hide").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("run").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("railgun").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("a").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("love").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("whirl").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("purge").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("bday").setExecutor(new MiscCommands(this, shared, cr, config));
		getCommand("rq").setExecutor(new RageQuitCommands());
		getCommand("vel").setExecutor(new VelocityCommands());
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, threads, (getConfig().getInt("announcement.delay") * 20 * 60), (getConfig().getInt("announcement.delay") * 20 * 60));
	}
	@Override
	public void onDisable() {
		getLogger().info("ServerUtils unloaded.");
	}
}