package aid.me.ops;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
	
//PLUGIN STARTUP/SHUTDOWN METHODS
	
	//When enabled
	@Override
	public void onEnable() {
		
		this.initPluginAssets();
		
		OpsPlugin.createDataConfig();
		OpsPlugin.initCommands();
		OpsPlugin.initListeners();
		
		getLogger().info("Enabled!");
	}
	
	//When disabled
	@Override
	public void onDisable() {
		getLogger().info("Disabled!");
	}
	
//Registry
	
	//Registers vital Plugin systems
	private void initPluginAssets() {
		OpsPlugin.setPluginMain(this);
		getLogger().info("Assets initialized!");
		return;
	}
	

	/*
	private void initMessager() {
		
		log.info("Messaging Util Initialized!");
	}
	*/
}