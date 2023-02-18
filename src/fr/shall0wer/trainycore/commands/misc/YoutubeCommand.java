package fr.shall0wer.trainycore.commands.misc;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class YoutubeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        player.sendMessage(new TextComponent("§r \n§c§lYOUTUBE §8§l❙ §fVous êtes §cYou§fTubeur et souhaitez avoir le grade ?\n§f    Voici les conditions:\n§r \n    §c▪ §fAvoir au moins §e250 abonnés§f.\n    §c▪ §fAvoir au minimum §b1 000 vues §fpar mois.\n§r \n§eVous avez les conditions ? Rendez-vous sur: §bhttps://www.trainyland.fr/youtubeur §f!\n§r ").toLegacyText());
        return false;
    }
}
