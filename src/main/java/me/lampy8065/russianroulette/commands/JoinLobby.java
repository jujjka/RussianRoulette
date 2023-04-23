package me.lampy8065.russianroulette.commands;

import me.lampy8065.russianroulette.RussianRoulette;
import me.lampy8065.russianroulette.models.Game;
import me.lampy8065.russianroulette.models.Lobby;
import me.lampy8065.russianroulette.utils.UT;
import me.lampy8065.russianroulette.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinLobby implements CommandExecutor  {
    private final RussianRoulette pl;

    public JoinLobby(RussianRoulette plugin){
        plugin.getCommand("roul").setExecutor(this::onCommand);
        this.pl = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)  {
        if(!(sender instanceof Player)){sender.sendMessage("Only players");return false;}
        if(args.length == 0){sender.sendMessage(UT.getString("helpMessage"));return false;}

        Player p = (Player) sender;
        /* use switch instead of else-if
        * 9 commands
        * */
        switch (args[0]) {
            /* command for create Lobby */
            case "create":
                if(args.length < 4){
                    p.sendMessage(UT.getString("Messages-report.NotArg"));
                    return false;
                }
                String name;
                int min;
                int max;
                try {
                    name = args[1];
                    min = Integer.parseInt(args[2]);
                    max = Integer.parseInt(args[3]);
                } catch (Exception ignored){
                    p.sendMessage(UT.getString("Messages-report.error_input"));
                    return false;
                }
                if(Utils.has(args[1])){
                    p.sendMessage(String.format(UT.getString("Messages-report.LobbyHAs"), name));
                    return false;
                }
                if(Utils.getLobby(p) != null){
                    p.sendMessage(String.format(UT.getString("Messages-report.AlwaysHaveLobby")));
                    return false;
                }

                /* CREATE LOBBY */
                Lobby l = new Lobby(p,name,min,max);
                l.setIsGame(false);
                l.JoinLobby(p);
                Lobby.getLobbyHashMap().put(name,l);
                p.sendMessage(String.format(UT.getString("StunMessage.CreateLobby"), name));
                break;

                //command for delete your lobby, YOUR!
                case "delete":
                    if(!Lobby.getOwnersHashMap().containsKey(p)){
                        p.sendMessage(UT.getString("Messages-report.YouNotLobby"));
                        return false;
                    }
                    Lobby lobby = Lobby.getOwnersHashMap().get(p);
                    if(!lobby.getOwner().equals(p)){
                        p.sendMessage(UT.getString("Messages-report.permission"));
                        return false;
                    }
                    lobby.delete();
                    sender.sendMessage(UT.getString("deleteLobby"));
                    break;

                    //cmd for remove lobby
            case "remove":
                if(args.length < 2){
                    p.sendMessage(UT.getString("Messages-report.NotArg"));
                    return false;
                }
                String lobbyName = args[1];
                if(!Utils.has(lobbyName)){
                    sender.sendMessage(String.format(UT.getString("Messages-report.NotLobby"), lobbyName));
                    return false;
                }
                if(!p.isOp()){
                    p.sendMessage(UT.getString("Messages-report.permission"));
                    return false;
                }
                Lobby lb = Lobby.getLobbyHashMap().get(lobbyName);
                lb.delete();
                p.sendMessage(String.format(UT.getString("LobbyRemove"), lobbyName));
                break;
                case "join":
                    if(args.length < 2){
                        p.sendMessage(UT.getString("Messages-report.NotArg"));
                        return false;
                    }
                    String Name = args[1];
                    if(!Utils.has(Name)){
                        sender.sendMessage(String.format(UT.getString("Messages-report.NotLobby"), Name));
                        return false;
                    }
                    Lobby join_lobby = Lobby.getLobbyHashMap().get(Name);
                    if(join_lobby.getIsGame()){
                        p.sendMessage(String.format(UT.getString("Messages-report.FullLobby"), Name));
                        return false;
                    }
                    if(Utils.getLobby(p) != null) {
                        p.sendMessage(String.format(UT.getString("Messages-report.AlwaysHaveLobby")));
                        return false;
                    }
                    join_lobby.JoinLobby(p);
                    break;
            /* command for leave for lobby */
            case "leave":
                if(Utils.getLobby(p) == null) {
                    p.sendMessage(String.format(UT.getString("Messages-report.YouNotLobby")));
                    return false;
                }
                Lobby l1 = Utils.getLobby(p);
                l1.kickLobby(p);
                break;

            /* kick player form lobby */
            case "kick":
                if(!p.isOp()){
                    p.sendMessage(UT.getString("Messages-report.permission"));
                    return false;
                }
                if(args.length < 2){
                    p.sendMessage(UT.getString("Messages-report.NotArg"));
                    return false;
                }
                if(Bukkit.getPlayer(args[1]) == null){
                    p.sendMessage(String.format(UT.getString("Messages-report.NotPlayer"), args[1]));
                    return false;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if(Utils.getLobby(target) == null) {
                    p.sendMessage(String.format(UT.getString("Messages-report.PlayerNotLobby"),args[1]));
                    return false;
                }
                if(Utils.getLobby(p) == null) {
                    p.sendMessage((UT.getString("Messages-report.YouNotLobby")));
                    return false;
                }
                if(Utils.getLobby(target) != Utils.getLobby(p)) {
                    p.sendMessage(String.format(UT.getString("Messages-report.PlayerNotLobby"),args[1]));
                    return false;
                }
                Lobby l2 = Utils.getLobby(p);
                l2.kickLobby(p);
                break;
            /* cmd for again game in your lobby */
            case "again":
                if(Utils.getLobby(p) == null) {
                    p.sendMessage((UT.getString("Messages-report.YouNotLobby")));
                    return false;
                }
                Lobby l3 = Utils.getLobby(p);
                if(!l3.getOwner().equals(p)) {
                    p.sendMessage((UT.getString("Messages-report.permission")));
                    return false;
                }
                if(l3.getIsGame()){
                    p.sendMessage(UT.getString("Messages-report.notAgainGame"));
                    return false;
                }
                l3.stopGame();
                l3.setGame(new Game(pl,l3));
                break;
            /* cmd for stop game in your lobby */
            case "stop":
                if(!Lobby.getOwnersHashMap().containsKey(p)){
                    p.sendMessage(UT.getString("Messages-report.YouNotLobby"));
                    return false;
                }
                Lobby l4 = Lobby.getOwnersHashMap().get(p);
                if(!l4.getOwner().equals(p)){
                    p.sendMessage(UT.getString("Messages-report.permission"));
                    return false;
                }
                if(!l4.getIsGame()){
                    p.sendMessage(UT.getString("StunMessage.GameSign"));
                    return false;
                }
                l4.stopGame();
                break;
        }
        return false;
    }
}
