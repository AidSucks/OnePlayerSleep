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
	public String getName() {
		return "reload";
	}

	@Override
	public String getPermission() {
		return "ops.admin.reload";
	}

	@Override
	public int maxAllowedArgs() {
		return 0;
	}

	@Override
	public boolean isAdminCmd() {
		return false;
	}

}