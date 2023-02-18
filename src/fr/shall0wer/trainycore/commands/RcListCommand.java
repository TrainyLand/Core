package fr.shall0wer.trainycore.commands;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.gui.GuiSanctionMessages;
import fr.shall0wer.trainycore.managers.ReportManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RcListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(LevelMod.getModLevel(player) < 3){
            player.sendMessage("§cErreur: Vous n'avez pas accès à cette commande.");
            return false;
        }

        if(args.length == 0 || args.length == 1){
            Boolean state = true;
            while (state){
                if(ReportManager.hasFileReportWaiting()){
                    String uuidOfReport = ReportManager.getNameFileReport().replace(".yml", "");
                    Main.getInstance().reportCorrespond.put(player, uuidOfReport);
                    player.sendMessage("§e§m------------------------------------------" +
                            "\n§a " +
                            "\n§8» §7ID : §o" + uuidOfReport + "\n§a " +
                            "\n§8» §cJoueur signalé : §f" + ReportManager.getInfoReport(uuidOfReport, "Joueur") +
                            "\n§8» §cMessage signalé : §f" + ReportManager.getInfoReport(uuidOfReport, "Message") +
                            "\n§a \n§8» §eSignalé par : §f" + ReportManager.getInfoReport(uuidOfReport, "Reporter") + "\n§a ");
                    TextComponent oui = new TextComponent("§a[§a§lSANCTIONNER§a]");
                    oui.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cSanctionner : §4" + ReportManager.getInfoReport(uuidOfReport, "Joueur")).create()));
                    oui.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rclist " + uuidOfReport + " confirm-report"));
                    TextComponent non = new TextComponent("§c[§c§lMAUVAIS SIGNALEMENT§c]");
                    non.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cSanctionner : §4" + ReportManager.getInfoReport(uuidOfReport, "Reporter")).create()));
                    non.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rclist " + uuidOfReport + " bad-report"));
                    TextComponent ignore = new TextComponent("§7[Ignorer]");
                    ignore.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rclist " + uuidOfReport + " delete"));
                    ignore.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Ignorer le signalement").create()));
                    TextComponent space = new TextComponent("   ");
                    player.spigot().sendMessage(space, oui, space, non, space, ignore);
                    player.sendMessage("\n§e§m------------------------------------------");
                } else {
                    player.sendMessage("§c§lREPORTS §8§l❙ §fAucun signalement pour le moment.");
                    return false;
                }
                state = false;
            }
            return true;
        }

        if(args.length == 2){
            String uuidOfReport = Main.getInstance().reportCorrespond.get(player);
            if(args[1].equals("delete")){
                if(ReportManager.isReportExist(uuidOfReport)){
                    ReportManager.deleteReport(uuidOfReport);
                    Bukkit.dispatchCommand(player, "rclist");
                    return true;
                }
                return false;
            }
            if(args[1].equals("confirm-report")){
                Main.getInstance().sanctionsCorrespond.put(player, ReportManager.getInfoReport(uuidOfReport,"Joueur"));
                Main.getInstance().getGuiManager().open(player, GuiSanctionMessages.class);
                ReportManager.deleteReport(uuidOfReport);
                return true;
            }
            if(args[1].equals("bad-report")){
                Main.getInstance().sanctionsCorrespond.put(player, ReportManager.getInfoReport(uuidOfReport,"Reporter"));
                Main.getInstance().getGuiManager().open(player, GuiSanctionMessages.class);
                ReportManager.deleteReport(uuidOfReport);
                return true;
            }
        }
        return false;
    }
}
