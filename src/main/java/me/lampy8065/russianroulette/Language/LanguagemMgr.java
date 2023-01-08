package me.lampy8065.russianroulette.Language;

import me.lampy8065.russianroulette.RussianRoulette;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguagemMgr {

    private final RussianRoulette plugin;

    public LanguagemMgr(RussianRoulette plugin) {
        this.plugin = plugin;
    }

    public static FileConfiguration getLang(){
        String lang = Bukkit.getPluginManager().getPlugin("RussianRoulette").getConfig().getString("lang");
        File file = new File(Bukkit.getPluginManager().getPlugin("RussianRoulette").getDataFolder().getPath(),lang);
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }
}
