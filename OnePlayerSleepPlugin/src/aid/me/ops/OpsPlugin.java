package aid.me.ops;
import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;

import aid.me.ops.command.CommandManager;
import aid.me.ops.command.DurationCmd;
import aid.me.ops.command.EnabledCmd;
import aid.me.ops.command.OpsCmd;
import aid.me.ops.command.OpsCommand;
import aid.me.ops.command.ReloadCmd;
import aid.me.ops.command.WeatherCmd;
import aid.me.ops.sleep.BedEnterListener;
import aid.me.ops.sleep.BedLeaveListener;
import aid.me.ops.sleep.SleepManager;
import aid.me.ops.util.MessageManager;

public class OpsPlugin {
	
	private static PluginMain plugin;
	
	private static CommandManager cmdMang; 
	private static SleepManager sleepMang;
	private static MessageManager messageMang;
	
	//Custom Config File Variables
	private static FileConfiguration dataConfig;
	private static File dataConfigFile;
	
	private OpsPlugin() {}
	
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
	
	
	//Data.yml configuration
	
	//Creates data config
	public static void createDataConfig() {
		dataConfigFile = new File(getPlugin().getDataFolder(), "data.yml");
		if(!dataConfigFile.exists()) {
			dataConfigFile.getParentFile().mkdirs();
			getPlugin().saveResource("data.yml", false);
		}
		
		dataConfig = new YamlConfiguration();
		reloadDataConfig();
		getPlugin().getLogger().info(dataConfigFile.getName() + " successfully loaded!");
	}
	
	//Returns data config
	public static FileConfiguration getDataConfig() {
		return dataConfig;
	}
	
	//Saves data config
	public static void saveDataConfig() {
		try {
			if(!dataConfigFile.exists()) {
				createDataConfig();
			}
			dataConfig.save(dataConfigFile);
		}catch(IOException e) {
			getPlugin().getLogger().info("Error while saving " + dataConfigFile.getName());
			e.printStackTrace();
		}
	}
	
	//Reloads the data config
	public static void reloadDataConfig() {
		try {
			if(!dataConfigFile.exists()) {
				createDataConfig();
			}
			dataConfig.load(dataConfigFile);
		}catch(IOException | InvalidConfigurationException e) {
			getPlugin().getLogger().info("Error while loading " + dataConfigFile.getName());
			e.printStackTrace();
		}
	}
	
}