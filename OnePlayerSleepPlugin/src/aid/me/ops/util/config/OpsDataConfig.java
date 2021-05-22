package aid.me.ops.util.config;

public class OpsDataConfig extends OpsConfiguration{
	
	public OpsDataConfig(String fileName, boolean isResource) {
		super(fileName, isResource);
	}
	
	public boolean getEnabled() {
		return this.get().getBoolean("enabled");
	}
	
	public boolean getWeather() {
		return this.get().getBoolean("changes_weather");
	}
	
	public long getDuration() {
		return this.get().getLong("sleep_duration");
	}
	
	public void setEnabled(boolean b) {
		this.get().set("enabled", b);
	}
	
	public void setWeather(boolean b) {
		this.get().set("changes_weather", b);
	}
	
	public void setDuration(long l) {
		this.get().set("sleep_duration", l);
	}
	
}
