package fr.shall0wer.trainycore.commands;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.gui.GuiSanctionMessages;
import fr.shall0wer.trainycore.managers.ReportManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ChatReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        if(args.length == 0){
            sender.sendMessage("§fCommande inconnue.");
            return false;
        }

        Player player = (Player) sender;
        if(ReportManager.isAlreadyReported(args[0])) {
            sender.sendMessage("§cErreur: Ce message a déjà été signalé !");
            return false;
        }

        if(!ReportManager.existReport(args[0])){
            sender.sendMessage("§cErreur: Ce message n'existe pas !");
            return false;
        }

        if(args.length == 1){
            if(ReportManager.getPreInfoReport(args[0], "Joueur").equalsIgnoreCase(player.getName())){
                player.sendMessage("§cErreur: Vous ne pouvez pas vous signaler vous-même !");
                return false;
            }
        }

        if(LevelMod.getModLevel(player) > 1){
            Main.getInstance().sanctionsCorrespond.put(player, ReportManager.getPreInfoReport(args[0], "Joueur"));
            Main.getInstance().getGuiManager().open(player, GuiSanctionMessages.class);
            return true;
        }

        if(args.length == 1){
            try {
                ReportManager.setReporter(args[0], player.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            TextComponent confirm = new TextComponent("§aCliquez ici pour confirmer le signalement !");
            confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aConfirmer le signalement de §b" + ReportManager.getPreInfoReport(args[0], "Joueur")).create()));
            confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/chatreport " + args[0] + " confirm"));
            player.sendMessage("§e§m----------------------------" +
                    "\n§a " +
                    "\n   §fVoulez-vous signaler le message de §b" + ReportManager.getPreInfoReport(args[0], "Joueur") + "§f:" +
                    "\n   §7- §o" + ReportManager.getPreInfoReport(args[0], "Message") +
                    "\n§a ");
            player.spigot().sendMessage(confirm);
            player.sendMessage("§a \n§e§m----------------------------");
            return true;
        }

        if(args.length == 2){
            if(args[1].equals("confirm")){
                try {
                    ReportManager.setReportInConfirmation(args[0]);
                    player.sendMessage("§c§lREPORTS §8§l❙ §aVotre signalement a bien été enregistré !");
                } catch (IOException e) {
                    e.printStackTrace();
                    player.sendMessage("§cUne erreur est survenue (Code: #0246)");
                }
            } else {
                player.sendMessage("§fCommande inconnue.");
            }
        }
        return false;
    }
}
