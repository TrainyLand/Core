package fr.shall0wer.trainycore.commands;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(LevelMod.getModLevel(player) < 4){
            player.sendMessage("§fCommande inconnue.");
            return false;
        }

        if(args.length == 0 || args.length > 2){
            player.sendMessage("§cUtilisation: /" + s + " <Gamemode> [Joueur]");
            return false;
        }

        Player target = null;
        if(args.length == 2){
            target = Bukkit.getPlayer(args[1]);
            if(target == null || !target.isOnline()){
                player.sendMessage("§cErreur: Ce joueur n'est pas en ligne.");
                return false;
            }
        } else {
            target = player;
        }
        if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survie")){
            target.setGameMode(GameMode.SURVIVAL);
            if(target != player){
                target.sendMessage("§e§lGAMEMODE §8§l❙ §fVotre mode de jeu a été modifié ! (§b" + target.getGameMode().toString() + "§f)");
                player.sendMessage("§e§lGAMEMODE §8§l❙ §fVous avez modifié le mode de jeu de §b" + target.getName() + "§f ! (§b" + target.getGameMode().toString() + "§f)");
                return true;
            }
            player.sendMessage("§e§lGAMEMODE §8§l❙ §fVotre mode de jeu a été modifié ! (§b" + target.getGameMode().toString() + "§f)");
            return false;
        }
        if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("créatif")){
            target.setGameMode(GameMode.CREATIVE);
            if(target != player){
                target.sendMessage("§e§lGAMEMODE §8§l❙ §fVotre mode de jeu a été modifié ! (§b" + target.getGameMode().toString() + "§f)");
                player.sendMessage("§e§lGAMEMODE §8§l❙ §fVous avez modifié le mode de jeu de §b" + target.getName() + "§f ! (§b" + target.getGameMode().toString() + "§f)");
                return true;
            }
            player.sendMessage("§e§lGAMEMODE §8§l❙ §fVotre mode de jeu a été modifié ! (§b" + target.getGameMode().toString() + "§f)");
            return false;
        }
        if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("aventure")){
            target.setGameMode(GameMode.ADVENTURE);
            if(target != player){
                target.sendMessage("§e§lGAMEMODE §8§l❙ §fVotre mode de jeu a été modifié ! (§b" + target.getGameMode().toString() + "§f)");
                player.sendMessage("§e§lGAMEMODE §8§l❙ §fVous avez modifié le mode de jeu de §b" + target.getName() + "§f ! (§b" + target.getGameMode().toString() + "§f)");
                return true;
            }
            player.sendMessage("§e§lGAMEMODE §8§l❙ §fVotre mode de jeu a été modifié ! (§b" + target.getGameMode().toString() + "§f)");
            return false;
        }
        if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectateur")){
            target.setGameMode(GameMode.SPECTATOR);
            if(target != player){
                target.sendMessage("§e§lGAMEMODE §8§l❙ §fVotre mode de jeu a été modifié ! (§b" + target.getGameMode().toString() + "§f)");
                player.sendMessage("§e§lGAMEMODE §8§l❙ §fVous avez modifié le mode de jeu de §b" + target.getName() + "§f ! (§b" + target.getGameMode().toString() + "§f)");
                return true;
            }
            player.sendMessage("§e§lGAMEMODE §8§l❙ §fVotre mode de jeu a été modifié ! (§b" + target.getGameMode().toString() + "§f)");
            return false;
        }
        return false;
    }
}
