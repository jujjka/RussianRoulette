package me.lampy8065.russianroulette.models;

import lombok.Data;
import me.lampy8065.russianroulette.Language.LanguagemMgr;
import me.lampy8065.russianroulette.RussianRoulette;
import me.lampy8065.russianroulette.utils.UT;
import me.lampy8065.russianroulette.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

@Data
public class Game implements StartGame{

    private final RussianRoulette plugin;
    private final Lobby lobby;

    @Override
    public void startGame() {
        List<Player> players = lobby.getPlayersLobby();
        Iterator<Player> it = players.iterator();

        new BukkitRunnable(){
            @Override
            public void run() {
                while (it.hasNext())
                {
                    if(it.next() != null) {
                        Player this_player = it.next();
                        Location location = this_player.getEyeLocation();
                        int chance = (int) (Math.random() * plugin.getConfig().getInt("Chance"));
                        if(chance == 1){
                            this_player.spigot().sendMessage(
                                    ChatMessageType.ACTION_BAR,
                                    TextComponent.fromLegacyText(UT.getString("actionMessage_death")));
                            new Utils().kill(this_player,lobby);
                            new Utils().particle(
                                    this_player.getLocation(),
                                    Particle.REDSTONE);
                            lobby.setDeath(lobby.getDeath() + 1);
                        } else {
                            this_player.spigot().sendMessage(
                                    ChatMessageType.ACTION_BAR,
                                    TextComponent.fromLegacyText(UT.getString("actionMessage_save")));
                            lobby.setSave(lobby.getSave() + 1);
                            lobby.sendBarLobbyMessage(String.format(UT.getString("StunMessage.GameSave"), this_player));
                            this_player.spawnParticle(Particle.DAMAGE_INDICATOR, location,1);
                        }
                    }
                }
                lobby.stopGame();
                this.cancel();
            }
        }.runTaskTimer(plugin,0,100);
    }

}