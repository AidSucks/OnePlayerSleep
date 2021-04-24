package aid.me.ops.command;

import org.bukkit.command.CommandSender;

public class DurationCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		super.cmdMang.setCurrentPlayer(sender);
		
		if(args.length == 1 || args[1] == null || !args[1].equalsIgnoreCase("set")) {
			super.msgMang.sendMessage("messages.success.duration");
			return;
		}
		
		if(args.length == 2 || args[2] == null) {
			super.msgMang.sendMessage("messages.error.notenoughargs");
			return;
		}
		
		try {
			long duration = Integer.parseInt(args[2]);
			super.config.setDuration(duration);
			super.config.save();
			super.msgMang.sendMessage("messages.success.setduration");
			return;
		}catch(NumberFormatException e) {
			super.msgMang.sendMessage("messages.error.invalidtype");
			return;
		}
		
	}

}