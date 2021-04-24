package aid.me.ops.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.config.OpsDataConfig;

public class MessageManager {
	
	//Class wide variables
	private MessageBuilder builder = new MessageBuilder();
	private OpsDataConfig config = (OpsDataConfig) OpsPlugin.getConfig("data.yml");
	
	//Wrapper methods
	public void broadcastMessage(String path) {
		Bukkit.getServer().broadcastMessage(formatMessage(getRawMsg(path)));
	}
	
	public void sendMessage(String path) {	
		OpsPlugin.getCommandManager().getCurrPlayer().sendMessage(formatMessage(getRawMsg(path)));
		return;
	}
	
	public String getRawMsg(String path) {
		String msg = "";
		if(config.get().getString(path) != null) {
			msg = config.get().getString(path);
		}
		return msg;
	}
	
	public String formatMessage(String msg) {
		
		HashMap<String, String> keyTerms = builder.getMessageMap();
		
		for(String x : keyTerms.keySet()) {
			msg = msg.replace(x, keyTerms.get(x));
		}
		
		msg = formatColor(msg);
		
		return msg;
	}
	
	public String formatColor(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
}
