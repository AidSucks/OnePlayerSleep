package aid.me.ops;

import org.bukkit.plugin.PluginManager;

import aid.me.ops.command.CommandManager;
import aid.me.ops.sleep.SleepManager;
import aid.me.ops.util.ConfigurationManager;
import aid.me.ops.util.MessageManager;
import aid.me.ops.command.DurationCmd;
import aid.me.ops.command.EnabledCmd;
import aid.me.ops.command.OpsCmd;
import aid.me.ops.command.OpsCommand;
import aid.me.ops.command.ReloadCmd;
import aid.me.ops.command.WeatherCmd;

import aid.me.ops.sleep.BedEnterListener;
import aid.me.ops.sleep.BedLeaveListener;


public class OpsPlugin {
	
	private static PluginMain plugin;
	
	private static CommandManager cmdMang; 
	private static SleepManager sleepMang;
	private static MessageManager messageMang;
	private static ConfigurationManager configMang;
	
	private OpsPlugin() {}
	
	//Only used once in PluginMain class
	public static void setPluginMain(PluginMain pl) {
		plugin = pl;
	}
	
	public static PluginMain getPlugin() {
		return plugin;
	}
	
	public static ConfigurationManager getConfigManager() {
		if(configMang == null) {
			configMang = new ConfigurationManager();
		}
		return configMang;
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
	
	//Registers all command classes
	public static void initCommands() {
		
		OpsCommand[] cmds = {new ReloadCmd(), new EnabledCmd(), new WeatherCmd(),
				 new DurationCmd(), new OpsCmd()};
		
		getCommandManager().addCommands(cmds);
		getPlugin().getCommand("ops").setExecutor(getCommandManager());
		
		getPlugin().getLogger().info("All commands initialized!");
	}
	
	

	
}