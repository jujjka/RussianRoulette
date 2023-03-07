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
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("RussianRouletteGame").getDataFolder(), "EngLang");

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
        get().set("Messages-report.permission","§4Not enough rights");
        get().set("Messages-report.NotLobby","§4Lobby [%s] does not exist");
        get().set("Messages-report.NotPlayer","§4Player [%s] does not exist");
        get().set("Messages-report.LobbyHAs","§4Lobby [%s] already exists!");
        get().set("Messages-report.AlwaysHaveLobby","§4You are already in the lobby");
        get().set("Messages-report.PlayerNotLobby","§4Player [%s] not in lobby");
        get().set("Messages-report.NotArg","§4Not enough arguments");
        get().set("Messages-report.YouNotLobby","§4You are not in the lobby");
        get().set("Messages-report.MaxMinPlayers","§4The maximum number of players is less than the minimum");
        get().set("Messages-report.FullLobby","§4The lobby [%s] is full");

        //Stun messages
        get().set("StunMessage","");
        get().set("StunMessage.CreateLobby","§3You have created a Lobby named - %s");
        get().set("StunMessage.ExitLobby","§3You left the lobby");
        get().set("StunMessage.deleteLobb1","§3Lobby deleted");
        get().set("StunMessage.RemoveForLobby","§3Player [%s] - removed from lobby");
        get().set("StunMessage.ActiveLobbys","§3Active Lobby: ");
        get().set("StunMessage.GameAgain","§3You have restarted the game!");
        get().set("StunMessage.OwnerClear","§3You have removed all regular players!");
        get().set("StunMessage.OwnerClersForPlayer","§3Admin clears the lobby!");
        get().set("StunMessage.joinLobby","§3Player [%s] joined the lobby");
        get().set("StunMessage.GameStart","§3The death game begins");
        get().set("StunMessage.GameStart1","§3left before the game ");
        get().set("StunMessage.GameStart2","§3The deadly game has begun");
        get().set("StunMessage.GameOver","§3Game over, after the game was killed [%s], Survived [%s]");
        get().set("StunMessage.KickPlayer","§3Player [%s] left the lobby");
        get().set("StunMessage.DeathGame","§3Player [%s] - a bullet fell out of a revolver");
        get().set("StunMessage.GameSave","§3Player [%s] - very lucky and he survived");
        get().set("StunMessage.MessageAgain","§3To restart the game just click");
        get().set("StunMessage.MessageAgain1","§3Restart the game");
        get().set("StunMessage.ActiveLobbys1","§3There are no active lobbies");
        get().set("StunMessage.RestartMessage","§3If you want to reset the lobby just click");
        get().set("StunMessage.RestartMessage1","§3Removing all players");
        // Help message
        get().set("HelpMessage","" +
                "§4joinLobby - joins a lobby //joinLobby NameLobby\n" +
                "§cleaveLobby - command to leave the lobby\n" +
                "§6removeLobby -removes Lobby //removeLobby NameLobby\n" +
                "§edeleteLobby - deletes your lobby\n" +
                "§2kickPlayerLobby - kicks a player from the lobby //command PlayerName PlayerName\n" +
                "§acreateLobby - creates a lobby //command Name MinPlayers MaxPlayers\n" +
                "§blistLobby - shows active lobbies \n" +
                "§3again - starts the game again\n" +
                "§1deletePlayer - clears the lobby of normal players\n" +
                "§9Example - /roul createLobby ol 1 2\n" +
                "§4always at the beginning of the command should be /roul");
        get().options().copyDefaults(true);
        save();
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
