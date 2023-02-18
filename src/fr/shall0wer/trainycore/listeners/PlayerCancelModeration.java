package fr.shall0wer.trainycore.listeners;

import fr.shall0wer.trainyapi.playerdata.Moderation;
import fr.shall0wer.trainycore.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import javax.jws.WebParam;
import java.util.List;

public class PlayerCancelModeration implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(Moderation.isModerationActive(e.getPlayer()) || Main.getInstance().freezedPlayers.containsKey(e.getPlayer())){
            e.setCancelled(true);
            return;
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(Moderation.isModerationActive(e.getPlayer()) || Main.getInstance().freezedPlayers.containsKey(e.getPlayer())){
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onBreak(BlockDamageEvent e){
        if(Moderation.isModerationActive(e.getPlayer()) || Main.getInstance().freezedPlayers.containsKey(e.getPlayer())){
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        if(Moderation.isModerationActive(e.getPlayer()) || Main.getInstance().freezedPlayers.containsKey(e.getPlayer())){
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(Moderation.isModerationActive(e.getPlayer()) || Main.getInstance().freezedPlayers.containsKey(e.getPlayer())){
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();
            if(Moderation.isModerationActive(player)){
                player.sendMessage("§c§lMOD §8§l❙ §fDégâts : §c" + e.getDamage());
                e.setCancelled(true);
                return;
            }
            if(Main.getInstance().freezedPlayers.containsKey(player)){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getWhoClicked() instanceof Player){
            if(Moderation.isModerationActive((Player) e.getWhoClicked())){
                if(e.getInventory().getName().startsWith("Inventaire de ") || e.getInventory().equals(e.getInventory())){
                    e.setCancelled(true);
                    return;
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().endsWith("S'y téléporter")){
                    Bukkit.dispatchCommand(e.getWhoClicked(), "/tp " + Main.getInstance().sanctionsCorrespond.get(e.getWhoClicked()));
                    return;
                }
            }
            if(Main.getInstance().freezedPlayers.containsKey(e.getWhoClicked())){
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(Main.getInstance().freezedPlayers.containsKey(e.getPlayer())){
            e.setTo(e.getFrom());
            return;
        }
    }

}
