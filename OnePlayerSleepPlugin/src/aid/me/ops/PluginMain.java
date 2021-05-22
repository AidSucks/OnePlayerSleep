package aid.me.ops;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
	
/* OnePlayerSleep v2.9-1.16.5
 * Created by: Aid
 * Last edited: 5/16/21
 */
	
	//When enabled
	@Override
	public void onEnable() {
		
		this.initPluginAssets();
		
		OpsPlugin.initConfigs();
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
	private void initPluginAssets() {
		OpsPlugin.setPluginMain(this);
		getLogger().info("Assets initialized!");
		return;
	}
}