  
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
		
		//TODO Implement a better way of displaying all commands
		sender.sendMessage(ChatColor.DARK_PURPLE + "One Player Sleep Commands:");
		for(OpsCommand cmd : commands) {
			sender.sendMessage("/ops " + cmd.getName());
		}
		return;
	}

	@Override
	public String getName() {
		return "ops";
	}

	@Override
	public String getPermission() {
		return "ops.help";
	}

	@Override
	public int maxAllowedArgs() {
		return -1;
	}

}