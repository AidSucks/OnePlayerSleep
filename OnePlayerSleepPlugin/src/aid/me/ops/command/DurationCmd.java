package aid.me.ops.command;

import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;

public class DurationCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		if(args.length == 1 || args[1] == null || !args[1].equalsIgnoreCase("set")) {
			super.msgMang.sendMessage("messages.success.duration", sender);
			return;
		}
		
		if(args.length == 2 || args[2] == null) {
			super.msgMang.sendMessage("messages.error.notenoughargs", sender);
			return;
		}
		
		try {
			long duration = Integer.parseInt(args[2]);
			OpsPlugin.getDataConfig().set("sleep_duration", duration);
			OpsPlugin.saveDataConfig();
			super.msgMang.sendMessage("messages.success.setduration", sender);
			return;
		}catch(NumberFormatException e) {
			super.msgMang.sendMessage("messages.error.invalidtype", sender);
			return;
		}
		
	}

	@Override
	public String getName() {
		return "duration";
	}

	@Override
	public String getPermission() {
		return "ops.set.duration";
	}

	@Override
	public int maxAllowedArgs() {
		return 2;
	}

}