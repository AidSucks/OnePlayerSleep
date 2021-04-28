  
package aid.me.ops.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class OpsCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		sender.sendMessage(ChatColor.DARK_PURPLE + "One Player Sleep Commands:");
		for(OpsCommandType type : OpsCommandType.values()) {
			sender.sendMessage(ChatColor.GREEN + 
					super.cmdConfig.getUsage(type.getLabel()) + " - " +
					super.cmdConfig.getDescription(type.getLabel()));
		}
		return;
	}

}