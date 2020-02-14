package aid.me.ops.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;

import aid.me.ops.OpsPlugin;

public class MessageManager {
	
	private ConfigurationManager config = OpsPlugin.getConfigManager();
	private OpsPlayerData pData = OpsPlugin.getPlayerData();
	
	private HashMap<String, String> keyTerms = new HashMap<String, String>();
	
	private void updateMapValues() {
		
		
		keyTerms.clear();
		String[] terms = {"{plugin}", "{player}", "{sPlayer}" , "{enabled}", "{weather}", "{duration}"};
		
		Object[] vals = 
			{
				OpsPlugin.getPlugin().getName(),
				pData.getCurrPlayer().getName(), 
				this.getSleepingString(),
				config.getEnabled(), 
				config.getWeather(), 
				config.getDuration()
			};
		
		for(int i = 0; i < terms.length; i++) {
			if(vals[i] == null) {
				keyTerms.put(terms[i], "");
			}
			
			keyTerms.put(terms[i], vals[i].toString());
		}
		
		return;
	}
	

	public String getSleepingString() {
		
		String text = "";
		int n = pData.getSleepingPlayers().size();
		
		ArrayList<CraftPlayer> pls = new ArrayList<CraftPlayer>(n);
		for(CraftPlayer p : pData.getSleepingPlayers().keySet()) {
			pls.add(p);
		}
		
		if(pls.size() == 1) {
			text = pls.get(0).getName();
			return text;
		}
		
		for(int i = 0; i < pls.size(); i++) {
			if(i == pls.size() - 1) {
				text = text + pls.get(i).getName();
				break;
			}
			text = text + pls.get(i).getName() + ", ";
		}
		
		return text;
	}

	
	public HashMap<String, String> getTermsMap() {
		this.updateMapValues();
		return keyTerms;
	}
	
	public void broadcastMessage(String path) {
		Bukkit.getServer().broadcastMessage(formatMessage(getRawMsg(path)));
	}
	
	public void sendMessage(String path) {	
		pData.getCurrPlayer().sendMessage(formatMessage(getRawMsg(path)));
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
