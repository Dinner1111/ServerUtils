package io.github.Dinner1111.ServerUtils;

import org.bukkit.entity.Player;


public class SharedVariables {
	public boolean isMuted = false;
	public boolean isBroadcasting = false;
	public String m = "";
	public String[] cM = null;
	Player kicker;
	
	boolean getIsMuted() { return isMuted; }
	void setIsMuted(boolean b) { isMuted = b; }
	
	boolean getIsBroadcasting() { return isBroadcasting; }
	void setIsBroadcasting(boolean b) { isBroadcasting = b; }
	
	String getMessage() { return m; }
	void setMessage(String s) { m = s; }
	
	String[] getConsoleMessage() { return cM; }
	void setConsoleMessage(String[] s) { cM = s; }
	
	Player getKicker() { return kicker; }
	void setKicker(Player p) { kicker = p; }
}
