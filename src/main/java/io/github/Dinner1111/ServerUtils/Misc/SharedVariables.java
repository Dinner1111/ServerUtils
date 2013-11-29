package io.github.Dinner1111.ServerUtils.Misc;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SharedVariables {
	public boolean isMuted = false;
	public boolean isBroadcasting = false;
	public String m = "";
	public String[] cM = null;
	CommandSender sender;
	Player kicker;
	
	boolean getIsMuted() { return isMuted; }
	public void setIsMuted(boolean b) { isMuted = b; }
	
	boolean getIsBroadcasting() { return isBroadcasting; }
	public void setIsBroadcasting(boolean b) { isBroadcasting = b; }
	
	String getMessage() { return m; }
	void setMessage(String s) { m = s; }
	
	String[] getConsoleMessage() { return cM; }
	void setConsoleMessage(String[] s) { cM = s; }
	
	public Player getKicker() { return kicker; }
	void setKicker(Player p) { kicker = p; }
	
	public CommandSender getSender() { return sender; }
	public void setSender(CommandSender s) { sender = s; }
}
