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
		if(label.toLowerCase() != commandType.getLabel()) return true;
		
		this.setCurrentPlayer(sender);
		
		if(args[0] != null) {
			for(OpsCommandType type : OpsCommandType.values()) {
				if(args[0].toLowerCase() == cmdConfig.getName(type.getLabel())) {
					commandType = type;
				}
			}
		}
		
		if(!sender.hasPermission(cmdConfig.getPermission(commandType.getLabel()))) {
			MSG.sendMessage("messages.error.permission");
			return true;
		}
		else if(args.length > cmdConfig.getMaxArgs(commandType.getLabel()) + 1) {
			MSG.sendMessage("messages.error.toomanyargs");
			return true;
		}
		else {
			//Run the specified command
			commandType.getCmd().onCommand(sender, args);
			return true;
		}
		
	}

}