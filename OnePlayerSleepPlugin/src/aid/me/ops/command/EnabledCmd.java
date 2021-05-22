package aid.me.ops.command;

import org.bukkit.command.CommandSender;

public class EnabledCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		boolean isEnabled;
		
		if(args.length == 1) {
			super.msgMang.sendMessage("messages.success.enabled");
			return;
		}

		if(!super.cmdConfig.getSubArgs(OpsCommandType.ENABLED.getLabel()).contains(args[1])) {
			return;
		}
				
		String x = args[2].toLowerCase();
		
		switch(x) {
		case "true":
			isEnabled = true;
			break;
		case "false":
			isEnabled = false;
			break;
		default:
			super.msgMang.sendMessage("messages.error.invalidtype");
			return;
		}

			
		super.config.setEnabled(isEnabled);
		super.config.save();
		super.msgMang.sendMessage("messages.success.setenabled");
		return;
	}

}