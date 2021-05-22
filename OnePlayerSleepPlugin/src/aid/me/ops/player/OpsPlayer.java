package aid.me.ops.player;

//For future updates, the only import that needs to be updated is this one
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import aid.me.ops.OpsPlugin;

public class OpsPlayer {

	private Player player;
	private BukkitTask sleepTask = null;
	
	public OpsPlayer(Player player) {
		this.player = player;
	}
	
	//Starts the "sleep" cycle which keeps players in bed
	public void sleep() {
		
		if(sleepTask != null) {
			return;
		}
		
		this.sleepTask = new BukkitRunnable() {
			
			public void run() {
				setSleepTicks(0);
			}
			
		}.runTaskTimer(OpsPlugin.getPlugin(), 0, 1);
		
	}
	
	public void wake() {
		
		try {
			
			this.sleepTask.cancel();
			
		}catch(IllegalStateException ex) {
			ex.printStackTrace();
		}
		
		this.setSleepTicks(99);
		return;
	}
	
	private void setSleepTicks(int ticks) {
		CraftPlayer cPlayer = (CraftPlayer) this.player;
		cPlayer.getHandle().sleepTicks = ticks;
		return;
	}
	
	//Getters
	public Player getPlayer() {
		return this.player;
	}
	
	public BukkitTask getTask() {
		return this.sleepTask;
	}
}
