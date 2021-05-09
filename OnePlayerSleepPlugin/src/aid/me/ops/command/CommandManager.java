package aid.me.ops.command;

import org.bukkit.Bukkit;
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
	private CommandSender currentPlayer;
	
	//GETTERS
	public CommandSender getCurrPlayer() {
		if(this.currentPlayer == null) {
			return Bukkit.getServer().getConsoleSender();
		}
		return this.currentPlayer;
	}
	
	//UTIL
	public void setCurrentPlayer(CommandSender currPlayer) {
		this.currentPlayer = currPlayer;
		return;
	}


	
	//MAIN METHOD
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		OpsCommandType commandType = OpsCommandType.OPS;
		
		//Check if this command is not an ops command
		if(!(label.equalsIgnoreCase(commandType.getLabel()))) {
			sender.sendMessage(label);
			return true;
		}
		
		this.setCurrentPlayer(sender);
		String cmdName = commandType.getLabel();
		
		if(args.length > 0) {
			commandType = OpsCommandType.getByLabel(args[0].toLowerCase());
		}
		
		if(!sender.hasPermission(cmdConfig.getPermission(cmdName))) {
			MSG.sendMessage("messages.error.permission");
			return true;
		}
		
		if(args.length > cmdConfig.getMaxArgs(cmdName) + 1) {
			MSG.sendMessage("messages.error.toomanyargs");
			return true;
		}
		
		if(!(cmdConfig.getSubArgs(cmdName).isEmpty()) && args.length == 2) {
			MSG.sendMessage("messages.error.notenoughargs");
			return true;
		}
		
		//Run the specified command
		commandType.getCmd().onCommand(sender, args);
		return true;
		
	}

}