package aid.me.ops.command;

import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;
import aid.me.ops.util.MessageManager;
import aid.me.ops.util.config.OpsCommandConfig;
import aid.me.ops.util.config.OpsDataConfig;

public abstract class OpsCommand {
	
	protected MessageManager msgMang = OpsPlugin.getMessageManager();
	protected OpsDataConfig config = (OpsDataConfig) OpsPlugin.getConfig("data.yml");
	protected OpsCommandConfig cmdConfig = (OpsCommandConfig) OpsPlugin.getConfig("cmdproperties.yml");
	protected CommandManager cmdMang = OpsPlugin.getCommandManager();
	
	public abstract void onCommand(CommandSender sender, String[] args);
}