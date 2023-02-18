package fr.shall0wer.trainycore.commands.misc;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChatCommand implements CommandExecutor {

    public static List getBanWords(){
        FileConfiguration config = null;
        File file = new File("/home/DataBase/config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        return config.getStringList("BanWords");
    }

    private void addBanWord(String word) throws IOException {
        FileConfiguration config = null;
        File file = new File("/home/DataBase/config.yml");
        List list = getBanWords();
        list.add(word);
        config = YamlConfiguration.loadConfiguration(file);
        config.set("BanWords", list);
        config.save(file);
    }

    private void removeBanWord(String word) throws IOException {
        FileConfiguration config = null;
        File file = new File("/home/DataBase/config.yml");
        List list = getBanWords();
        list.remove(word);
        config = YamlConfiguration.loadConfiguration(file);
        config.set("BanWords", list);
        config.save(file);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player player = (Player) sender;
        if(LevelMod.getModLevel(player) < 4){
            player.sendMessage("§cErreur: Vous n'avez pas la permission d'utiliser cette commande.");
            return false;
        }

        if(args.length == 0){
            player.sendMessage("§cUtilisation: /chat <add / remove / list> <mot>");
            return false;
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("list")){

            } else {
                player.sendMessage("§cUtilisation: /chat <add - remove - list> <mot>");
            }
            return true;
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")){
            if(args[0].equalsIgnoreCase("add")){
                if(getBanWords().contains(args[1])){
                    player.sendMessage("§b§lCHAT §8§l❙ §cCe mot est déjà banni.");
                    return false;
                }
                try {
                    addBanWord(args[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage("§b§lCHAT §8§l❙ §fLe mot '§e" + args[1] + "§f' est désormais §cbanni §fdu chat.");
                return true;
            }
            if(args[0].equalsIgnoreCase("remove")){
                if(!getBanWords().contains(args[1])){
                    player.sendMessage("§b§lCHAT §8§l❙ §cCe mot n'est pas banni.");
                    return false;
                }
                try {
                    removeBanWord(args[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage("§b§lCHAT §8§l❙ §fLe mot '§e" + args[1] + "§f' §an'est plus §fbanni du chat.");
                return true;
            }
            player.sendMessage("§cUtilisation: /chat <add / remove / list> <mot>");
            return false;
        }
        player.sendMessage("§cUtilisation: /chat <add / remove / list> <mot>");
        return false;
    }
}
