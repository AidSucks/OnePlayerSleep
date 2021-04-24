package aid.me.ops.command;

public enum OpsCommandType {
	
	//ENUM 	//NAME
		   	//USAGE
			//PERMISSION
			//DESCRIPTION
			//MAX ARGUMENTS
			//ADMIN COMMAND
	
	//TODO Possibly store in yml or text file instead of an enum
	OPS("ops", 
		"/ops", 
		"ops.help", 
		"Default OPS Command", 
		-1, 
		false
		),
	
	DURATION("duration",
			"/ops duration [set <ticks>]",
			"ops.set.duration",
			"Sets or outputs the current duration in ticks",
			2,
			false
			),
	
	ENABLED("enabled",
			"/ops enabled [set <true|false>]",
			"ops.set.enabled",
			"Sets or outputs the current state of the plugin",
			2,
			false
			),
	
	WEATHER("weather",
			"/ops weather [set <true|false>]",
			"ops.set.weather",
			"Sets or outputs whether or not the weather is affected by sleeping",
			2,
			false
			),
	
	RELOAD("reload",
		   "/ops reload",
		   "ops.admin.reload",
		   "Reloads the OPS data.yml",
		   0,
		   true
		   ),
	
	REVOKE("revoke",
			"/ops revoke <player>",
			"ops.admin.revoke",
			"Forbids the given player from sleeping",
			1,
			true
			);
	
	private String name, usage, permission, description;
	private int maxArgs;
	private boolean isAdmin;
	
	//Definition Constructor
	private OpsCommandType
			(String name, String usage, 
			String permission, String description, 
			int maxArgs, boolean isAdmin) 
	{
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsage() {
		return this.usage;
	}

	public String getPermission() {
		return this.permission;
	}
	
	public String getDescription() {
		return this.description;
	}

	public int maxAllowedArgs() {
		return this.maxArgs;
	}

	public boolean isAdminCmd() {
		return this.isAdmin;
	}

}
