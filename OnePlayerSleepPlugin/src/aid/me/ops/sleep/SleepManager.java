package aid.me.ops.sleep;

import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import aid.me.ops.OpsPlugin;
import aid.me.ops.PluginMain;
import aid.me.ops.player.OpsPlayer;
import aid.me.ops.util.config.OpsDataConfig;

public class SleepManager {
	
	//VARIABLES
	private PluginMain pl = OpsPlugin.getPlugin();
	private static ArrayList<OpsPlayer> sleepingPlayers;
	private BukkitTask globalSleepTask;
	private OpsDataConfig dataConfig = (OpsDataConfig) OpsPlugin.getConfig("data.yml");
	
	//CONSTRUCTOR
	public SleepManager() {
		//Initialize list
		if(sleepingPlayers == null) {
	    	sleepingPlayers = new ArrayList<OpsPlayer>();
	    }
	}
	
	//GETTERS
	public ArrayList<OpsPlayer> getSleepingPlayers() {
		return sleepingPlayers;
	}

	public BukkitTask getGlobalTask() {
		return this.globalSleepTask;
	}
	
	public void startGlobalSleep(World world) {
		
	    //Schedules a new Bukkit Task to be run after the duration in ticks
	    this.globalSleepTask = new BukkitRunnable() {
			
		public void run() {
		    world.setTime(0);
				
		    if(dataConfig.getWeather()) {
			world.setStorm(false);
			world.setThundering(false);
			world.setWeatherDuration(0);
		    }
			
		    OpsPlugin.getMessageManager().broadcastMessage("messages.success.slept");
		    stopGlobalSleep();
		}

	    }.runTaskLater(this.pl, dataConfig.getDuration());
	
	    return;
	}
	
	//Interrupts everyone's sleep cycle
	public void stopGlobalSleep() {
		
		this.globalSleepTask.cancel();
			
		for(OpsPlayer player : sleepingPlayers) {
			player.wake();
		}
		
		sleepingPlayers.clear();
		return;
	}
	
	//TODO If I decide to add a command to allow an operator to kick someone out of bed
	//then, I can just check in the command class whether or not the player is in the sleepingPlayers Map
	public void addPlayer(Player p) {
		OpsPlayer player = new OpsPlayer(p);
		sleepingPlayers.add(player);
		player.sleep();
		return;
	}
	
	//Used to remove only one player
	public void removePlayer(Player p) {
		for(OpsPlayer player : sleepingPlayers) {
			if(player.getPlayer() == p) {
				player.wake();
				sleepingPlayers.remove(player);
				return;
			}
		}
	}
	
}