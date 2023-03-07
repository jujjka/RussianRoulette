package me.lampy8065.russianroulette.events;

import me.lampy8065.russianroulette.RussianRoulette;
import me.lampy8065.russianroulette.models.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

public class LeaveEvent implements Listener {

    private final RussianRoulette pl;


    public LeaveEvent(RussianRoulette plugin){
        this.pl = plugin;
    }

    @EventHandler
    public void leave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        Lobby lobby = Lobby.getLobbyByPlayer(player);
        if(lobby != null){
            lobby.kickLobby(player);
        }
    }
}
