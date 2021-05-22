package aid.me.ops.command.admin;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import aid.me.ops.command.OpsCommand;

public class RevokeCmd extends OpsCommand{

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		if(args.length == 1) {
			msgMang.sendMessage("messages.error.notenoughargs");
			return;
		}
		
		UUID targetPlayer = null;
		
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if(args[1].equalsIgnoreCase(p.getName())) {
				targetPlayer = p.getUniqueId();
			}
		}
		
		//Check if player wasn't found
		if(targetPlayer == null) {
			super.msgMang.sendMessage("messages.error.invalidplayer");
			return;
		}

		if(playerConfig.getRevokedList().contains(targetPlayer)) {
			super.playerConfig.removePlayer(targetPlayer);
			super.msgMang.sendMessage("messages.success.unrevoke");
		}else {
			super.playerConfig.addPlayer(targetPlayer);
			super.msgMang.sendMessage("messages.success.revoke");
		}
	}

}
