package aid.me.ops.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.MessageManager;

public class CommandManager implements CommandExecutor{

	//VARIABLES
	private ArrayList<OpsCommand> commands;
	private MessageManager MSG = OpsPlugin.getMessageManager();
	private CommandSender currentPlayer;

	
	//CONSTRUCTOR
	public CommandManager() {
		if(this.commands == null) {
			this.commands = new ArrayList<OpsCommand>();
		}
	}
	
	
	//GETTERS
	public CommandSender getCurrPlayer() {
		if(this.currentPlayer == null) {
			return Bukkit.getServer().getConsoleSender();
		}
		return this.currentPlayer;
	}
	
	public ArrayList<OpsCommand> getCommands() {
		return this.commands;
	}
	
	
	//UTIL
	public void addCommands(OpsCommand[] cmds) {
		for(OpsCommand cmd : cmds) {
			commands.add(cmd);
		}
	}
	
	public void setCurrentPlayer(CommandSender currPlayer) {
		this.currentPlayer = currPlayer;
		return;
	}

	
	
	//MAIN METHOD
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		this.setCurrentPlayer(sender);
		
		boolean isOpsCmd = false;
		OpsCommand command = null;
		
		if(args.length >= 1 && args[0] != null) {
			//Loop through the names of registered commands and 
			for(OpsCommand opscmd : commands) {
				String x = args[0];
				String cmdName = opscmd.getType().getName();
			
				//If the command is found, set it to the var command
				if(x.equalsIgnoreCase(cmdName)) {
					command = opscmd;
					isOpsCmd = true;
					break;
				}
			}
		}
		else {
			command = new OpsCmd();
			isOpsCmd = true;
		}
		
		//Check if this is not a command
		if(!isOpsCmd) return true;
		
		if(!sender.hasPermission(command.getType().getPermission())) {
			MSG.sendMessage("messages.error.permission");
			return true;
		}
		else if(args.length > command.getType().maxAllowedArgs() + 1) {
			MSG.sendMessage("messages.error.toomanyargs");
			return true;
		}
		else {
			//Run the specified command
			command.onCommand(sender, args);
			return true;
		}
		
	}

}