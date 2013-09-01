package io.github.Dinner1111.ServerUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteListener implements Listener {
    private boolean canTalk;
    private boolean isBroadcasting;
    boolean hasSent = false;
    private String m = "";
    
    @EventHandler
    public void listen(AsyncPlayerChatEvent e) {
        if (canTalk == false) {
            if (!e.getPlayer().isOp()) {
                e.setCancelled(true);
            }
        }
        if (isBroadcasting == true) {
            if (e.getPlayer().isOp()) {
                m = e.getMessage();
                e.setCancelled(true);
                if (m != "") {
                    Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Broadcast" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + m);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }
    boolean getCanTalk() { return canTalk; }
    void setCanTalk(Boolean b) { canTalk = b; }
    
    boolean getIsBroadcasting() { return isBroadcasting; }
    void setIsBroadcasting(Boolean b) { isBroadcasting = b; }
    
    String getM() { return m; }
    void setM(String s) { m = s; }
}
