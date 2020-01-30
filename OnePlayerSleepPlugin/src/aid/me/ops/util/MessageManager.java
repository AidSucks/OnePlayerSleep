package aid.me.ops.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;

public class MessageManager {
	
	private HashMap<String, String> keyTerms = new HashMap<String, String>();
	
	private void updateMapValues() {
		
		keyTerms.clear();
		String[] terms = {"{plugin}", "{player}", "{enabled}", "{weather}", "{duration}"};
		
		String[] vals = 
			{
				OpsPlugin.getPlugin().getName(),
				OpsPlugin.getCommandManager().getCurrentSender().getName(), 
				OpsPlugin.getDataConfig().get("enabled").toString(), 
				OpsPlugin.getDataConfig().get("changes_weather").toString(), 
				OpsPlugin.getDataConfig().get("sleep_duration").toString()
			};
		
		for(int i = 0; i < terms.length; i++) {
			if(vals[i] == null) {
				keyTerms.put(terms[i], "");
			}
			
			keyTerms.put(terms[i], vals[i]);
		}
		
		return;
	}
	
	public HashMap<String, String> getTermsMap() {
		this.updateMapValues();
		return keyTerms;
	}
	
	public void broadcastMessage(String path) {
		Bukkit.getServer().broadcastMessage(formatMessage(getRawMsg(path)));
	}
	
	public void sendMessage(String path, CommandSender sender) {	
		sender.sendMessage(formatMessage(getRawMsg(path)));
		return;
	}
	
	public String getRawMsg(String path) {
		String msg = "";
		if(OpsPlugin.getDataConfig().getString(path) != null) {
			msg = OpsPlugin.getDataConfig().getString(path);
		}
		return msg;
	}
	
	public String formatMessage(String msg) {
		
		this.updateMapValues();
		
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
