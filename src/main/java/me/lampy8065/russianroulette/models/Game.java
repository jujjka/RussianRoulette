package me.lampy8065.russianroulette.models;

import me.lampy8065.russianroulette.Language.LanguagemMgr;
import me.lampy8065.russianroulette.Language.RuLang;
import me.lampy8065.russianroulette.RussianRoulette;
import me.lampy8065.russianroulette.messages.MessagerMg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.List;

public class Game {

    private final RussianRoulette plugin;


    public Game(RussianRoulette plugin) {
        this.plugin = plugin;
    }


    public static void startGame(Lobby lobby) {
        new BukkitRunnable() {
            int i = 0;
            int size = lobby.playersLobby.size();

            @Override
            public void run() {

                    if (i >= 0) {
                            if (i >= size) {
                                lobby.stopGame();
                                new BukkitRunnable(){

                                    @Override
                                    public void run() {
                                        MessagerMg.messageAgain(lobby.Owner);
                                        MessagerMg.messageDelete(lobby.Owner);
                                    }
                                }.runTaskLater(RussianRoulette.getPlugin(RussianRoulette.class),190);

                                cancel();
                            }
                            else {
                                Player player = lobby.playersLobby.get(i);
                                if (i < size) {
                                    i++;
                                    int rand = 1;
                                    rand = (int) (Math.random() * RussianRoulette.getPlugin(RussianRoulette.class).getConfig().getInt("Chance"));
                                    if (rand == 1) {
                                        lobby.sendBarLobbyMessage(ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.Players1").toString() + ChatColor.GOLD + player.getDisplayName() + ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.DeathGame"));
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                player.setHealth(0.0);
                                                lobby.death++;
                                            }
                                        }.runTaskLater(RussianRoulette.getPlugin(RussianRoulette.class), 100);
                                    } else {
                                        lobby.sendBarLobbyMessage(ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.Players1").toString() + ChatColor.GOLD + player.getDisplayName() + ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.GameSave"));
                                        lobby.save++;
                                    }
                                }
                            }
                    }
            }
        }.runTaskTimer(RussianRoulette.getPlugin(RussianRoulette.class), 0, 150);
    }
}