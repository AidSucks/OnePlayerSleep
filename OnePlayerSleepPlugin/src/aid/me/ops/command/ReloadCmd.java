package aid.me.ops.command;

import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;

public class ReloadCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		OpsPlugin.reloadDataConfig();
		OpsPlugin.saveDataConfig();
		super.msgMang.sendMessage("messages.success.reload", sender);
		return;
	}

	@Override
	public String getName() {
		return "reload";
	}

	@Override
	public String getPermission() {
		return "ops.reload";
	}

	@Override
	public int maxAllowedArgs() {
		return 0;
	}

}