package aid.me.ops.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.config.OpsDataConfig;

public class MessageManager {
	
	//Class wide variables
	private MessageBuilder builder = new MessageBuilder();
	private OpsDataConfig config = (OpsDataConfig) OpsPlugin.getConfig("data.yml");
	
	private static CommandSender messageRecipient;
	
	//Returns the current recipient, if null, returns the Console as a default
	public CommandSender getRecipient() {
		if(messageRecipient == null) {
			return Bukkit.getConsoleSender();
		}
		return messageRecipient;
	}
	
	//Sets the recipient of the next message
	public void setRecipient(CommandSender sender) {
		messageRecipient = sender;
		return;
	}
	
	//Wrapper methods
	//Broadcasts a message to the server given a yml path
	public void broadcastMessage(String path) {
		Bukkit.getServer().broadcastMessage(formatMessage(getRawMsg(path)));
	}
	
	//Sends a message to the current recipient privately given a yml path
	public void sendMessage(String path) {	
		getRecipient().sendMessage(formatMessage(getRawMsg(path)));
		return;
	}
	
	//Returns the raw string provided a yml path
	public String getRawMsg(String path) {
		String msg = "";
		if(config.get().getString(path) != null) {
			msg = config.get().getString(path);
		}
		return msg;
	}
	
	//Sends back a formatted message given the raw string to format
	public String formatMessage(String msg) {
		
		HashMap<String, String> keyTerms = builder.getMessageMap();
		
		for(String x : keyTerms.keySet()) {
			msg = msg.replace(x, keyTerms.get(x));
		}
		
		msg = formatColor(msg);
		
		return msg;
	}
	
	//Wrapper for formatting color codes
	public String formatColor(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
}
