package aid.me.ops.command;

import org.bukkit.command.CommandSender;

public class ReloadCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		super.config.reload();
		super.config.save();
		super.msgMang.sendMessage("messages.success.reload");
		return;
	}

}