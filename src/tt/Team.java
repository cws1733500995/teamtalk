package tt;



import java.util.HashMap;

import org.bukkit.entity.Player;

public class Team{
	
	private long created;
	private HashMap<Player, OnlinePlayer> members;
	private String name;
	private String prefix;
	
	public Team(String name, HashMap<Player, OnlinePlayer> members){
		this.members = members;
		this.name = name;
		this.prefix = "[" + name + "]";
		this.created = System.currentTimeMillis();
	}
}
