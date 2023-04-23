package me.lampy8065.russianroulette.commands;

import me.lampy8065.russianroulette.models.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Completer implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1){
            List<String> cmds = new ArrayList<String>();
            cmds.add("join");
            cmds.add("leave");
            cmds.add("remove");
            cmds.add("delete");
            cmds.add("kick");
            cmds.add("create");
            cmds.add("again");
            return cmds;
                    }
        else if(args.length == 2 && args[0].equals("join")){
            List<String> lobbys = new ArrayList<>();
            for (Map.Entry entry: Lobby.getLobbyHashMap().entrySet()){
                lobbys.add((String) entry.getKey());
            }
            return lobbys;
        }
        return null;
    }
}
