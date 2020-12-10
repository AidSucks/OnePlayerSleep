package aid.me.ops.sleep;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.ConfigurationManager;

public class BedEnterListener implements Listener{
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e) {
		
		SleepManager mang = OpsPlugin.getSleepManager();
		ConfigurationManager data = OpsPlugin.getConfigManager();
		
		//Check to see if the plugin is enabled, and the BedResult is OK
		if(!data.getEnabled() || !e.getBedEnterResult().equals(BedEnterResult.OK)) {
			return;
		}

		//Execute sleep cycle code
		mang.addSleepingPlayer((CraftPlayer) e.getPlayer());
		
		if(mang.getSleepingPlayers().size() <= 1) {
			mang.startSleep(e.getPlayer().getWorld());
		}
			
		return;
	}
	
}
