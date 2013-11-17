package io.github.Dinner1111.ServerUtils.Listeners;

import io.github.Dinner1111.ServerUtils.Misc.CaseManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DeathListener implements Listener {
	CaseManager cm;
	public DeathListener(CaseManager c) {
		cm = c;
	}
	/*@EventHandler
	public void deathListener(PlayerDeathEvent e) {
		EntityType killed = e.getEntityType();
		Player killer = e.getEntity().getKiller();
		String i = cm.weaponFinder(e.getEntity().getKiller().getItemInHand().getType().toString().toLowerCase());
		String deathMessage = cm.messageFinder(i);
		if (killed.equals(EntityType.PLAYER)) {
			Player p = Bukkit.getPlayer(killed.getName());
			if (i != ".") {
				e.setDeathMessage(p.getDisplayName() + ChatColor.GRAY + deathMessage + killer.getDisplayName() + ChatColor.GRAY + "\'s " + i);
			} else {
				e.setDeathMessage(p.getDisplayName() + ChatColor.GRAY + deathMessage + killer.getDisplayName() + ChatColor.GRAY + i);
			}
		}
	}*/
	@EventHandler
	public void voidKill(EntityDamageEvent e) {
		if (e.getEntityType().equals(EntityType.PLAYER) && e.getCause().equals(DamageCause.VOID)) {
			Player p = ((Player) e.getEntity());
			p.setHealth(20);
			p.setExhaustion(0);
			p.setFallDistance(0);
			p.setFireTicks(0);
			p.setFoodLevel(20);
			if (p.getBedSpawnLocation() != null) {
				p.teleport(p.getBedSpawnLocation());
			} else {
				p.teleport(p.getWorld().getSpawnLocation());
			}
			Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.GRAY + " has fallen out of the world!");
			e.setCancelled(true);
		}
	}
	/*@EventHandler
	public void deathListener(PlayerDeathEvent e) {
		if (e.getEntity().getName().equalsIgnoreCase("pepsidawg00")) {
			getServer().broadcastMessage(ChatColor.GRAY + "Pepsi died! Hah!");
			Firework fw = (Firework) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.FIREWORK);
			FireworkMeta fwm = fw.getFireworkMeta();
			fwm.setPower(1);
			fwm.addEffect(FireworkEffect.builder()
					.with(Type.BURST)
					.withColor(Color.BLUE, Color.NAVY)
					.trail(true)
					.flicker(true)
					.build());
			fw.setFireworkMeta(fwm);
		}
	}*/
}
