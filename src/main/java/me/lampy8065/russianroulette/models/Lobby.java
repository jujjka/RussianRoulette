package me.lampy8065.russianroulette.models;

import lombok.Data;
import lombok.Getter;
import me.lampy8065.russianroulette.RussianRoulette;
import me.lampy8065.russianroulette.utils.UT;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Data
public class Lobby {

    /**
     * plugin main class
     */
    @Getter
    private List<Player> playersLobby = new ArrayList<>();
    @Getter
    private static HashMap<Player,Lobby> OwnersHashMap = new HashMap<>();
    @Getter
    private static HashMap<String,Lobby> lobbyHashMap = new HashMap<>();

    private RussianRoulette plugin = RussianRoulette.getPlugin();

    private final Player Owner;
    private final String Name;
    private final int minPlayers;
    private final int maxPlayers;
    private int death;
    private int save;
    private Boolean isGame;
    private Game game;

    /**
     * will join the player and start the game at the same time
     */
    public void JoinLobby(Player player){
        if(!getIsGame()){
            if(playersLobby.size() < maxPlayers){
                playersLobby.add(player);
            }
            if (playersLobby.size() >= minPlayers){
                Game game = new Game(plugin,this);
            }
        }
    }
    /**
     * stop game
     */
    public void stopGame(){
        setIsGame(false);
        sendBarLobbyMessage(String.format(UT.getString("StunMessage.GameOver"), death,save));
        setSave(0);
        setDeath(0);
        this.game = null;
    }
    public void delete(){
        stopGame();
        lobbyHashMap.remove(this);
        lobbyHashMap.remove(getOwner());
        playersLobby.clear();
    }

    public void kickLobby(Player player){
        sendLobbyMessage(String.format(UT.getString("StunMessage.KickPlayer"), player.getDisplayName()));
        playersLobby.remove(player);
    }
    public void sendLobbyMessage(String message){
        Iterator<Player> it = playersLobby.listIterator();
        while(it.hasNext()) {
            it.next().sendMessage(ChatColor.AQUA + message);
        }
    }
    /**
     * I did it very crookedly, but I couldn’t do it differently
     */
    public void sendBarLobbyMessage(String message){
        for (Player player: playersLobby){
            new BukkitRunnable(){
                int i = 2;
                @Override
                public void run() {
                    if (i < 133){
                        i++;
                        String voi = "                                                                                                                                     " + message;
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,TextComponent.fromLegacyText(voi.substring(i,133 + message.length())));
                    }
                }
            }.runTaskTimer(RussianRoulette.getPlugin(RussianRoulette.class),0,1);
        }
    }
}
