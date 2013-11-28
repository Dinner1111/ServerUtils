package io.github.Dinner1111.ServerUtils.Misc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
 
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
 
public class ConfigMethods {
    private FileConfiguration customConfig = null;
    private File customConfigFile = null;
    private Plugin plg;
    private String fileName;
    private String path;
    public ConfigMethods(String name, String path, Plugin pl){
    	plg = pl;
    	fileName = name;
    	this.path = path;
    }
    public void reloadConfig() {
        if (customConfigFile == null) {
        	customConfigFile = new File(plg.getDataFolder(), path);
        	if(!customConfigFile.exists()) {
        		try {
        			customConfigFile.createNewFile();
        		} catch (IOException e) {
        		    e.printStackTrace();
        		}
        	}
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        InputStream defConfigStream = plg.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            customConfig.setDefaults(defConfig);
        }
    }
    public FileConfiguration getConfig() {
        if (customConfig == null) {
            reloadConfig();
        }
        return customConfig;
    }
    public void saveConfig() {
        if (customConfig == null || customConfigFile == null) {
            return;
        }
        try { getConfig().save(customConfigFile); } catch (IOException ex) {
            plg.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
    }
    public void saveDefaultConfig() {
        if (customConfigFile == null) {
        	try { customConfigFile = new File(plg.getDataFolder(), path); } catch (NullPointerException e) {
        		e.printStackTrace();
        	}
        	if(!customConfigFile.exists()) {
        		try { customConfigFile.createNewFile(); } catch (IOException e) {
        		    e.printStackTrace();
        		}
        	}
        }
        if (!customConfigFile.exists()) {            
             plg.saveResource(fileName, false);
        }
    }
}