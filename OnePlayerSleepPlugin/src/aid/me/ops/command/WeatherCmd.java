package aid.me.ops.command;

import org.bukkit.command.CommandSender;

public class WeatherCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		boolean isEnabled;
		super.cmdMang.setCurrentPlayer(sender);
		
		if(args.length == 1 || args[1] == null || !args[1].equalsIgnoreCase("set")) {
			super.msgMang.sendMessage("messages.success.weather");
			return;
		}
		
		if(args.length == 2 || args[2] == null) {
			super.msgMang.sendMessage("messages.error.notenoughargs");
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
		
		super.config.setWeather(isEnabled);
		super.config.saveDataConfig();
		super.msgMang.sendMessage("messages.success.setweather");
		return;
	}

	@Override
	public OpsCommandType getType() {
		return OpsCommandType.WEATHER;
	}

}
