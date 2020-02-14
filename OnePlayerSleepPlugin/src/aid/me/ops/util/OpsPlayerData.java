package aid.me.ops.util;

//import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
//import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class OpsPlayerData {

	private CommandSender currentPlayer;
//	private CraftPlayer sleepingPlayer;
	
	//Not currently used
	//TODO Implement the ability for multiple people to sleep
	private HashMap<CraftPlayer, BukkitTask> sleepingPlayers = null;
	
	public void setCurrentPlayer(CommandSender currPlayer) {
		this.currentPlayer = currPlayer;
		return;
	}

	/*
	public void setSleepingPlayer(CraftPlayer player) {
		this.sleepingPlayer = player;
		return;
	}
	*/
	
	/*
	public CraftPlayer getSleepingPlayer() {
		return this.sleepingPlayer;
	}
	*/
	
	public CommandSender getCurrPlayer() {
		if(this.currentPlayer == null) {
			return Bukkit.getServer().getConsoleSender();
		}
		return this.currentPlayer;
	}
	
	public HashMap<CraftPlayer, BukkitTask> getSleepingPlayers() {
		if(this.sleepingPlayers == null) {
			this.sleepingPlayers = new HashMap<CraftPlayer, BukkitTask>();
		}
		return this.sleepingPlayers;
	}
	
	
	//Not currently used
	
	/*
	public void updatePlayers() {
		
		this.sleepingPlayers.clear();
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(player.isSleeping()) {
				this.sleepingPlayers.add((CraftPlayer) player);
			}
		}
		return;
	}
	*/
}
