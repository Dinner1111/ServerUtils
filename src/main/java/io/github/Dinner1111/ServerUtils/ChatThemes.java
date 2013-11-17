package io.github.Dinner1111.ServerUtils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class ChatThemes {
	Plugin plg;
	Map<ThemeType, ThemeColors> themes = new HashMap<ThemeType, ThemeColors>();
	public ChatThemes(Plugin pl) {
		addThemes();
		plg = pl;
	}
	public enum ThemeType {
		COOL_BLUE,
		DEVILS_DEN,
		JUNGLE_JAM,
		GRAY_GLOOM
	}
	public void addThemes() {
		themes.put(ThemeType.COOL_BLUE, new ThemeColors(ChatColor.AQUA, ChatColor.BLUE, ChatColor.GRAY, ChatColor.DARK_GRAY));
		themes.put(ThemeType.DEVILS_DEN, new ThemeColors(ChatColor.RED, ChatColor.DARK_RED, ChatColor.YELLOW, ChatColor.DARK_GRAY));
		themes.put(ThemeType.JUNGLE_JAM, new ThemeColors(ChatColor.GREEN, ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.DARK_GREEN));
		themes.put(ThemeType.GRAY_GLOOM, new ThemeColors(ChatColor.WHITE, ChatColor.GRAY, ChatColor.DARK_GRAY, ChatColor.BLACK));
	}
	public ThemeColors ThemeColor(ThemeType tt) {
		if (themes.containsKey(tt)) {
			return themes.get(tt);
		} else {
			return ThemeColor(ThemeType.COOL_BLUE);
		}
	}
}
