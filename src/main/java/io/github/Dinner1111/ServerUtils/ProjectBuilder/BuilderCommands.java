package io.github.Dinner1111.ServerUtils.ProjectBuilder;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class BuilderCommands implements CommandExecutor {
	ShapeBuilder builder = new ShapeBuilder();
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLine, String[] args) {
		if (cmd.getName().equalsIgnoreCase("builder")) {
			if (sender instanceof Player) {
				if (args.length == 8) {
					if (args[0].equalsIgnoreCase("rectangle")) {
						int[] points = new int[5];
						Player p = Bukkit.getPlayer(sender.getName());
						Material m;
						int delay;
						points[0] = Integer.parseInt(args[1]);
						points[1] = Integer.parseInt(args[2]);
						points[2] = Integer.parseInt(args[3]);
						points[3] = Integer.parseInt(args[4]);
						points[4] = Integer.parseInt(args[5]);
						points[5] = Integer.parseInt(args[6]);
						try { m = Material.valueOf(args[6]); } catch (Exception e) {
							return false;
						}
						try { delay = Integer.parseInt(args[7]); } catch (Exception e) {
							return false;
						}
						try { builder.buildRectangle(points[0], points[1], points[2], points[3], points[4], points[5], p.getWorld(), m, delay); } catch (InterruptedException e) {
							Bukkit.getLogger().log(Level.SEVERE, "Could not start building rectangle because the thread was interrupted.");
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
