package aid.me.ops.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import aid.me.ops.OpsPlugin;
import aid.me.ops.PluginMain;

public class ConfigurationManager {
	
	private PluginMain plugin = OpsPlugin.getPlugin();
	
	//Custom Config File Variables
	private FileConfiguration dataConfig;
	private File dataConfigFile;
	
	
	public boolean getEnabled() {
		return this.getDataConfig().getBoolean("enabled");
	}
	
	public boolean getWeather() {
		return this.getDataConfig().getBoolean("changes_weather");
	}
	
	public long getDuration() {
		return this.getDataConfig().getLong("sleep_duration");
	}
	
	
	
	public void setEnabled(boolean b) {
		this.getDataConfig().set("enabled", b);
	}
	
	public void setWeather(boolean b) {
		this.getDataConfig().set("changes_weather", b);
	}
	
	public void setDuration(long l) {
		this.getDataConfig().set("sleep_duration", l);
	}
	
	
	
	//Data.yml configuration
	
	//Creates data config
	public void createDataConfig() {
		dataConfigFile = new File(plugin.getDataFolder(), "data.yml");
		if(!dataConfigFile.exists()) {
			dataConfigFile.getParentFile().mkdirs();
			plugin.saveResource("data.yml", false);
		}
		
		dataConfig = new YamlConfiguration();
		reloadDataConfig();
		plugin.getLogger().info(dataConfigFile.getName() + " successfully loaded!");
	}
	
	//Returns data config
	public FileConfiguration getDataConfig() {
		return dataConfig;
	}
	
	//Saves data config
	public void saveDataConfig() {
		try {
			if(!dataConfigFile.exists()) {
				createDataConfig();
			}
			dataConfig.save(dataConfigFile);
		}catch(IOException e) {
			plugin.getLogger().info("Error while saving " + dataConfigFile.getName());
			e.printStackTrace();
		}
	}
	
	//Reloads the data config
	public void reloadDataConfig() {
		try {
			if(!dataConfigFile.exists()) {
				createDataConfig();
			}
			dataConfig.load(dataConfigFile);
		}catch(IOException | InvalidConfigurationException e) {
			plugin.getLogger().info("Error while loading " + dataConfigFile.getName());
			e.printStackTrace();
		}
	}
	
	
}
