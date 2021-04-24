package aid.me.ops.command;

import org.bukkit.command.CommandSender;

public class ReloadCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		super.cmdMang.setCurrentPlayer(sender);
		
		super.config.reloadDataConfig();
		super.config.saveDataConfig();
		super.msgMang.sendMessage("messages.success.reload");
		return;
	}

	@Override
	public OpsCommandType getType() {
		return OpsCommandType.RELOAD;
	}

}