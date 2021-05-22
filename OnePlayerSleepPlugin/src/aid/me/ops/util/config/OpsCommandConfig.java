package aid.me.ops.util.config;

import java.util.List;

public class OpsCommandConfig extends OpsConfiguration {

	public OpsCommandConfig(String fileName, boolean isResource) {
		super(fileName, isResource);
	}

	public String getName(String cmdName) {
		return this.get().getString(cmdName + ".name");
	}
	
	public String getUsage(String cmdName) {
		return this.get().getString(cmdName + ".usage");
	}
	
	public String getPermission(String cmdName) {
		return this.get().getString(cmdName + ".perm");
	}
	
	public String getDescription(String cmdName) {
		return this.get().getString(cmdName + ".desc");
	}
	
	public int getMaxArgs(String cmdName) {
		return this.get().getInt(cmdName + ".maxargs");
	}
	
	public boolean isAdminCmd(String cmdName) {
		return this.get().getBoolean(cmdName + ".admin");
	}
	
	public List<String> getSubArgs(String cmdName) {
		return this.get().getStringList(cmdName + ".subargs");
	}
}
