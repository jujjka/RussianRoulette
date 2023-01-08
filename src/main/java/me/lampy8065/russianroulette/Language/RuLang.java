package me.lampy8065.russianroulette.Language;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class RuLang {
    private static File file;
    private static FileConfiguration customFile;

    //Finds or generates the custom config file
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("RussianRoulette").getDataFolder(), "RuLang");

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
            System.out.println("Couldn't save file RuLang");
        }
    }

    public static void setupMessages(){
        setup();

        //Reports messages
        get().set("Messages-report","");
        get().set("Messages-report.permission","Недостаточно прав!");
        get().set("Messages-report.NotLobby","Лобби не существует");
        get().set("Messages-report.NotPlayer","Этот игрок не существует");
        get().set("Messages-report.LobbyHAs","Такое лобби уже существует!");
        get().set("Messages-report.AlwaysHaveLobby","Вы уже в лобби");
        get().set("Messages-report.PlayerNotLobby","Игрок не состоит лобби");
        get().set("Messages-report.NotArg","Недостаточно аргументов");
        get().set("Messages-report.YouNotLobby","Вы не в лобби!");
        get().set("Messages-report.MaxMinPlayers","Максимальное количество игроков меньше минимального");
        get().set("Messages-report.FullLobby","Лобби заполнено");

        //Stun messages
        get().set("StunMessage","");
        get().set("StunMessage.CreateLobby","Вы создали лобби с именем - ");
        get().set("StunMessage.ExitLobby","Вы вышли из лобби");
        get().set("StunMessage.deleteLobb1","Лобби удалено");
        get().set("StunMessage.Lobby","Лобби - ");
        get().set("StunMessage.Remove","удалено");
        get().set("StunMessage.Player","Игрок");
        get().set("StunMessage.RemoveForLobby"," - удален из лобби");
        get().set("StunMessage.ActiveLobbys","Активные лобби: ");
        get().set("StunMessage.GameAgain","Вы начали игру заново!");
        get().set("StunMessage.OwnerClear","Вы удалили всех обычных игроков!");
        get().set("StunMessage.OwnerClersForPlayer","Админ очищает лобби!");
        get().set("StunMessage.joinLobby"," Присоеденился к лобби");
        get().set("StunMessage.GameStart","Смертельная игра начинается");
        get().set("StunMessage.GameStart1","осталось до игры ");
        get().set("StunMessage.GameStart2","Смертельная игра началась");
        get().set("StunMessage.GameOver","Игра закончена, по прошествию игры было убито - ");
        get().set("StunMessage.PlayerSave",", Выжито -");
        get().set("StunMessage.KickPlayer"," Вышел из лобби");
        get().set("StunMessage.Players1","Игроку -");
        get().set("StunMessage.DeathGame"," - выпала пуля из револьера");
        get().set("StunMessage.GameSave"," - очень сильно повезло и он остался жив");
        get().set("StunMessage.MessageAgain","Чтобы начать игру заново просто нажмите");
        get().set("StunMessage.MessageAgain1","Начните игру заново");
        get().set("StunMessage.ActiveLobbys1","Активных лобби нету");
        get().set("StunMessage.RestartMessage","Если вы хотите сбросить лобби, просто нажмите");
        get().set("StunMessage.RestartMessage1","Удаление всех игроков");
        // Help message
        get().set("HelpMessage","" +
                "joinLobby - присоединяет к лобби //joinLobby ИмяЛобби \n" +
                "leaveLobby - команда для того чтобы выйти из лобби \n" +
                "removeLobby - удаляет Лобби //removeLobby ИмяЛобби \n" +
                "deleteLobby - удаляет твое лобби \n" +
                "kickPlayerLobby - кикает игрока с лобби //команда Имя Имяигрока \n" +
                "createLobby - создает лобби //команда Имя МинИгроков МаксИгроков \n" +
                "listLobby - показывает активные лобби \n" +
                "again - начинает игру заново \n" +
                "deletePlayer - очищает лобби от обычных игроков \n");
        get().options().copyDefaults(true);
        save();
    }


    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}
