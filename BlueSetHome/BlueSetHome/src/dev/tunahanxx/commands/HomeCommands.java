package dev.tunahanxx.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import dev.tunahanxx.D;

public class HomeCommands implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	
	@Override
	public boolean onCommand(CommandSender s,
			Command cmd,
			String l,
			String[] a)
	{
		
		Player o = (Player) s;
		
		String UUID = o.getUniqueId().toString();
		
		String playerN = o.getDisplayName();
		
		Location loc = o.getLocation();
				
		FileConfiguration c = D.i.getConfig();
		
		if (s instanceof Player) {
			if (o.hasPermission("bsh.homes")) {
				
				if (l.equalsIgnoreCase("sethome")) {
					if (c.getBoolean("Homes.Data." + UUID + ".Player.Home.Enabled") == true) {
						
						o.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou Homes Is Active!"));
						
						o.sendTitle(" ", ChatColor.translateAlternateColorCodes('&', "&eYou Homes Is Active"));
						
						o.playSound(loc, Sound.ANVIL_LAND, 10, 1);
						
					} else {
						
                        String w = o.getWorld().getName();
						
						float pitch = o.getLocation().getPitch();
						float yaw = o.getLocation().getYaw();
						
						double x = o.getLocation().getX();
						double y = o.getLocation().getY();
						double z = o.getLocation().getZ();
						
						c.set("Homes.Data." + UUID + ".Player.Home.Owner", playerN);
						c.set("Homes.Data." + UUID + ".Player.Home.Enabled", true);
						c.set("Homes.Data." + UUID + ".Player.Home.World", w);
						c.set("Homes.Data." + UUID + ".Player.Home.Pitch", pitch);
						c.set("Homes.Data." + UUID + ".Player.Home.Yaw", yaw);
						c.set("Homes.Data." + UUID + ".Player.Home.X", x);
						c.set("Homes.Data." + UUID + ".Player.Home.Y", y);
						c.set("Homes.Data." + UUID + ".Player.Home.Z", z);
						
						D.i.saveConfig();
						D.i.reloadConfig();
						
						o.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aHome Set Succes!"));
						
						o.sendTitle(" ", ChatColor.translateAlternateColorCodes('&', "&aHome Set Succes!"));
						
						o.playSound(loc, Sound.LEVEL_UP, 10, 1);
						
					}	
				} else if (l.equalsIgnoreCase("home")) {
					if (c.getBoolean("Homes.Data." + UUID + ".Player.Home.Enabled") == true) {
						
						World w = Bukkit.getServer().getWorld(c.getString("Homes.Data." + UUID + ".Player.Home.World"));
						
						float pitch = (float) c.getDouble("Homes.Data." + UUID + ".Player.Home.Pitch");
						float yaw = (float) c.getDouble("Homes.Data." + UUID + ".Player.Home.Yaw");
						
						double x = c.getDouble("Homes.Data." + UUID + ".Player.Home.X");
						double y = c.getDouble("Homes.Data." + UUID + ".Player.Home.Y");
						double z = c.getDouble("Homes.Data." + UUID + ".Player.Home.Z");
						
						o.teleport(new Location (w, x, y, z, (float) pitch, (float) yaw));
						
						D.i.saveConfig();
						D.i.reloadConfig();
						
						o.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aTeleported The Home!!"));
						
						o.sendTitle(" ", ChatColor.translateAlternateColorCodes('&', "&aTeleported The Home!"));
						
						o.playSound(loc, Sound.ENDERMAN_TELEPORT, 10, 1);
						
					} else {
						
						o.sendMessage(ChatColor.translateAlternateColorCodes('&', "You Must Build House First!"));
						
						o.playSound(loc, Sound.ANVIL_LAND, 10, 1);
						
					}		
					
				} else if (l.equalsIgnoreCase("delhome")) {
					if (c.getBoolean("Homes.Data." + UUID + ".Player.Home.Enabled") == true) {
						
						c.set("Homes.Data." + UUID + ".Player.Home.Owner", null);
						c.set("Homes.Data." + UUID + ".Player.Home.Enabled", false);
						c.set("Homes.Data." + UUID + ".Player.Home.World", null);
						c.set("Homes.Data." + UUID + ".Player.Home.Pitch", null);
						c.set("Homes.Data." + UUID + ".Player.Home.Yaw", null);
						c.set("Homes.Data." + UUID + ".Player.Home.X", null);
						c.set("Homes.Data." + UUID + ".Player.Home.Y", null);
						c.set("Homes.Data." + UUID + ".Player.Home.Z", null);
						
						D.i.saveConfig();
						D.i.reloadConfig();
						
						o.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aHome Delete Succes!"));
						
						o.sendTitle(" ", ChatColor.translateAlternateColorCodes('&', "&aHome Delete Succes!"));
						
						o.playSound(loc, Sound.EXPLODE, 10, 1);
						
					} else {
						
						o.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou Don't Have A Home Already!"));
						
						o.playSound(loc, Sound.ANVIL_LAND, 10, 1);
						
					}	
				}
				
			}
		}
		return true;
	}
}