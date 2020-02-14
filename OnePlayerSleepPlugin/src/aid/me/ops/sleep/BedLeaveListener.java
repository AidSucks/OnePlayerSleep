package aid.me.ops.sleep;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.OpsPlayerData;

public class BedLeaveListener implements Listener{
	
	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent e) {
		
		CraftPlayer player = (CraftPlayer) e.getPlayer();
		OpsPlayerData mang = OpsPlugin.getPlayerData();
		
		boolean isEnabled = OpsPlugin.getConfigManager().getEnabled();
		
		//Check to see if either the plugin isn't enabled, or the player or Bukkit Task don't exist yet
		if(!isEnabled) {
			return;
		}
		
		if(!mang.getSleepingPlayers().containsKey(player)) {
			return;
		}
		
		//execute code to remove the player from the sleeping list
		OpsPlugin.getSleepManager().removeSleepingPlayer(player);
		
		return;
	}
}
