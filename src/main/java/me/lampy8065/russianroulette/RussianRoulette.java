package me.lampy8065.russianroulette;

import me.lampy8065.russianroulette.Language.EngLang;
import me.lampy8065.russianroulette.Language.RuLang;
import me.lampy8065.russianroulette.commands.Completer;
import me.lampy8065.russianroulette.commands.JoinLobby;
import me.lampy8065.russianroulette.events.LeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class RussianRoulette extends JavaPlugin {



    @Override
    public void onEnable() {
        /* Console */
        Bukkit.getLogger().info("=================== RussianRoulette ====================");
        Bukkit.getLogger().info("RussianRoulette: is now enabled!");
        Bukkit.getLogger().info("version: " + getDescription().getVersion());
        Bukkit.getLogger().info("author: " + getDescription().getAuthors());
        /* Commands */
        getCommand("roul").setExecutor(new JoinLobby(this));
        getCommand("roul").setTabCompleter(new Completer());
        /* Events */
        getServer().getPluginManager().registerEvents(new LeaveEvent(this),this);
        /* Metrics */
        int pluginId = 17443; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);
        /* Installing the language file on your server */
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        EngLang.setupMessages();
        RuLang.setupMessages();
    }
}
