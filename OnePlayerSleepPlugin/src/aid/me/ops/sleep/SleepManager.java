package aid.me.ops.sleep;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import aid.me.ops.OpsPlugin;
import aid.me.ops.PluginMain;

public class SleepManager {
	
	//VARIABLES
	private PluginMain pl = OpsPlugin.getPlugin();
	private HashMap<CraftPlayer, BukkitTask> sleepingPlayers;
	private BukkitTask bukkitTask; 

	
	//CONSTRUCTOR
	public SleepManager() {
		if(this.sleepingPlayers == null) {
			this.sleepingPlayers = new HashMap<CraftPlayer, BukkitTask>();
		}
	}
	
	//GETTERS
	public HashMap<CraftPlayer, BukkitTask> getSleepingPlayers() {
		return this.sleepingPlayers;
	}

	public BukkitTask getBukkitTask() {
		return this.bukkitTask;
	}
	
	
	//SETTERS
	public void setSleepTicks(int ticks, CraftPlayer pl) {
		pl.getHandle().sleepTicks = ticks;
		return;
	}

	//METHODS
	private void wakeupPlayer(CraftPlayer pl) {
		this.setSleepTicks(99, pl);
		return;
	}
	
	public void startSleep(World world) {
		
		//Schedules a new Bukkit Task to be run after the duration in ticks
		this.bukkitTask = new BukkitRunnable() {
			
			public void run() {
				world.setTime(0);
				
				if(OpsPlugin.getConfigManager().getWeather()) {
					world.setStorm(false);
					world.setThundering(false);
					world.setWeatherDuration(0);
				}
				
				OpsPlugin.getMessageManager().broadcastMessage("messages.success.slept");
				stopSleep();
			}
			
		}.runTaskLater(this.pl, OpsPlugin.getConfigManager().getDuration());
		
		return;
	}
	
	//Interrupts everyone's sleep cycle
	public void stopSleep() {
		
		this.bukkitTask.cancel();
			
		CraftPlayer[] players = sleepingPlayers.keySet().toArray(new CraftPlayer[sleepingPlayers.size()]);
		
		for(CraftPlayer p : players) {
			this.removeSleepingPlayer(p);
		}
		
		sleepingPlayers.clear();
		return;
	}
	
	//TODO If I decide to add a command to allow an operator to kick someone out of bed
	//then, I can just check in the command class whether or not the player is in the sleepingPlayers Map
	public void addSleepingPlayer(CraftPlayer pl) {
		
		BukkitTask tempTimer = new BukkitRunnable() {
	
			public void run() {
				setSleepTicks(0, pl);
			}
			
		}.runTaskTimer(this.pl, 0, 1);
		
		sleepingPlayers.put(pl, tempTimer);
		return;
	}
	
	//Used to remove only one player
	public void removeSleepingPlayer(CraftPlayer pl) {
		
		try {
		
			sleepingPlayers.get(pl).cancel();
			sleepingPlayers.remove(pl);
			
		}catch(IllegalStateException ex) {
			ex.printStackTrace();
		}
		
		this.wakeupPlayer(pl);
		return;
	}
	
}