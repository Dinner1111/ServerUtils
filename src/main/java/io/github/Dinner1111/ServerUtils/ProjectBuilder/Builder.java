package io.github.Dinner1111.ServerUtils.ProjectBuilder;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Builder extends Thread {
	public void buildRectangle(int x1, int x2, int y1, int y2, int z1, int z2, Player sender, Material block, int delay) throws InterruptedException {
		for (int xPoint = x1; xPoint >= x2; xPoint++) {
			for (int yPoint = y1; yPoint >= y2; yPoint++) {
				for (int zPoint = z1; zPoint >= x2; zPoint++) {
					sender.getWorld().getBlockAt(xPoint, yPoint, zPoint).setType(block);
					try { this.wait(delay); } catch (InterruptedException e) {
						throw new InterruptedException("Cannot complete the rectangle because the thread was interrupted.");
					}
				}
			}
		}
	}
}
