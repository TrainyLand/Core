package fr.shall0wer.trainycore.listeners;

import fr.shall0wer.trainyapi.playerdata.Moderation;
import fr.shall0wer.trainycore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Random;

public class PlayerInteract implements Listener {

    @EventHandler
    public void modInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(!Moderation.isModerationActive(player)){
            return;
        }
        e.setCancelled(true);

        if(player.hasPermission("modo.mod")){
            switch (player.getInventory().getItemInHand().getType()){
                case INK_SACK:
                    if(e.getItem().getItemMeta().getDisplayName().endsWith("Désactivé")){
                        e.getPlayer().getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, (byte) 10).setName("§6Vanish §8§l❙ §aActivé").toItemStack());
                        player.sendMessage("§c§lMOD §8§l❙ §fVanish : §aActivé");
                        Bukkit.getOnlinePlayers().forEach(loopedPlayer -> loopedPlayer.hidePlayer(player));
                        break;
                    }
                    if(e.getItem().getItemMeta().getDisplayName().endsWith("Activé")){
                        e.getPlayer().getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, (byte) 8).setName("§6Vanish §8§l❙ §cDésactivé").toItemStack());
                        player.sendMessage("§c§lMOD §8§l❙ §fVanish : §cDésactivé");
                        Bukkit.getOnlinePlayers().forEach(loopedPlayer -> loopedPlayer.showPlayer(player));
                        break;
                    }

                case WATCH:
                    if(e.getMaterial().equals(Material.WATCH)){
                        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                            ArrayList<Player> playersList = new ArrayList<>();
                            for (Player players : Bukkit.getOnlinePlayers()){
                                    if(!(players.getName() == player.getName()) || !(Moderation.isModerationActive(players))){
                                    playersList.add(players);
                                }
                            }
                            if(playersList.isEmpty()){
                                player.sendMessage("§cErreur: Il n'y a personne à qui se téléporter !");
                                break;
                            }
                            Player randomPlayer = playersList.get(new Random().nextInt(playersList.size()));
                            player.teleport(randomPlayer.getLocation());
                            player.sendMessage("§c§lMOD §8§l❙ §fVous vous êtes téléportés sur §b" + randomPlayer.getName() + "§f.");
                            playersList.clear();
                        }
                        return;
                    }
                    break;


                default: break;
            }
        }
    }
}
