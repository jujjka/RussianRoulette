package me.lampy8065.russianroulette;

import lombok.Getter;
import me.lampy8065.russianroulette.Language.LanguagemMgr;
import me.lampy8065.russianroulette.commands.Completer;
import me.lampy8065.russianroulette.commands.JoinLobby;
import org.bukkit.plugin.java.JavaPlugin;


public class RussianRoulette extends JavaPlugin {
    @Getter
    private static RussianRoulette plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        LanguagemMgr.setupFiles();
        this.getLogger().info("All files setup");

        this.getLogger().info("=================== RussianRoulette ====================");
        this.getLogger().info("is now enabled!");
        this.getLogger().info("version: " + getDescription().getVersion());
        this.getLogger().info("author: " + getDescription().getAuthors());

        getCommand("roul").setExecutor(new JoinLobby(this));
        getCommand("roul").setTabCompleter(new Completer());


        int pluginId = 17443; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

    }
}
