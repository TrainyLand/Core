package fr.shall0wer.trainycore.commands;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import fr.shall0wer.trainyapi.premium.Premium;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.gui.GuiSanctionMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SanctionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(LevelMod.getModLevel(player) < 3){
            player.sendMessage("§cErreur: Vous n'avez pas la permission d'utiliser cette commande.");
            return false;
        }

        if(args.length == 1){
            String target = args[0];
            try {
                Boolean targetP = Premium.isUsernamePremium(target);
                String result = "§cCrack";
                if(targetP){
                    result = "§aPremium";
                }
                Main.getInstance().targetPremium.put(player, result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getInstance().sanctionsCorrespond.put(player, target);
            Main.getInstance().getGuiManager().open(player, GuiSanctionMain.class);
            return true;
        } else {
            player.sendMessage("§cUtilisation: /" + label + " <Pseudo>");
        }

        return false;
    }
}
