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
		
		boolean isEnabled = OpsPlugin.getConfigManager().getEnabled();
		
		//Check to see if either the plugin isn't enabled, or the player or Bukkit Task don't exist yet
		if(mang.getBukkitPlayer() == null || mang.getBukkitTask() == null || !isEnabled) {
			return;
		}
		
		//Check if the player leaving a bed is the same player
		if(player.equals(mang.getBukkitPlayer())) {
			//If so, execute code to stop the bukkit task
			mang.stopSleep();
		}
		
		return;
	}
}
