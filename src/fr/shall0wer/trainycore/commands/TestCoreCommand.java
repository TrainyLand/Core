package fr.shall0wer.trainycore.commands;

import fr.shall0wer.trainycore.Main;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class TestCoreCommand implements CommandExecutor {

    public void sendBungeeCommand(Player player, String command){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Message");
            out.writeUTF("ALL");
            out.writeUTF("/alert Testing command distance");
        } catch(Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
        player.sendMessage("Envoyé !");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!((sender) instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        World world = player.getWorld();
        player.sendMessage("§bTemps: §f" + world.getTime());

        return false;
    }
}
