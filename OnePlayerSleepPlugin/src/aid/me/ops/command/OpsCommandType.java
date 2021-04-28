package aid.me.ops.command;

import aid.me.ops.command.admin.*;

public enum OpsCommandType {
	
	OPS("ops", new OpsCmd()), 
	DURATION("duration", new DurationCmd()), 
	ENABLED("enabled", new EnabledCmd()), 
	WEATHER("weather", new WeatherCmd()), 
	RELOAD("reload", new ReloadCmd()), 
	REVOKE("revoke", new RevokeCmd());

	private String label;
	private OpsCommand cmd;
	
	private OpsCommandType(String label, OpsCommand cmd) {
		this.label = label;
		this.cmd = cmd;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public OpsCommand getCmd() {
		return this.cmd;
	}
	
	public static OpsCommandType getByLabel(String l) {
		for(OpsCommandType t : values()) {
			if(t.getLabel().equals(l)) {
				return t;
			}
		}
		return OPS;
	}
	
}
