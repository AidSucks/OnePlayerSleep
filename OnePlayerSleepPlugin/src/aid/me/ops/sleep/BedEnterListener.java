package aid.me.ops.sleep;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.config.OpsDataConfig;
import aid.me.ops.util.config.OpsPlayerConfig;

public class BedEnterListener implements Listener{
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e) {
		
		SleepManager mang = OpsPlugin.getSleepManager();
		OpsDataConfig data = (OpsDataConfig) OpsPlugin.getConfig("data.yml");
		OpsPlayerConfig pCfg = (OpsPlayerConfig) OpsPlugin.getConfig("playerdata.yml");
		
		OpsPlugin.getMessageManager().setRecipient(e.getPlayer());
		
		//Check to see if the plugin is enabled, and the BedResult is OK
		if(!data.getEnabled() || !e.getBedEnterResult().equals(BedEnterResult.OK)) {
			return;
		}
		
		if(pCfg.getRevokedList().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			OpsPlugin.getMessageManager().sendMessage("messages.error.cannotsleep");
			return;
		}

		//Execute sleep cycle code
		mang.addPlayer(e.getPlayer());
		
		if(mang.getSleepingPlayers().size() <= 1) {
			mang.startGlobalSleep(e.getPlayer().getWorld());
		}
			
		return;
	}
	
}
