package me.lampy8065.russianroulette.permission;

import me.lampy8065.russianroulette.RussianRoulette;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class ManagerPerm {

    public static HashMap<UUID,PermissionAttachment> Pperms = new HashMap<>();
    private static RussianRoulette plugin;

    public ManagerPerm(RussianRoulette plugin) {
        this.plugin = plugin;
    }

}
