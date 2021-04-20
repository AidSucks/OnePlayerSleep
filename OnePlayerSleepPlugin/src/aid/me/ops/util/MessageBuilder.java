package aid.me.ops.util;

import java.util.HashMap;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

import aid.me.ops.OpsPlugin;
import aid.me.ops.sleep.SleepManager;

public class MessageBuilder {
	
	private ConfigurationManager config = OpsPlugin.getConfigManager();
	private SleepManager sleepMang = OpsPlugin.getSleepManager();
	private HashMap<String, String> messageMap = new HashMap<String, String>();
	
	//Returns a updated messageMap
	public HashMap<String, String> getMessageMap(){
		this.updateValues();
		return this.messageMap;
	}
	
	//Builds a string based on the amount of players that slept
	public String buildSleepString() {
		
		String finalizedText = "";
		
		CraftPlayer[] players = 
				sleepMang.getSleepingPlayers().keySet().toArray(new CraftPlayer[sleepMang.getSleepingPlayers().size()]);
		
		if(players.length > 1) {
			
			for(int x = 0; x < players.length; x++) {
				//Check for last player
				if(x == players.length - 1) {
					finalizedText.concat("and " + players[x].getName());
					break;
				}
				finalizedText.concat(players[x].getName() + ", ");
			}
		}else {
			finalizedText = players[0].getName();
		}
		
		return finalizedText;
	}
	
	//Logic for updating the messageMap
	private void updateValues() {
		
		String[] values = {
			OpsPlugin.getPlugin().getName(),
			OpsPlugin.getCommandManager().getCurrPlayer().getName(), 
			this.buildSleepString(),
			String.valueOf(config.getEnabled()), 
			String.valueOf(config.getWeather()), 
			String.valueOf(config.getDuration())
		};
		
		final MessageKey[] keys = MessageKey.values();
		this.messageMap.clear();
		
		int x = 0;
		for(MessageKey key : keys) {
			if(values[x] == null) {
				messageMap.put(key.getLabel(), "");
			}else {
				messageMap.put(key.getLabel(), values[x]);
			}
			x++;
		}
	}
}

enum MessageKey {
	
	PLUGIN("{plugin}"),
	PLAYER("{player}"),
	SENDER("{sPlayer}"),
	ENABLED("{enabled}"),
	WEATHER("{weather}"),
	DURATION("{duration}");
	
	private String label;
	
	private MessageKey(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}
