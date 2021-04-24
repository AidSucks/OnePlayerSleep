package aid.me.ops.util.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import aid.me.ops.OpsPlugin;
import aid.me.ops.PluginMain;

public class OpsConfiguration {

	protected PluginMain plugin = OpsPlugin.getPlugin();
	
	private FileConfiguration configuration;
	private File configurationFile;
	
	protected String fileName;
	protected boolean isResource;
	
	//create() called upon class construction
	public OpsConfiguration(String fileName, boolean isResource) {
		this.fileName = fileName;
		this.isResource = isResource;
		
		if(isResource) {
			loadResource();
		}else {
			create();
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public boolean isResource() {
		return isResource;
	}
	
	public FileConfiguration get() {
		return configuration;
	}
	
	private void create() {
	
		configurationFile = new File(plugin.getDataFolder(), fileName);
		
		if(!configurationFile.exists()) {
			configurationFile.getParentFile().mkdirs();
			plugin.saveResource(fileName, false);
		}
		
		configuration = new YamlConfiguration();
		load();
		plugin.getLogger().info(fileName + " successfully loaded!");
	}
	
	public void save() {
		if(isResource) {
			plugin.saveResource(fileName, true);
			return;
		}
		if(!configurationFile.exists()) {
			create();
		}
		try {
			configuration.save(configurationFile);
		}catch(IOException e) {
			plugin.getLogger().info("Error while saving " + fileName);
			e.printStackTrace();
		}
	}
	
	private void loadResource() {
		configurationFile = new File(fileName);
		try {
			Reader reader = new InputStreamReader(this.plugin.getResource(fileName), "UTF8");
			
			configuration = YamlConfiguration.loadConfiguration(reader);
			
		} catch (UnsupportedEncodingException e) {
			plugin.getLogger().info("Error while loading " + fileName);
			e.printStackTrace();
		}
	}
	
	private void load() {
		try {	
			configuration.load(configurationFile);
		}catch(IOException | InvalidConfigurationException e) {
			plugin.getLogger().info("Error while loading " + fileName);
			e.printStackTrace();
		}
	}
	
	public void reload() {
		if(!configurationFile.exists()) {
			create();
		}
		load();
	}
	
}
