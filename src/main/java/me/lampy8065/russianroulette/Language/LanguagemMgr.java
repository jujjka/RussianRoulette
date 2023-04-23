package me.lampy8065.russianroulette.Language;

import me.lampy8065.russianroulette.RussianRoulette;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class LanguagemMgr {

    private static RussianRoulette plugin = RussianRoulette.getPlugin();

    public static void setupFiles(){
        File lang = new File(plugin.getDataFolder(), "lang");
        plugin.saveResource("lang/ru.yml",true);
        plugin.saveResource("lang/en.yml",true);
        if (!lang.exists()){
            lang.mkdirs();
        }
    }
    private File getLang() {
        File directory = getLangDirectory();
        for (File file_lang: directory.listFiles()){
            if(file_lang.getName().contains(plugin.getConfig().getString("lang"))){
                return file_lang;
            }
        }
        return null;
    }
    public String getString(String string) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(getLang());
        return configuration.getString(string);
    }
    public File getLangDirectory(){
        List<File> files = Arrays.asList(plugin.getDataFolder().listFiles());
        for (File file : files)
        {
            if(file.getName().equals("lang")){
                return file;
            }
        }
        return null;
    }
}


