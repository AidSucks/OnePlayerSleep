package aid.me.ops.sleep;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import aid.me.ops.OpsPlugin;

public class BedLeaveListener implements Listener{
	
	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent e) {
		
		CraftPlayer player = (CraftPlayer) e.getPlayer();
		SleepManager mang = OpsPlugin.getSleepManager();
		
		//Find a way to store this variables elsewhere
		//Possibly a Configuration Handler class
		boolean isEnabled = OpsPlugin.getDataConfig().getBoolean("enabled");
		
		//Check to see if either the plugin isn't enabled, or the player or Bukkit Task don't exist yet
		if(mang.getBukkitPlayer() == null || mang.getBukkitTask() == null || !isEnabled) {
			return;
		}
		
		//If the player exists, then store them as a variable
		CraftPlayer storedPlayer = mang.getBukkitPlayer();
		
		//Check if the player leaving a bed is the same player
		if(player.equals(storedPlayer)) {
			//If so, execute code to stop the bukkit task
			mang.stopSleep();
		}
		
		return;
	}
}
