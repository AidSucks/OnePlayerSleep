  
package aid.me.ops.command;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;

public class OpsCmd extends OpsCommand{

	private CommandManager cmdMang = OpsPlugin.getCommandManager();
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		ArrayList<OpsCommand> commands = cmdMang.getCommands();
		
		sender.sendMessage(ChatColor.DARK_PURPLE + "One Player Sleep Commands:");
		for(OpsCommand cmd : commands) {
			sender.sendMessage(ChatColor.GREEN + cmd.getType().getUsage() + 
					" - " + cmd.getType().getDescription());
		}
		return;
	}

	@Override
	public OpsCommandType getType() {
		return OpsCommandType.OPS;
	}

}