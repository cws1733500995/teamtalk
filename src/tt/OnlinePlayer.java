package tt;

import org.bukkit.entity.Player;

public class OnlinePlayer{
	
	private Player player;
	private boolean hasTeam;
	private boolean hasChatMuted;
	
	OnlinePlayer(Player p){
		this.player = p;
		this.hasTeam = false;
		this.hasChatMuted = false;
	}
	
	public boolean hasTeam(){
		return hasTeam;
		
	}
	
	public boolean hasChatMuted(){
		return hasChatMuted;
	}
	
	public void setHasChatMuted(boolean b){
		hasChatMuted = b;
	}
	public void setHasTeam(boolean b){
		hasTeam = b;
	}
}
