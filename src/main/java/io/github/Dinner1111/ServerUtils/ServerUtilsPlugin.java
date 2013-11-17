package io.github.Dinner1111.ServerUtils;

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
	BlockListener blockListen = new BlockListener(this);
	JoinListener joinListen = new JoinListener(this, start, config);
	SpawnListener spawnListen = new SpawnListener(this);
	LeaveListener leaveListen = new LeaveListener(this, shared, config);
	CommandPreprocessListener cpListen = new CommandPreprocessListener(this, shared, start);
	InventoryClickListener invListen = new InventoryClickListener(this, config);
	
	@Override
	public void onEnable() {
		File f = getDataFolder();
		if(!f.exists()){
		 Bukkit.broadcastMessage("DATA FOLDER DOES NOW EXIST");
		}
		getLogger().info("ServerUtils loaded.");
		if (!(new File(getDataFolder(), "config.yml")).exists()) {
            saveDefaultConfig();
		}
		if (!(new File(getDataFolder(), "players.yml")).exists()) {
            config.saveDefaultConfig();
		}
		if (!(new File("log.txt")).exists()) {
			File logger = new File("log.txt");
			logger.setWritable(true);
		}
        reloadConfig();
        start.splitter(true, true, true, true);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(muteListen, this);
		pm.registerEvents(blockListen, this);
		pm.registerEvents(joinListen, this);
		pm.registerEvents(spawnListen, this);
		pm.registerEvents(cpListen, this);
		pm.registerEvents(leaveListen, this);
		pm.registerEvents(deathListen, this);
		pm.registerEvents(invListen, this);
		getCommand("util-op").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-deop").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-unwhitelist").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-kick").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-help").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-world").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-list").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-mute").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-broadcast").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-kill").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-spawn").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-protect").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-version").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-reload").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-alert").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("pepsi").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
		getCommand("util-config").setExecutor(new ServerUtilsCommands(this, shared, start, cm, config));
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
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, threads, (getConfig().getInt("announcement.delay") * 20 * 60), (getConfig().getInt("announcement.delay") * 20 * 60));
	}
	@Override
	public void onDisable() {
		getLogger().info("ServerUtils unloaded.");
	}
}