package io.github.Dinner1111.ServerUtils;

import org.bukkit.ChatColor;

public class ThemeColors {
	public ChatColor getColor1() {
		return color1;
	}
	public void setColor1(ChatColor color1) {
		this.color1 = color1;
	}
	public ChatColor getColor2() {
		return color2;
	}
	public void setColor2(ChatColor color2) {
		this.color2 = color2;
	}
	public ChatColor getColor3() {
		return color3;
	}
	public void setColor3(ChatColor color3) {
		this.color3 = color3;
	}
	public ChatColor getColor4() {
		return color4;
	}
	public void setColor4(ChatColor color4) {
		this.color4 = color4;
	}
	ChatColor color1;
	ChatColor color2;
	ChatColor color3;
	ChatColor color4;
	public ThemeColors(ChatColor color1, ChatColor color2, ChatColor color3, ChatColor color4) {
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.color4 = color4;
	}
}