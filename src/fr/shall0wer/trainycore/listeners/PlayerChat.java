package fr.shall0wer.trainycore.listeners;

import fr.shall0wer.trainyapi.playerdata.Moderation;
import fr.shall0wer.trainyapi.playerdata.Rank;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.commands.misc.ChatCommand;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.Locale;


public class PlayerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (Moderation.isModerationActive(player)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(new TextComponent(Rank.getRank(player).replace("&", "§") + " " + player.getName() + " §8» " + ChatColor.AQUA + e.getMessage()).toLegacyText());
            }
            return;
        }
    }
}

