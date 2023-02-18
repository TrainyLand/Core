package fr.shall0wer.trainycore.commands.misc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage("§r \n§9§lDISCORD §8§l❙ §fNotre discord: §bhttps://www.discord.gg/trainyland\n§r ");
        return true;
    }
}
