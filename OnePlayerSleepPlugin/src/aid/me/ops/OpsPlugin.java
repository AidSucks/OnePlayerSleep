package aid.me.ops;

import java.util.ArrayList;

import org.bukkit.plugin.PluginManager;

import aid.me.ops.command.CommandManager;
import aid.me.ops.sleep.SleepManager;
import aid.me.ops.util.MessageManager;
import aid.me.ops.util.config.OpsCommandConfig;
import aid.me.ops.util.config.OpsConfiguration;
import aid.me.ops.util.config.OpsDataConfig;
import aid.me.ops.util.config.OpsPlayerConfig;
import aid.me.ops.util.config.TabCompleteListener;
import aid.me.ops.sleep.BedEnterListener;
import aid.me.ops.sleep.BedLeaveListener;

public class OpsPlugin {
	
	private static PluginMain plugin;
	
	//ASSET VARIABLES
	private static CommandManager cmdMang; 
	private static SleepManager sleepMang;
	private static MessageManager messageMang;
	private static ArrayList<OpsConfiguration> configurations;
	
	private OpsPlugin() {}
	
	//Only used once in PluginMain class
	public static void setPluginMain(PluginMain pl) {
		plugin = pl;
	}
	
	public static PluginMain getPlugin() {
		return plugin;
	}
	
	public static CommandManager getCommandManager() {
		if(cmdMang == null) {
			cmdMang = new CommandManager();
		}
		return cmdMang;
	}
	
	public static SleepManager getSleepManager() {
		if(sleepMang == null) {
			sleepMang = new SleepManager();
		}
		return sleepMang;
	}

	
	public static MessageManager getMessageManager() {
		if(messageMang == null) {
			messageMang = new MessageManager();
		}
		return messageMang;
	}
	
	public static OpsConfiguration getConfig(String fileName) {
		for(OpsConfiguration cfg : configurations) {
			if(cfg.getFileName() == fileName) {
				return cfg;
			}
		}
		return null;
	}
		
//Registry Methods
	
	//Registers all listeners
	public static void initListeners() {
		
		//Gets the sever-wide plugin manager
		PluginManager pmg = getPlugin().getServer().getPluginManager();
		
		//Registers all and any events
		pmg.registerEvents(new BedEnterListener(), getPlugin());
		pmg.registerEvents(new BedLeaveListener(), getPlugin());
		
		//Notifies of successful initialization
		getPlugin().getLogger().info("Events initialized!");
		return;
	}
	
	public static void initConfigs() {
		
		configurations = new ArrayList<OpsConfiguration>();
		
		OpsConfiguration[] configs = {
				new OpsDataConfig("data.yml", false), 
				new OpsCommandConfig("cmdproperties.yml", true),
				new OpsPlayerConfig("playerdata.yml", false)};
		
		for(OpsConfiguration cfg : configs) {
			configurations.add(cfg);
		}
		
		getPlugin().getLogger().info("All configurations initialized!");
	}
	
	//Registers all command classes
	public static void initCommands() {
		
		getPlugin().getCommand("ops").setExecutor(getCommandManager());
		getPlugin().getCommand("ops").setTabCompleter(new TabCompleteListener());
		
		getPlugin().getLogger().info("All commands initialized!");
	}
}