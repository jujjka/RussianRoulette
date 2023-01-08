package me.lampy8065.russianroulette.messages;

import me.lampy8065.russianroulette.Language.LanguagemMgr;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.TextComponentSerializer;
import org.bukkit.entity.Player;

public class MessagerMg extends TextComponentSerializer {



     public static void messageAgain(Player player){
          TextComponent component1 = new TextComponent(LanguagemMgr.getLang().getString("StunMessage.MessageAgain"));
         component1.setColor(ChatColor.GOLD);
         component1.setBold(true);
         component1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/roul again"));
         component1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                 new ComponentBuilder(LanguagemMgr.getLang().getString("StunMessage.MessageAgain1")).color(ChatColor.GOLD).italic(true).create()));
         player.spigot().sendMessage(component1);
     }
    public static void messageDelete(Player player){
        TextComponent component1 = new TextComponent(LanguagemMgr.getLang().getString("StunMessage.RestartMessage"));
        component1.setColor(ChatColor.GOLD);
        component1.setBold(true);
        component1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/roul deletePlayers"));
        component1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(LanguagemMgr.getLang().getString("StunMessage.RestartMessage1")).color(ChatColor.GOLD).italic(true).create()));
        player.spigot().sendMessage(component1);
    }


}
