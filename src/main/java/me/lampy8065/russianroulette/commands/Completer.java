package me.lampy8065.russianroulette.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Completer implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("roul") && args.length == 1){
            List<String> cmds = new ArrayList<String>();
            cmds.add("help");
            cmds.add("joinLobby");
            cmds.add("leaveLobby");
            cmds.add("removeLobby");
            cmds.add("deleteLobby");
            cmds.add("kickPlayerLobby");
            cmds.add("createLobby");
            cmds.add("listLobby");
            cmds.add("again");
            cmds.add("deletePlayers");
            return cmds;

        }
        return null;
    }
}
