package me.lampy8065.russianroulette.utils;

import lombok.Getter;
import me.lampy8065.russianroulette.Language.LanguagemMgr;
import org.bukkit.configuration.file.FileConfiguration;

public class UT {
    @Getter
    private static LanguagemMgr languagemMgr = new LanguagemMgr();
    /**
     * class is meant to shorten code
     */
    public static String getString(String str) {
        /* get string message */
        return languagemMgr.getString(str);
    }
}
