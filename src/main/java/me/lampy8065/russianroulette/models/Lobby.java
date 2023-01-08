package me.lampy8065.russianroulette.models;

import me.lampy8065.russianroulette.Language.LanguagemMgr;
import me.lampy8065.russianroulette.RussianRoulette;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Lobby {

    public List<Player> playersLobby = new ArrayList<>();
    public static List<Lobby> lobbyList = new ArrayList<>();
    int startGame = 3;

    public Player Owner;


    public static Lobby getLobby(String name){
        for(Lobby lobby: lobbyList){
            if(lobby.nameLobby.equals(name)){
                return lobby;
            }
        }
        return null;
    }

    public static Lobby getLobbyByPlayer(Player player){
        for (Lobby lobby: lobbyList){
            if(lobby.playersLobby.contains(player)){
                return lobby;
            }
        }
        return null;
    }

    private String nameLobby;

    private int minPlayers;
    private int maxPlayers;

    public int death;
    public int save;

    public Lobby(String nameLobby, int minPlayers, int maxPlayers) {
        this.nameLobby = nameLobby;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }


    public static void DeleteLobby(Lobby L){
        lobbyList.remove(L);
    }
    public void JoinLobby(Player player){
        if(playersLobby.size() == maxPlayers){
            player.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.FullLobby").toString());
        }
        else {
            playersLobby.add(player);
            sendLobbyMessage(player.getDisplayName() + LanguagemMgr.getLang().get("StunMessage.joinLobby"));
            if(playersLobby.size() >= minPlayers){
                startGame();
        }

        }
    }

    public void startGame(){
        startGame = 3;
        new BukkitRunnable(){

            @Override
            public void run() {
                if(startGame > 0){
                    sendLobbyTittle(LanguagemMgr.getLang().get("StunMessage.GameStart").toString(),LanguagemMgr.getLang().getString("StunMessage.GameStart1")+ startGame);
                    startGame--;
                }
                else if (startGame ==0){
                    sendLobbyTittle(LanguagemMgr.getLang().get("StunMessage.GameStart2").toString(),"");
                    new BukkitRunnable(){

                        @Override
                        public void run() {
                            Game.startGame(Lobby.this);
                        }
                    }.runTaskLater(RussianRoulette.getPlugin(RussianRoulette.class),20);

                    this.cancel();
                }
            }
        }.runTaskTimer(RussianRoulette.getPlugin(RussianRoulette.class),0,20);
    }
    public void stopGame(){
        sendBarLobbyMessage(ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.GameOver").toString() + ChatColor.GOLD + death + ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.PlayerSave") +  ChatColor.GOLD + save);
        save = 0;
        death = 0;
    }
    public void kickLobby(Player player){
        playersLobby.remove(player);
        sendLobbyMessage(player.getDisplayName() + LanguagemMgr.getLang().get("StunMessage.KickPlayer"));
    }
    public void sendLobbyMessage(String message){
        for (Player player: playersLobby){
            player.sendMessage(ChatColor.AQUA + message);
        }
    }
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
    public void sendLobbyTittle(String message,String subtittle){
        for (Player player: playersLobby){
            player.sendTitle(ChatColor.AQUA + message,subtittle,20,20,20);
        }
    }

    public String getNameLobby() {
        return nameLobby;
    }

    public void setNameLobby(String nameLobby) {
        this.nameLobby = nameLobby;
    }
}
