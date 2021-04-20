package aid.me.ops.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import aid.me.ops.OpsPlugin;

public class MessageManager {
	
	//Class wide variables
	private MessageBuilder builder;
	private ConfigurationManager config;
	
	//Intialization upon construction
	public MessageManager() {
		this.builder = new MessageBuilder();
		this.config = OpsPlugin.getConfigManager();
	}
	
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
		if(config.getDataConfig().getString(path) != null) {
			msg = config.getDataConfig().getString(path);
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
