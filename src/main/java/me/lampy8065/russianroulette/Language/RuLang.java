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
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("RussianRouletteGame").getDataFolder(), "RuLang");

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
        get().set("Messages-report.permission","§4Недостаточно прав!");
        get().set("Messages-report.NotLobby","§4Лобби [%s] не существует");
        get().set("Messages-report.NotPlayer","§4Игрока [%s] не существует");
        get().set("Messages-report.LobbyHAs","§4Лобби [%s] уже существует!");
        get().set("Messages-report.AlwaysHaveLobby","§4Вы уже в лобби");
        get().set("Messages-report.PlayerNotLobby","§4Игрок [%s] не состоит лобби");
        get().set("Messages-report.NotArg","§4Недостаточно аргументов");
        get().set("Messages-report.YouNotLobby","§4Вы не в лобби!");
        get().set("Messages-report.MaxMinPlayers","§4Максимальное количество игроков меньше минимального");
        get().set("Messages-report.FullLobby","§4Лобби [%s] заполнено");
        get().set("Messages-report.AlwaysHaveArena","§4Aрена [%s] уже существует!");
        get().set("Messages-report.PlayerHaveArena","§4Вы уже на арене!");
        //Stun messages
        get().set("StunMessage","");
        get().set("StunMessage.CreateLobby","§3Вы создали лобби с именем - %s");
        get().set("StunMessage.ExitLobby","§3Вы вышли из лобби");
        get().set("StunMessage.deleteLobby","§3Лобби удалено");
        get().set("StunMessage.LobbyRemove","§3Лобби [%s] Удалено");
        get().set("StunMessage.RemovePlayer","§3Игрок [%s] удален из лобби");
        get().set("StunMessage.KickPlayer","§3Игрок [%s] вышел из лобби");
        get().set("StunMessage.ActiveLobbys","§3Активные лобби: ");
        get().set("StunMessage.GameAgain","§3Вы начали игру заново!");
        get().set("StunMessage.OwnerClear","§3Вы удалили всех обычных игроков!");
        get().set("StunMessage.OwnerClersForPlayer","§3Админ очищает лобби!");
        get().set("StunMessage.joinLobby","§3Игрок [%s] присоеденился к лобби");
        get().set("StunMessage.GameStart","§3Игра начинается");
        get().set("StunMessage.GameStart1","§3осталось до игры ");
        get().set("StunMessage.GameStart2","§3Игра началась");
        get().set("StunMessage.GameOver","§3Игра закончена, по прошествию игры было убито [%s], Выжито [%s]");
        get().set("StunMessage.DeathGame","§3Игроку [%s] - выпала пуля из револьера");
        get().set("StunMessage.GameSave","§3Игроку [%s] - очень сильно повезло и он остался жив");
        get().set("StunMessage.MessageAgain","Чтобы начать игру заново просто нажмите");
        get().set("StunMessage.MessageAgain1","Начните игру заново");
        get().set("StunMessage.ActiveLobbys1","§3Активных лобби нету");
        get().set("StunMessage.RestartMessage","Если вы хотите сбросить лобби, просто нажмите");
        get().set("StunMessage.RestartMessage1","§3Удаление всех игроков");
        get().set("StunMessage.GameSign","§3Игра идет");
        // Help message
        get().set("HelpMessage","" +
                "§4joinLobby - присоединяет к лобби //joinLobby ИмяЛобби \n" +
                "§cleaveLobby - команда для того чтобы выйти из лобби \n" +
                "§6removeLobby - удаляет Лобби //removeLobby ИмяЛобби \n" +
                "§edeleteLobby - удаляет твое лобби \n" +
                "§2kickPlayerLobby - кикает игрока с лобби //команда Имя Имяигрока \n" +
                "§acreateLobby - создает лобби //команда Имя МинИгроков МаксИгроков \n" +
                "§blistLobby - показывает активные лобби \n" +
                "§3again - начинает игру заново \n" +
                "§1deletePlayer - очищает лобби от обычных игроков \n" +
                "§9Пример /roul createLobby test 1 2\n" +
                "§4Важно - чтобы перед каждой командой стоял /roul");
        get().options().copyDefaults(true);
        save();
    }


    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}
