package fr.shall0wer.trainycore.listeners;

import fr.shall0wer.trainyapi.playerdata.*;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.utils.ItemBuilder;
import fr.shall0wer.trainycore.utils.Title;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerEntityInteract implements Listener {

    private List<UUID> cooldown = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if(!Moderation.isModerationActive(player)){
            return;
        }
        if(!(e.getRightClicked() instanceof Player)){
            return;
        }
        if(cooldown.contains(player.getUniqueId())) {
            return;
        }
        Player target = (Player) e.getRightClicked();
        e.setCancelled(true);

        switch (player.getInventory().getItemInHand().getType()){
            case PACKED_ICE:
                if(Main.getInstance().moderateurs.contains(target.getUniqueId())){
                    player.sendMessage("§cErreur: Vous ne pouvez pas immobiliser les modérateurs en mode modération.");
                    return;
                }
                if(Main.getInstance().freezedPlayers.containsKey(target.getUniqueId())){
                    Main.getInstance().freezedPlayers.remove(target.getUniqueId());
                    target.sendMessage("§b§lFREEZE §8§l❙ §aVous n'êtes plus immobilisé !");
                    player.sendMessage("§c§lMOD §8§l❙ §fVous avez unfreeze §b" + target.getName() + "§f !");
                } else {
                    Main.getInstance().freezedPlayers.put(target.getUniqueId(), target.getLocation());
                    Title.sendTitle(target, 20, 3 * 20, 20, "§4Vous êtes immobilisé !", "§cRegardez votre chat !");
                    target.sendMessage("§b§lFREEZE §8§l❙ §cVous êtes immobilisé, veuillez vous connecter sur notre discord dans un salon demande d'aide ! §9(/discord)");
                    player.sendMessage("§c§lMOD §8§l❙ §fVous avez freeze §b" + target.getName() + "§f !");
                }
                cooldown.add(player.getUniqueId());
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> cooldown.remove(player.getUniqueId()), 5);
                break;

            case STONE_HOE:
                if(Main.getInstance().moderateurs.contains(target.getUniqueId())){
                    player.sendMessage("§cErreur: Vous ne pouvez pas tuer les modérateurs en mode modération.");
                    cooldown.add(player.getUniqueId());
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> cooldown.remove(player.getUniqueId()), 5);
                    return;
                }
                target.damage(target.getHealth());
                cooldown.add(player.getUniqueId());
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> cooldown.remove(player.getUniqueId()), 5);
                break;

            case DIAMOND_HOE:
                if(Main.getInstance().cpsverif.containsKey(target.getUniqueId()) || Main.getInstance().cpsverifright.containsKey(target.getUniqueId())){
                    player.sendMessage("§c§lMOD §8§l❙ §fUn test sur ce joueur est §cdéjà en cours§f...");
                    cooldown.add(player.getUniqueId());
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> cooldown.remove(player.getUniqueId()), 5);
                    return;
                }
                Main.getInstance().cpsverif.put(target.getUniqueId(), 0.0);
                Main.getInstance().cpsverifright.put(target.getUniqueId(), 0.0);
                player.sendMessage("§c§lMOD §8§l❙ §fLancement du test sur §b" + target.getName() + " §f...");
                cooldown.add(player.getUniqueId());
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> cooldown.remove(player.getUniqueId()), 5);
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    Double cpsl = Main.getInstance().cpsverif.get(target.getUniqueId());
                    Double cps1 = Main.getInstance().cpsverif.get(target.getUniqueId());
                    Double cpsr = Main.getInstance().cpsverifright.get(target.getUniqueId());
                    Double cps2 = Main.getInstance().cpsverifright.get(target.getUniqueId());
                    cpsl = cpsl / 5;
                    // if(cpsl >= 3.5){ cpsl += 3; }
                    cpsr = cpsr / 5;
                    // if(cpsr >= 3.5){ cpsr += 3; }
                    player.sendMessage("§6§m--------------§6 Résultats §6§m--------------\n§a \n§fJoueur: §b" + target.getName() + "\n§fClique gauche §7» §b" + cpsl + "§f / §b" + cps1 + "§7   (1s / 5s)" + "\n§fClique droit §7» §b" + cpsr + "§f / §b" + cps2 + "§7   (1s / 5s)\n§a \n§6§m------------------------------------");
                    Main.getInstance().cpsverif.remove(target.getUniqueId());
                    Main.getInstance().cpsverifright.remove(target.getUniqueId());
                }, 20 * 5);
                break;

            case BOOK:
                Main.getInstance().sanctionsCorrespond.put(player, target.getName());
                Inventory inv = Bukkit.createInventory(null, 6 * 9, "§cInventaire de " + target.getName());
                EntityPlayer nmsTarget = ((CraftPlayer) target).getHandle();
                inv.setItem(0, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(target.getName()).setName("§c" + target.getName()).setLore("§a",
                        "§fUUID: §b" + target.getUniqueId(),
                        "§fGrade: " + Rank.getRank(target).replace("&", "§"),
                        "§fCoins: §b" + Coins.getCoins(target.getName()),
                        "§fCrédits: §b" + Credits.getCredits(target.getName()),
                        "§fVIP Level: §b" + LevelMod.getVIPLevel(target),
                        "§fMOD Level: §b" + LevelMod.getModLevel(target),
                        "",
                        "§fPing: §b" + nmsTarget.ping).toItemStack());
                inv.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(2, new ItemBuilder(Material.EYE_OF_ENDER).setName("§6S'y téléporter").toItemStack());
                inv.setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(11, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(14, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(15, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
                inv.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());


                /**
                 * if(target.getInventory().getItem(i)){
                 *                         inv.setItem(s, target.getInventory().getItem(i));
                 *                         s++;
                 *                     }
                 */

                int s = 18;
                for(int i = 0; i < 36; i++){
                    inv.setItem(s, target.getInventory().getItem(i));
                    s++;
                }

                inv.setItem(5, target.getInventory().getHelmet());
                inv.setItem(6, target.getInventory().getChestplate());
                inv.setItem(7, target.getInventory().getLeggings());
                inv.setItem(8, target.getInventory().getBoots());

                player.openInventory(inv);
                break;
            default: break;
        }
    }
}
