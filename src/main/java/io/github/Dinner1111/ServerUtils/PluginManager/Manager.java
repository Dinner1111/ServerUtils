package io.github.Dinner1111.ServerUtils.PluginManager;

import java.util.HashMap;
import java.util.Map;

public class Manager {
	Map<String, Boolean> enabled = new HashMap<String, Boolean>();
	public void enablePlugin(String name) {
		if (!enabled.containsKey(name)) enabled.put(name, true);
	}
	public void disablePlugin(String name) {
		if (enabled.containsKey(name)) enabled.put(name, false);
	}
	public boolean checkEnabled(String name) {
		if (enabled.containsKey(name))
			if (enabled.get(name).booleanValue()) return true;
		return false;
	}
	public boolean checkPluginName(String name) {
		switch (name.toLowerCase()) {
		case "boomer":
		case "velocity":
		case "bettercreepers":
		case "whitelister":
			return true;
		default: return false;
		}
	}
}