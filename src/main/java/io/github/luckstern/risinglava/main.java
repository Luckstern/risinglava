package io.github.luckstern.risinglava;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class main extends JavaPlugin implements Listener{
	@Override
	public void onEnable()  {
		getLogger().info("The Rising Lava plugin has been enabled!");
		this.getServer().getPluginManager().registerEvents(this, this);
		Plugin plugin = Bukkit.getPluginManager().getPlugin("risinglava");
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("startlava")) {
        	if (sender.hasPermission("risinglava.startlava")) {
        		BukkitScheduler scheduler = getServer().getScheduler();
        		long delay = Long.parseLong(args[5])*20;
    			getLogger().info(String.valueOf(delay));
        		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
        			int i = Integer.parseInt(args[4]);
                    @Override
                    public void run() {
                    	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                		String command = "fill "+args[0]+" "+i+" "+args[1]+" "+args[2]+" "+i+" "+args[3]+" minecraft:lava";
                		Bukkit.dispatchCommand(console, command);
                    	getLogger().info(String.valueOf(i));
                		i++;
                    }   
                }, 0L, delay);
        		
                return true;
        	}
        	else {
            	sender.sendMessage("You don't have permission to run that command");
            }
        	
    }
    return false;
	}
}
