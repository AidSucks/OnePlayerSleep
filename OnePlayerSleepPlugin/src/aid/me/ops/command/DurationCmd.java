package aid.me.ops.command;

import org.bukkit.command.CommandSender;

public class DurationCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		if(args.length == 1) {
			super.msgMang.sendMessage("messages.success.duration");
			return;
		}
		
		if(!super.cmdConfig.getSubArgs(OpsCommandType.DURATION.getLabel()).contains(args[1])) {
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