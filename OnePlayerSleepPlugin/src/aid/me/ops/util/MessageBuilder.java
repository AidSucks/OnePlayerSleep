package aid.me.ops.util;

import java.util.ArrayList;
import java.util.HashMap;

import aid.me.ops.OpsPlugin;
import aid.me.ops.player.OpsPlayer;
import aid.me.ops.sleep.SleepManager;
import aid.me.ops.util.config.OpsDataConfig;

public class MessageBuilder {
	
	private OpsDataConfig config = (OpsDataConfig) OpsPlugin.getConfig("data.yml");
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
		
		ArrayList<OpsPlayer> players = this.sleepMang.getSleepingPlayers();
		
		if(players.isEmpty()) {
			return finalizedText;
		}
		
		//Cached index of last player
		int lastIndex = players.size() - 1;
		
		for(OpsPlayer p : players) {
			
			//Get player name of current cycle
			String pName = p.getPlayer().getName();
			//Get current index each cycle
			int currIndex = players.indexOf(p);
			
			//Only one player
			if(lastIndex == 0) {
				finalizedText = finalizedText.concat(pName);
				break;
			}
			//Last Player
			if(currIndex == lastIndex) {
				finalizedText = finalizedText.concat("and " + pName);
				break;
			}
			//Any other player
			finalizedText = finalizedText.concat(pName + ", ");
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
