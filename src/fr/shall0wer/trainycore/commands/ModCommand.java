package fr.shall0wer.trainycore.commands;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import fr.shall0wer.trainyapi.playerdata.Moderation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ModCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(LevelMod.getModLevel(player) < 2){
            player.sendMessage("§cErreur: Vous n'avez la permission d'éxécuter cette commande.");
            return false;
        }

        if(Moderation.isInModModeration(player)){
            try {
                Moderation.setModeration(player, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage("§r\n§c§lMOD §8§l❙ §fVous venez de §cdésactiver §fvotre §bmode modération§f. Ce dernier restera §eactif §fjusqu'à votre prochain §echangement de serveur §fou §cdéconnexion§f. Si vous souhaitez le §aréactiver§f, refaites §b/mod§f.\n§f ");
            return true;
        }
        try {
            Moderation.setModeration(player, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendMessage("§r\n§c§lMOD §8§l❙ §fVous venez d'§aactiver §fvotre §bmode modération§f, il sera §eactif§f dès votre prochain §echangemement de serveur§f.\n§f ");
        return false;
    }
}
