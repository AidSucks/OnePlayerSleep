package aid.me.ops.util.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import aid.me.ops.OpsPlugin;
import aid.me.ops.command.OpsCommandType;

public class TabCompleteListener implements TabCompleter{
	
	private OpsCommandConfig cmdConfig = (OpsCommandConfig) OpsPlugin.getConfig("cmdproperties.yml");

	//TODO, Rework and rewrite this tab completer. Right now, it only functions as a simple witch case,
	//possibly create a map in cmdproperties.yml that has completions for each field?
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		final int leng = args.length;
		
		List<String> completions = new ArrayList<String>();
		
		switch(leng) {
			
		case 1:
			for(OpsCommandType t : OpsCommandType.values()) {
				if(t == OpsCommandType.OPS) {
					continue;
				}
				completions.add(t.getLabel());
			}
			break;
		case 2:
			if(!cmdConfig.getSubArgs(args[0]).isEmpty()) {
				completions.addAll(cmdConfig.getSubArgs(args[0]));
			}
			break;
		case 3:
			completions.add("true");
			completions.add("false");
			break;
		default:
			break;
		}
		
		return completions;
	}
	
}
