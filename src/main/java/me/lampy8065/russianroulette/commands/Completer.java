package me.lampy8065.russianroulette.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class Completer implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("roul") && args.length == 1){
            return List.of(
                    "help",
                    "joinLobby",
                    "leaveLobby",
                    "removeLobby",
                    "deleteLobby",
                    "kickPlayerLobby",
                    "createLobby",
                    "listLobby",
                    "again",
                    "deletePlayers"
            );
        }
        return null;
    }
}
