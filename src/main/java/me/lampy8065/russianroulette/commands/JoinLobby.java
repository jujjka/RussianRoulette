package me.lampy8065.russianroulette.commands;

import me.lampy8065.russianroulette.Language.LanguagemMgr;
import me.lampy8065.russianroulette.RussianRoulette;
import me.lampy8065.russianroulette.models.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

public class JoinLobby implements CommandExecutor  {




    private final RussianRoulette pl;


    public JoinLobby(RussianRoulette plugin){
        plugin.getCommand("roul").setExecutor(this::onCommand);
        this.pl = plugin;
    }


    public String lobbys;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)  {
        try {
            if (sender instanceof Player ) {
                Player p = (Player) sender;
                if(args.length == 0){
                    p.sendMessage(LanguagemMgr.getLang().getString("HelpMessage"));
                }
                 else if (args[0].equalsIgnoreCase("joinLobby")) {
                    if (args.length < 2) {sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.NotArg"));return false;}
                    String lobb = args[1];
                    if (Lobby.getLobby(lobb) != null) {sender.sendMessage(String.format(LanguagemMgr.getLang().getString("Messages-report.NotLobby"),lobb));return false;}
                        if(Lobby.getLobby(lobb).playersLobby.contains(sender) || Lobby.lobbyList.contains(sender)){sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.AlwaysHaveLobby"));return false;}
                        Lobby.getLobby(lobb).JoinLobby((Player) sender);
                        return true;

                } else if (args[0].equalsIgnoreCase("leaveLobby")) {
                    if (Lobby.getLobbyByPlayer(p) == null) {sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.YouNotLobby"));return false;}
                        Lobby.getLobbyByPlayer(p).playersLobby.remove(p);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + LanguagemMgr.getLang().getString("StunMessage.ExitLobby"));
                        return true;
                }
                else if (args[0].equalsIgnoreCase("removeLobby")) {
                    if(args.length < 2){sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.NotArg"));return false;}
                    if(!sender.isOp()) { p.sendMessage(LanguagemMgr.getLang().getString("Messages-report.permission"));}
                        String name = args[1];
                        if(Lobby.getLobby(name) == null) { sender.sendMessage(String.format(LanguagemMgr.getLang().getString("Messages-report.NotLobby"), name));return false;}
                            Lobby.DeleteLobby(Lobby.getLobby(name));
                            sender.sendMessage(String.format(LanguagemMgr.getLang().getString("StunMessage.LobbyRemove"), name));
                            return true;
                }

                else if (args[0].equalsIgnoreCase("deleteLobby")) {
                        if(!sender.hasPermission("russianroulette.owner")) { p.sendMessage(LanguagemMgr.getLang().getString("Messages-report.permission"));}
                            if(Lobby.getLobbyByPlayer((Player) sender) == null) {  sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.YouNotLobby"));return false;}
                                Lobby.DeleteLobby(Lobby.getLobbyByPlayer((Player) sender));
                                sender.sendMessage(LanguagemMgr.getLang().getString("StunMessage.deleteLobby"));
                                return true;
                }
                else if (args[0].equalsIgnoreCase("kickPlayerLobby")) {
                    if(args.length < 3){sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.NotArg"));return false;}
                    if(!sender.isOp()) {p.sendMessage(LanguagemMgr.getLang().getString("Messages-report.permission"));return false;}
                            String Lobbyname = args[1];
                            String Playername = args[2];
                            Player TARGET_PLAYER = Bukkit.getPlayer(Playername);
                            if (Lobby.getLobby(Lobbyname) == null) { sender.sendMessage(String.format(LanguagemMgr.getLang().getString("Messages-report.NotLobby"), Lobbyname));return false;}
                                if (TARGET_PLAYER == null) {sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.NotPlayer"));return false;}
                                    if (Lobby.getLobbyByPlayer(TARGET_PLAYER) == null) {  sender.sendMessage(String.format(LanguagemMgr.getLang().getString("Messages-report.PlayerNotLobby"), TARGET_PLAYER.getName()));return false;}
                                        Lobby.getLobbyByPlayer(TARGET_PLAYER).kickLobby(TARGET_PLAYER);
                                        sender.sendMessage(String.format(LanguagemMgr.getLang().getString("StunMessage.RemovePlayer"), TARGET_PLAYER.getName()));
                                        return true;}

                else if (args[0].equalsIgnoreCase("createLobby")) {
                    if (args.length < 4) {sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.NotArg"));return false;}
                    String name = args[1];
                    int min = Integer.parseInt(args[2]);
                    int max = Integer.parseInt(args[3]);
                    if(min > max) { sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.MaxMinPlayers")); return false;}
                        if (Lobby.lobbyList.contains(Lobby.getLobby(name))) {sender.sendMessage(String.format(LanguagemMgr.getLang().getString("Messages-report.LobbyHAs"), name));return false;}
                            if (Lobby.getLobbyByPlayer((Player) sender) != null) { sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.AlwaysHaveLobby"));return false;}
                                Lobby lobby = new Lobby(name, min, max,p);
                                Lobby.lobbyList.add(lobby);
                                lobby.setOwner(p);
                                lobby.JoinLobby(p);
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + String.format(LanguagemMgr.getLang().getString("StunMessage.CreateLobby"), name));
                                PermissionAttachment attachment = p.addAttachment(pl);
                                Permission permission = new Permission("russianroulette.owner");
                                attachment.setPermission(permission, true);
                                return true;
                }
                else if(args[0].equalsIgnoreCase("listLobby")){
                    lobbys = LanguagemMgr.getLang().getString("StunMessage.ActiveLobbys");
                    if(Lobby.lobbyList.size() == 0){sender.sendMessage(LanguagemMgr.getLang().getString("StunMessage.ActiveLobbys1"));}
                    else {
                    for(Lobby onlineLob: Lobby.lobbyList) {
                        lobbys = lobbys + onlineLob.getNameLobby() + ",";
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + lobbys);
                    }
                    }
                }
                else if(args[0].equalsIgnoreCase("again")){
                    if (Lobby.getLobbyByPlayer(p) == null) { sender.sendMessage(String.format(LanguagemMgr.getLang().getString("Messages-report.NotLobby"), "") + ChatColor.ITALIC);return false;}
                    Lobby lobby = Lobby.getLobbyByPlayer(p);
                    if (lobby.getOwner() != sender) { sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.permission")+ ChatColor.ITALIC);return false;}
                            sender.sendMessage(LanguagemMgr.getLang().getString("StunMessage.GameAgain") + ChatColor.ITALIC);
                            lobby.startGame();
                }
                else if (args[0].equalsIgnoreCase("deletePlayers")) {
                    Lobby lobby = Lobby.getLobbyByPlayer(p);
                    if (lobby == null) { sender.sendMessage(String.format(LanguagemMgr.getLang().getString("Messages-report.NotLobby"), lobby.getNameLobby()) + ChatColor.ITALIC);return false;}
                        if (lobby.getOwner()!= sender) {sender.sendMessage(LanguagemMgr.getLang().getString("Messages-report.permission") + ChatColor.ITALIC);return false;}
                            sender.sendMessage(LanguagemMgr.getLang().getString("StunMessage.OwnerClear"));
                            for (Player player : lobby.playersLobby) {
                                if (player != lobby.getOwner()) {
                                    player.sendMessage(LanguagemMgr.getLang().getString("StunMessage.OwnerClersForPlayer")+ ChatColor.ITALIC);
                                    lobby.kickLobby(player);
                                }
                            }
                }
                else if(args[0].equalsIgnoreCase("help")){
                    sender.sendMessage(LanguagemMgr.getLang().getString("HelpMessage"));
                }
                }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
