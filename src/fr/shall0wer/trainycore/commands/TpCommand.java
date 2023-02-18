package fr.shall0wer.trainycore.commands;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(LevelMod.getModLevel(player) < 3){
            player.sendMessage("§cErreur: Vous n'avez pas la permission d'utiliser cette commande.");
            return false;
        }

        if(args.length != 1){
            player.sendMessage("§cUtilisation: /tp <Pseudo>");
            return false;
        }

        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                player.sendMessage("§6§lTP §8§l❙ §fCe joueur §cn'est pas §fconnecté.");
                return false;
            }
            player.teleport(target);
            player.sendMessage("§6§lTP §8§l❙ §fVous vous êtes téléporté à §b" + target.getName() + "§f.");
        }
        return true;
    }
}
