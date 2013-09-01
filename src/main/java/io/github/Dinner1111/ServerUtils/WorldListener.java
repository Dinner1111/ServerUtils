package io.github.Dinner1111.ServerUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WorldListener implements Listener {
    @EventHandler 
    public void listen(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        
    }
}
