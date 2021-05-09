package aid.me.ops.util.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpsPlayerConfig extends OpsConfiguration{

	public OpsPlayerConfig(String fileName, boolean isResource) {
		super(fileName, isResource);
	}
	
	public ArrayList<UUID> getRevokedList() {
		
		List<String> strIds = this.get().getStringList("revoked_players");
		ArrayList<UUID> playerIds = new ArrayList<UUID>();
		
		//Check if the list is empty
		if(strIds.isEmpty()) {
			return playerIds;
		}
		
		//Convert each item in list to UUID
		for(String s : strIds) {
			UUID id = UUID.fromString(s);
			playerIds.add(id);
		}
		return playerIds;
	}
	
	public void addPlayer(UUID id) {		
		List<String> strIds = this.get().getStringList("revoked_players");
		
		strIds.add(id.toString());
		
		this.get().set("revoked_players", strIds);
		
		this.save();
		return;
	}

	public void removePlayer(UUID id) {
		List<String> strIds = this.get().getStringList("revoked_players");
		
		if(!strIds.contains(id.toString())) {
			return;
		}
		
		strIds.remove(id.toString());
		
		this.get().set("revoked_players", strIds);
		
		this.save();
		return;
	}
}
