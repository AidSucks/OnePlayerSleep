package aid.me.ops.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.MessageManager;
import aid.me.ops.util.config.OpsCommandConfig;

public class CommandManager implements CommandExecutor{

	//VARIABLES
	private MessageManager MSG = OpsPlugin.getMessageManager();
	private OpsCommandConfig cmdConfig = (OpsCommandConfig) OpsPlugin.getConfig("cmdproperties.yml");
	
	//MAIN METHOD
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		OpsCommandType commandType = OpsCommandType.OPS;
		
		//Check if this command is not an ops command
		if(!(label.equalsIgnoreCase(commandType.getLabel()))) {
			sender.sendMessage(label);
			return true;
		}
		
		//Set current recipient
		MSG.setRecipient(sender);
		
		//Find command derived from args[0], if not found, return type OPS
		if(args.length > 0) {
			commandType = OpsCommandType.getByLabel(args[0].toLowerCase());
		}
		
		//Cache the command's name
		String cmdName = commandType.getLabel();
		
		//Check if the player has permission
		if(!sender.hasPermission(cmdConfig.getPermission(cmdName))) {
			MSG.sendMessage("messages.error.permission");
			return true;
		}
		
		//Check if there's more args than max allowed flor this command
		if(args.length > cmdConfig.getMaxArgs(cmdName) + 1) {
			MSG.sendMessage("messages.error.toomanyargs");
			return true;
		}
		
		//Check for not enough args
		if(!(cmdConfig.getSubArgs(cmdName).isEmpty()) && args.length == 2) {
			MSG.sendMessage("messages.error.notenoughargs");
			return true;
		}
		
		//Run the specified command
		commandType.getCmd().onCommand(sender, args);
		
		//Reset the recipient
		MSG.setRecipient(null);
		return true;
		
	}

}