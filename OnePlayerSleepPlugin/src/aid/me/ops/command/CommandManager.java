package aid.me.ops.command;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.MessageManager;
import aid.me.ops.util.OpsPlayerData;

public class CommandManager implements CommandExecutor{

	private ArrayList<OpsCommand> commands;
	private MessageManager MSG = OpsPlugin.getMessageManager();
	private OpsPlayerData pData = OpsPlugin.getPlayerData();

	public CommandManager() {
		if(commands == null) {
			commands = new ArrayList<OpsCommand>();
		}
	}
	
	/* This is now handled in the OpsPlugin class
	 * Obselete
	 * 
	public void initCommands() {
		OpsCommand[] cmds = {new ReloadCmd(), new EnabledCmd(), new WeatherCmd(),
				 new DurationCmd(), new OpsCmd()};
		if(commands == null) commands = new ArrayList<OpsCommand>();
		addCommands(cmds);
	} 
	*/
	
	public void addCommands(OpsCommand[] cmds) {
		for(OpsCommand cmd : cmds) {
			commands.add(cmd);
		}
	}
	
	public ArrayList<OpsCommand> getCommands() {
		return this.commands;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		pData.setCurrentPlayer(sender);
		
		boolean isOpsCmd = false;
		OpsCommand command = null;
		
		if(args.length >= 1 && args[0] != null) {
			//Loop through the names of registered commands and 
			for(OpsCommand opscmd : commands) {
				String x = args[0];
				String cmdName = opscmd.getName();
			
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
		
		if(!sender.hasPermission(command.getPermission())) {
			MSG.sendMessage("messages.error.permission");
			return true;
		}
		else if(args.length > command.maxAllowedArgs() + 1) {
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