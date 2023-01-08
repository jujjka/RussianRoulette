package me.lampy8065.russianroulette.Language;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class EngLang {
    private static File file;
    private static FileConfiguration customFile;

    //Finds or generates the custom config file
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("RussianRoulette").getDataFolder(), "EngLang");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                //owww
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try{
            customFile.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save file EngLang");
        }
    }
    public static void setupMessages(){
        setup();

        //Reports messages
        get().set("Messages-report","");
        get().set("Messages-report.permission","Not enough rights");
        get().set("Messages-report.NotLobby","Lobby does not exist");
        get().set("Messages-report.NotPlayer","This player does not exist");
        get().set("Messages-report.LobbyHAs","This lobby already exists!");
        get().set("Messages-report.AlwaysHaveLobby","You are already in the lobby");
        get().set("Messages-report.PlayerNotLobby","Player not in lobby");
        get().set("Messages-report.NotArg","Not enough arguments");
        get().set("Messages-report.YouNotLobby","You are not in the lobby");
        get().set("Messages-report.MaxMinPlayers","The maximum number of players is less than the minimum");
        get().set("Messages-report.FullLobby","The lobby is full");

        //Stun messages
        get().set("StunMessage","");
        get().set("StunMessage.CreateLobby","You have created a Lobby named -");
        get().set("StunMessage.ExitLobby","You left the lobby");
        get().set("StunMessage.Lobby","Lobby - ");
        get().set("StunMessage.Remove","- remove");
        get().set("StunMessage.deleteLobb1","Lobby deleted");
        get().set("StunMessage.Player","Player");
        get().set("StunMessage.RemoveForLobby"," - removed from lobby");
        get().set("StunMessage.ActiveLobbys","Active Lobby: ");
        get().set("StunMessage.GameAgain","You have restarted the game!");
        get().set("StunMessage.OwnerClear","You have removed all regular players!");
        get().set("StunMessage.OwnerClersForPlayer","Admin clears the lobby!");
        get().set("StunMessage.joinLobby"," joined the lobby");
        get().set("StunMessage.GameStart","The death game begins");
        get().set("StunMessage.GameStart1","left before the game ");
        get().set("StunMessage.GameStart2","The deadly game has begun");
        get().set("StunMessage.GameOver","Game over, after the game was killed - ");
        get().set("StunMessage.PlayerSave",", Survived - ");
        get().set("StunMessage.KickPlayer"," left the lobby");
        get().set("StunMessage.Players1","Player -");
        get().set("StunMessage.DeathGame"," - a bullet fell out of a revolver");
        get().set("StunMessage.GameSave","- very lucky and he survived");
        get().set("StunMessage.MessageAgain","To restart the game just click");
        get().set("StunMessage.MessageAgain1","Restart the game");
        get().set("StunMessage.ActiveLobbys1","There are no active lobbies");
        get().set("StunMessage.RestartMessage","If you want to reset the lobby just click");
        get().set("StunMessage.RestartMessage1","Removing all players");
        // Help message
        get().set("HelpMessage","" +
                "joinLobby - joins a lobby //joinLobby NameLobby\n" +
                "leaveLobby - command to leave the lobby\n" +
                "removeLobby -removes Lobby //removeLobby NameLobby\n" +
                "deleteLobby - deletes your lobby\n" +
                "kickPlayerLobby - kicks a player from the lobby //command PlayerName PlayerName\n" +
                "createLobby - creates a lobby //command Name MinPlayers MaxPlayers\n" +
                "listLobby - shows active lobbies \n" +
                "again - starts the game again\n" +
                "deletePlayer - clears the lobby of normal players\n");
        get().options().copyDefaults(true);
        save();
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
