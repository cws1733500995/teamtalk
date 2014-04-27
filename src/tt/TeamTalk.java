package tt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;

public class TeamTalk extends JavaPlugin implements Listener{
	
	private HashMap<Player, OnlinePlayer> playerlist = new HashMap<Player, OnlinePlayer>();
	
    @Override
    public void onEnable() {
    	
    	for(Player p: Bukkit.getServer().getOnlinePlayers()){
    		playerlist.put(p,new OnlinePlayer(p));
    	};
    	getServer().getPluginManager().registerEvents(this, this);
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("TeamTalk was successfully disabled.");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("team")) { // If the player typed /basic then do the following...
    		if (args.length == 0){
    			sender.sendMessage("TeamTalk, written custom for CrystalCraft by suBDavis.");
    		}
    		if(args.length == 1){
    			if (args[0].equalsIgnoreCase("t")){
    				toggleMute((Player)sender);
    				sender.sendMessage("You toggled the gray-muted chat.");
    			}
    			else return false;
    		}
    		return true;
    	}
    	return false; 
    }
    
    private void toggleMute(Player p) {
		OnlinePlayer po = playerlist.get(p);
		po.setHasChatMuted(!po.hasChatMuted());
	}

	@EventHandler
    public void onLogin(PlayerLoginEvent event){
    	Player justIn = (Player)event.getPlayer();
    	playerlist.put(justIn, new OnlinePlayer(justIn));
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
    	playerlist.remove(event.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerChat(AsyncPlayerChatEvent m){

    	m.setCancelled(true);
    	
    	Iterator it = playerlist.entrySet().iterator();
    	
    	while (it.hasNext()){
    		Map.Entry pairs = (Map.Entry)it.next();
    		if (((OnlinePlayer) pairs.getValue()).hasChatMuted()){
    			( (CommandSender) pairs.getKey()).sendMessage(ChatColor.GRAY + ((Player) pairs.getKey()).getDisplayName()+ " " + m.getMessage());
    		}
    		else ((CommandSender) pairs.getKey()).sendMessage(((Player) pairs.getKey()).getDisplayName()+ " " + m.getMessage());
    	}
    	
    }
    
    public HashMap<Player, OnlinePlayer> getPlayerList(){
    	return playerlist;
    }
    

}