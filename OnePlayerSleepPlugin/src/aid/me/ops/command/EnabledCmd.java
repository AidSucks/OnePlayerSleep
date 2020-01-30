package aid.me.ops.command;

import org.bukkit.command.CommandSender;

import aid.me.ops.OpsPlugin;

public class EnabledCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		boolean isEnabled;
		
		if(args.length == 1 || args[1] == null || !args[1].equalsIgnoreCase("set")) {
			super.msgMang.sendMessage("messages.success.enabled", sender);
			return;
		}

		if(args.length == 2 || args[2] == null) {
			super.msgMang.sendMessage("messages.error.notenoughargs", sender);
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
			super.msgMang.sendMessage("messages.error.invalidtype", sender);
			return;
		}

			
		OpsPlugin.getDataConfig().set("enabled", isEnabled);
		OpsPlugin.saveDataConfig();
		super.msgMang.sendMessage("messages.success.setenabled", sender);
		return;
	}
	
	@Override
	public String getName() {
		return "enabled";
	}

	@Override
	public String getPermission() {
		return "ops.set.enabled";
	}

	@Override
	public int maxAllowedArgs() {
		return 2;
	}

}