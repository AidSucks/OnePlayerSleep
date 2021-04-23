package aid.me.ops.command.admin;

import org.bukkit.command.CommandSender;

import aid.me.ops.command.OpsCommand;

public class RevokeCmd extends OpsCommand{

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		
		
	}

	@Override
	public String getName() {
		return "revoke";
	}

	@Override
	public String getPermission() {
		return "ops.admin.revoke";
	}

	@Override
	public int maxAllowedArgs() {
		return 1;
	}

	@Override
	public boolean isAdminCmd() {
		return true;
	}

}
