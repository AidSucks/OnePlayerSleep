package aid.me.ops.sleep;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.ConfigurationManager;
import aid.me.ops.util.MessageManager;

public class BedEnterListener implements Listener{
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e) {
		
		SleepManager mang = OpsPlugin.getSleepManager();
		ConfigurationManager data = OpsPlugin.getConfigManager();
		MessageManager MSG = OpsPlugin.getMessageManager();
		
		//Check to see if the plugin is enabled, and the BedResult is OK
		if(!data.getEnabled() || !e.getBedEnterResult().equals(BedEnterResult.OK)) {
			return;
		}
		
		//Check if the sleep task already exists
		if(mang.getBukkitTask() != null) {
			mang.keepPlayerAsleep((CraftPlayer) e.getPlayer());
			MSG.sendMessage("messages.error.alreadyasleep");
			return;
		}
		
		//Execute sleep cycle code
		mang.startSleep((CraftPlayer) e.getPlayer());
		return;
	}
	
}
