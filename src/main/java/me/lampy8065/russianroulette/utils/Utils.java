package me.lampy8065.russianroulette.utils;

import me.lampy8065.russianroulette.RussianRoulette;
import me.lampy8065.russianroulette.models.Lobby;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    private final RussianRoulette plugin = RussianRoulette.getPlugin();

    public void kill(Player player,Lobby lobby){
        Location location = player.getEyeLocation();
        location.setX(location.getX() + 1);
        bow(location);

        new BukkitRunnable(){
            @Override
            public void run() {
                player.setHealth(0);
                lobby.sendBarLobbyMessage(String.format(UT.getString("StunMessage.DeathGame"), player.getDisplayName()));
            }
        }.runTaskLater(plugin,50);
    }
    private void bow(Location location){
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setArms(true);
        armorStand.setHealth(10000);
        armorStand.setHelmet(new ItemStack(Material.BOW));

        new BukkitRunnable(){
            @Override
            public void run() {
                armorStand.remove();
            }
        }.runTaskLater(plugin,60);
    }
    public void particle(Location location, Particle particle){
        location.getWorld().spawnParticle(particle, location,10);
    }

    //STUPID METHOD(
    public static boolean has(String name){
        for(Map.Entry entry: Lobby.getLobbyHashMap().entrySet()){
            if(entry.getKey().equals(name)){
                return true;
            }
        }
        return false;
    }
    public static Lobby getLobby(Player player){
        for(Map.Entry entry: Lobby.getLobbyHashMap().entrySet()){
           Lobby lobby = (Lobby) entry.getValue();
           if(lobby.getPlayersLobby().contains(player)){
               return lobby;
           }
        }
        return null;
    }
}
