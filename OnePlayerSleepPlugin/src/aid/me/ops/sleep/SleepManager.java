package aid.me.ops.sleep;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import aid.me.ops.OpsPlugin;
import aid.me.ops.PluginMain;
import aid.me.ops.util.OpsPlayerData;

public class SleepManager {
	
	//Variables
	
	//The amount of sleepticks the bukkitPlayer is currently at
//	private int playerSleepTicks = 0;
	
	private PluginMain pl = OpsPlugin.getPlugin();
	
	private OpsPlayerData plData = OpsPlugin.getPlayerData();
	
	//, taskTimer
	private BukkitTask bukkitTask; 
	
//	private boolean taskCompleted = false;
	
	//Getters
	
	/*
	public int getSleepTicks() {
		return this.playerSleepTicks;
	}
	*/
	
	/*
	public CraftPlayer getBukkitPlayer() {
		return this.bukkitPlayer;
	}
	*/
	
	public BukkitTask getBukkitTask() {
		return this.bukkitTask;
	}
	
	/*
	public BukkitTask getBukkitTimerTask() {
		return this.taskTimer;
	}
	*/
	
	/*
	public boolean taskIsCompleted() {
		return taskCompleted;
	}
	*/
	
	//Setters
	public void setSleepTicks(int ticks, CraftPlayer pl) {
		pl.getHandle().sleepTicks = ticks;
		return;
	}

	//Methods
	public void startSleep(CraftPlayer player) {
		
		
		World world = player.getWorld();
		
		//Schedules a new Bukkit Task to be run after the duration in ticks
		this.bukkitTask = new BukkitRunnable() {
			
			public void run() {
				wakeupPlayer(player);
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
		
		//Starts the timer that keeps a player in bed until the previous task is complete
		this.keepPlayerAsleep(player);
		
		return;
	}
	
	public void keepPlayerAsleep(CraftPlayer pl) {
		
		BukkitTask timer = new BukkitRunnable() {
	
			public void run() {
				setSleepTicks(0, pl);
			}
			
		}.runTaskTimer(this.pl, 0, 1);
		
		this.addSleepingPlayer(pl, timer);
		
		return;
	}
	
	public void wakeupPlayer(CraftPlayer pl) {
		this.setSleepTicks(99, pl);
		return;
	}
	
	public void addSleepingPlayer(CraftPlayer pl, BukkitTask task) {
		
		//TODO If I decide to add a command to allow an operator to kick someone out of bed
		//then, I can just check in the command class whether or not the player is in the sleepingPlayers Map
		
		//Check to see if the player is already in the Map
		if(plData.getSleepingPlayers().containsKey(pl)) {
			return;
		}
		//If not, add them to the Map
		else {
			plData.getSleepingPlayers().put(pl, task);
			return;
		}

	}
	
	public void removeSleepingPlayer(CraftPlayer pl) {
		
		try {
		
			plData.getSleepingPlayers().get(pl).cancel();
			plData.getSleepingPlayers().remove(pl);
			this.wakeupPlayer(pl);
			
		}catch(IllegalStateException ex) {
			ex.printStackTrace();
		}
		
		return;
	}
	
	//Interrupts everyone's sleep cycle
	public void stopSleep() {
		
		try {
			
			this.bukkitTask.cancel();
			this.bukkitTask = null;
			
			if(!plData.getSleepingPlayers().isEmpty()) {
				for(CraftPlayer p : plData.getSleepingPlayers().keySet()) {
					this.removeSleepingPlayer(p);
				}
				plData.getSleepingPlayers().clear();
			}
			
		}catch(IllegalStateException ex) {
			ex.printStackTrace();
		}
		
		return;
	}
	
}