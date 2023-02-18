package fr.shall0wer.trainycore.listeners;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import fr.shall0wer.trainyapi.playerdata.Moderation;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.managers.CoreManager;
import fr.shall0wer.trainycore.utils.ItemBuilder;
import fr.shall0wer.trainycore.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(CoreManager.getMaintenance()){
            if(LevelMod.getModLevel(e.getPlayer()) < 4){
                e.getPlayer().kickPlayer("§cErreur §8§l❙ §cUne maintenance sur ce serveur est en cours. Essayez plus tard !");
            }
        }

        Player player = e.getPlayer();
        if(Moderation.isInModModeration(player)){
            if(!Moderation.moderateurs.contains(player)){
                Moderation.moderateurs.add(player);
            }
            player.getInventory().clear();
            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);
            player.setAllowFlight(true);
            player.setFlying(true);
            player.setGameMode(GameMode.SURVIVAL);

            for(Player p : Bukkit.getOnlinePlayers()){
                if(!Moderation.isInModModeration(p)){
                    p.hidePlayer(player);
                }
            }

            player.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, (byte) 10).setName("§6Vanish §8❙ §aActivé").toItemStack());
            player.getInventory().setItem(1, new ItemBuilder(Material.WATCH).setName("§6Téléportation aléatoire").toItemStack());
            player.getInventory().setItem(3, new ItemBuilder(Material.PACKED_ICE).setName("§6Freeze").toItemStack());
            player.getInventory().setItem(4, new ItemBuilder(Material.GOLD_SWORD).setName("§6Épée Knockback §bII").addEnchant(Enchantment.KNOCKBACK, 2).setInfinityDurability().toItemStack());
            player.getInventory().setItem(5, new ItemBuilder(Material.BOOK).setName("§6Ouvrir l'inventaire").toItemStack());
            player.getInventory().setItem(7, new ItemBuilder(Material.STONE_HOE).setName("§6Kill").toItemStack());
            player.getInventory().setItem(8, new ItemBuilder(Material.DIAMOND_HOE).setName("§6Vérification CPS").toItemStack());
            player.sendMessage("§r \n§c§lMOD §8§l❙ §cMode modération actif.\n§r ");
            player.getInventory().setItem(2, null);
            return;
        }


    }
}
