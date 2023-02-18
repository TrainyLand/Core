package fr.shall0wer.trainycore.commands;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        if(LevelMod.getModLevel(player) >= 4){
            if(args.length == 2){
                ItemStack item = new ItemStack(Material.getMaterial(args[1]));
                if(args[0].equalsIgnoreCase("all")){
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.getInventory().setHelmet(item);
                    }
                    player.sendMessage("§6§lFUN §8§l❙ §fVous avez mis §e" + args[1].toUpperCase() + "§f a §etout le monde §f!");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    player.sendMessage("§cErreur: Ce joueur n'est pas connecté !");
                    return false;
                }
                target.getInventory().setHelmet(item);
                player.sendMessage("§6§lFUN §8§l❙ §fVous avez mis §e" + args[1].toUpperCase() + "§f a §e" + target.getName() + "§f !");
                return true;
            }
            player.sendMessage("§cUtilisation: /tete <Joueur / ALL> <Item>");
            return false;
        } else {
            player.sendMessage("§fCommande inconnue.");
        }
        return false;
    }
}
