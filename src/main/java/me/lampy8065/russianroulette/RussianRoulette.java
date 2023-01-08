package me.lampy8065.russianroulette;

import me.lampy8065.russianroulette.Language.EngLang;
import me.lampy8065.russianroulette.Language.RuLang;
import me.lampy8065.russianroulette.commands.Completer;
import me.lampy8065.russianroulette.commands.JoinLobby;
import me.lampy8065.russianroulette.events.LeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class RussianRoulette extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("RussianRoulette plugin Its WORK");
        getCommand("roul").setExecutor(new JoinLobby(this));
        getCommand("roul").setTabCompleter(new Completer());
        getServer().getPluginManager().registerEvents(new LeaveEvent(),this);



        //Installing the language file on your server
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        EngLang.setupMessages();
        RuLang.setupMessages();
    }
}
