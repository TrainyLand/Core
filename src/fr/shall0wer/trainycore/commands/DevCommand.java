package fr.shall0wer.trainycore.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceEvent;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStartEvent;
import de.dytanic.cloudnet.driver.provider.CloudMessenger;
import de.dytanic.cloudnet.ext.syncproxy.node.CloudNetSyncProxyModule;
import fr.shall0wer.trainyapi.playerdata.LevelMod;
import fr.shall0wer.trainyapi.playerdata.Settings;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.managers.CoreManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class DevCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if(LevelMod.getModLevel(player) < 4){
            player.sendMessage("§fCommande inconnue.");
            return false;
        }

        if(args.length == 0){
            player.sendMessage("§r \n§9§lDEV §8§l❙ §fListe des commandes développeurs:" +
                    "\n§8» §9/dev §bmaintenance §f: Active/Désactive la maintenance serveur." +
                    "\n§8» §9/dev §bserveur §f: Se téléporter au serveur de développement." +
                    "\n§8» §9/dev §bbypass §f: Permet de bypass les interdiction au lobby." +
                    "\n§r ");
            return false;
        }

        if(args.length == 1){ // ``/dev maintenance``
            if(args[0].equalsIgnoreCase("maintenance")){
                if(CoreManager.getMaintenance()){
                    try {
                        CoreManager.setMaintenance(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.sendMessage("§9§lDEV §8§l❙ §fLa maintenance de ce serveur est §cdésactivée§f.");
                    return true;
                }
                try {
                    CoreManager.setMaintenance(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage("§9§lDEV §8§l❙ §fLa maintenance de ce serveur est §aactivée§f.");
                return true;
            }
            if(args[0].equalsIgnoreCase("serveur") || args[0].equalsIgnoreCase("server")){
                player.sendMessage("§9§lDEV §8§l❙ §fPréparez vous, la téléportation va commencer...");
                ByteArrayDataOutput outjl = ByteStreams.newDataOutput();
                outjl.writeUTF("Connect");
                outjl.writeUTF("Dev");
                player.sendPluginMessage(Main.getInstance(), "BungeeCord", outjl.toByteArray());
                return true;
            }
            if(args[0].equalsIgnoreCase("bypass")){
                if(Settings.getOption(player, "DevBypass")){
                    try {
                        Settings.setOption(player, "DevBypass", false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.sendMessage("§9§lDEV §8§l❙ §fBypass: §cdésactivé§f.");
                    return true;
                }
                try {
                    Settings.setOption(player, "DevBypass", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage("§9§lDEV §8§l❙ §fBypass: §aactivé§f.");
            }
        }
        return false;
    }
}
