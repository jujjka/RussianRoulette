package me.lampy8065.russianroulette.commands;

import me.lampy8065.russianroulette.Language.LanguagemMgr;
import me.lampy8065.russianroulette.RussianRoulette;
import me.lampy8065.russianroulette.models.Game;
import me.lampy8065.russianroulette.models.Lobby;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.UUID;

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
            if (sender instanceof Player p) {
                if(args.length == 0){
                    p.sendMessage(ChatColor.AQUA + LanguagemMgr.getLang().get("HelpMessage").toString());
                }
                 else if (args[0].equalsIgnoreCase("joinLobby")) {
                    if (args.length < 2) {
                        sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotArg").toString());
                        return true;
                    }
                    String lobb = args[1];
                    if (Lobby.getLobby(lobb) != null) {
                        if(Lobby.getLobby(lobb).playersLobby.contains(sender) || Lobby.lobbyList.contains(sender)){
                            sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.AlwaysHaveLobby").toString());
                            return false;
                        }
                        Lobby.getLobby(lobb).JoinLobby((Player) sender);
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.RED +LanguagemMgr.getLang().get("Messages-report.NotLobby").toString());
                        return true;
                    }

                } else if (args[0].equalsIgnoreCase("leaveLobby")) {
                    if (Lobby.getLobbyByPlayer(p) == null) {
                            sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.YouNotLobby").toString());
                            return false;
                    } else {
                        Lobby.getLobbyByPlayer(p).playersLobby.remove(p);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + LanguagemMgr.getLang().get("StunMessage.ExitLobby").toString());
                        return true;
                    }

                }
                else if (args[0].equalsIgnoreCase("removeLobby")) {
                    if(args.length < 2){
                        sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotArg").toString());
                        return true;
                    } else {
                    if(sender.isOp()) {
                        String name = args[1];
                        if(Lobby.getLobby(name) != null) {
                            Lobby.DeleteLobby(Lobby.getLobby(name));
                            sender.sendMessage(ChatColor.LIGHT_PURPLE + LanguagemMgr.getLang().get("StunMessage.Lobby").toString() + name + LanguagemMgr.getLang().get("StunMessage.Remove"));
                            return true;
                        }
                        sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotLobby").toString());
                        return false;
                    }
                    p.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.permission").toString());
                    }
                }

                else if (args[0].equalsIgnoreCase("deleteLobby")) {
                        if(sender.hasPermission("russianroulette.owner")) {
                            if(Lobby.getLobbyByPlayer((Player) sender) != null) {
                                Lobby.DeleteLobby(Lobby.getLobbyByPlayer((Player) sender));
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + LanguagemMgr.getLang().get("StunMessage.deleteLobb1").toString());
                                return true;
                            }
                            sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.YouNotLobby").toString());
                            return false;
                        }
                        p.sendMessage(ChatColor.RED + "Недостаточно прав");
                }
                else if (args[0].equalsIgnoreCase("kickPlayerLobby")) {
                    if(args.length < 3){
                        sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotArg").toString());
                        return true;
                    } else {
                        if(sender.isOp()) {
                            String Lobbyname = args[1];
                            String Playername = args[2];
                            Player TARGET_PLAYER = Bukkit.getPlayer(Playername);
                            if (Lobby.getLobby(Lobbyname) != null) {
                                if (TARGET_PLAYER != null) {
                                    if (Lobby.getLobbyByPlayer(TARGET_PLAYER) != null) {
                                        Lobby.getLobbyByPlayer(TARGET_PLAYER).kickLobby(TARGET_PLAYER);
                                        sender.sendMessage(ChatColor.LIGHT_PURPLE + LanguagemMgr.getLang().get("StunMessage.Player").toString() + ChatColor.LIGHT_PURPLE + " - " + Playername + LanguagemMgr.getLang().get("StunMessage.RemoveForLobby") + Lobbyname);
                                        return true;
                                    }
                                    sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.PlayerNotLobby").toString());
                                    return false;
                                }
                                sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotPlayer").toString());
                                return false;
                            }
                            sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotLobby").toString());
                            return false;
                        }
                        p.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.permission").toString());
                    }
                }


                else if (args[0].equalsIgnoreCase("createLobby")) {
                    if (args.length < 4) {
                        sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotArg").toString());
                        return true;
                    }
                    String name = args[1];
                    int min = Integer.parseInt(args[2]);
                    int max = Integer.parseInt(args[3]);
                    if(min <= max) {
                        if (Lobby.lobbyList.contains(Lobby.getLobby(name))) {
                            sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.LobbyHAs").toString());
                            return false;
                        } else {
                            if (Lobby.getLobbyByPlayer((Player) sender) == null) {
                                Lobby lobby = new Lobby(name, min, max);
                                Lobby.lobbyList.add(lobby);
                                lobby.Owner = p;
                                lobby.JoinLobby(p);
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + LanguagemMgr.getLang().get("StunMessage.CreateLobby").toString() + name);
                                PermissionAttachment attachment = p.addAttachment(pl);
                                Permission permission = new Permission("russianroulette.owner");
                                attachment.setPermission(permission, true);
                                return true;
                            }
                            sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.AlwaysHaveLobby").toString());
                            return false;
                        }
                    }
                        sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.MaxMinPlayers").toString());
                }
                else if(args[0].equalsIgnoreCase("listLobby")){
                    lobbys = LanguagemMgr.getLang().get("StunMessage.ActiveLobbys").toString();
                    if(Lobby.lobbyList.size() == 0){
                        sender.sendMessage(ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.ActiveLobbys1").toString());
                    }
                    else {
                    for(Lobby onlineLob: Lobby.lobbyList) {
                        lobbys = lobbys + onlineLob.getNameLobby() + ",";
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + lobbys);
                    }
                    }
                }
                else if(args[0].equalsIgnoreCase("again")){
                    Lobby lobby = Lobby.getLobbyByPlayer(p);
                    if (lobby!= null) {
                        if (lobby.Owner == sender) {
                            sender.sendMessage(ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.GameAgain").toString() + ChatColor.ITALIC);
                            lobby.startGame();
                        } else {
                            sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.permission").toString() + ChatColor.ITALIC);
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotLobby").toString() + ChatColor.ITALIC);
                    }
                }
                else if (args[0].equalsIgnoreCase("deletePlayers")) {
                    Lobby lobby = Lobby.getLobbyByPlayer(p);
                    if (lobby != null) {
                        if (lobby.Owner == sender) {
                            sender.sendMessage(ChatColor.GOLD + LanguagemMgr.getLang().get("StunMessage.OwnerClear").toString());
                            for (Player player : lobby.playersLobby) {
                                if (player != lobby.Owner) {
                                    player.sendMessage(ChatColor.AQUA + LanguagemMgr.getLang().get("StunMessage.OwnerClersForPlayer").toString() + ChatColor.ITALIC);
                                    lobby.kickLobby(player);
                                }
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.permission").toString() + ChatColor.ITALIC);
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + LanguagemMgr.getLang().get("Messages-report.NotLobby").toString() + ChatColor.ITALIC);
                    }
                }
                else if(args[0].equalsIgnoreCase("help")){
                    sender.sendMessage(ChatColor.AQUA + LanguagemMgr.getLang().get("HelpMessage").toString());
                }
                }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
