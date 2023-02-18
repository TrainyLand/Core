package fr.shall0wer.trainycore.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.shall0wer.trainycore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PinfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                player.sendMessage("§cErreur: Ce joueur n'est pas connecté.");
                return false;
            }

            return false;
        }
        return false;
    }
}
