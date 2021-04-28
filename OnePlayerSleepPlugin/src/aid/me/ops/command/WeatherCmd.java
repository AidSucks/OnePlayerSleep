package aid.me.ops.command;

import org.bukkit.command.CommandSender;

public class WeatherCmd extends OpsCommand{
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		boolean isEnabled;
		super.cmdMang.setCurrentPlayer(sender);
		
		if(args.length == 1) {
			super.msgMang.sendMessage("messages.success.weather");
			return;
		}
		
		if(!super.cmdConfig.getSubArgs(OpsCommandType.WEATHER.getLabel()).contains(args[1])) {
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
		super.config.save();
		super.msgMang.sendMessage("messages.success.setweather");
		return;
	}

}
