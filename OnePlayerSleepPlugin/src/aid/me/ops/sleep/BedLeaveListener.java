package aid.me.ops.sleep;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.config.OpsDataConfig;

public class BedLeaveListener implements Listener{
	
	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent e) {

		SleepManager mang = OpsPlugin.getSleepManager();
		OpsDataConfig data = (OpsDataConfig) OpsPlugin.getConfig("data.yml");
		
		//Check to see if either the plugin isn't enabled, or the player or Bukkit Task don't exist yet
		if(!data.getEnabled()) {
			return;
		}

		if(mang.getSleepingPlayers().size() <= 1) {
			mang.stopSleep();
		}
		else{
			mang.removeSleepingPlayer((CraftPlayer) e.getPlayer());
		}

		return;
	}
}
