package fr.shall0wer.trainycore.gui;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.utils.GuiBuilder;
import fr.shall0wer.trainycore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class GuiSanctionMessages implements GuiBuilder {
    @Override
    public String name() {
        return "§cSanction §f(§cMessages§f)";
    }

    @Override
    public int getSize() {
        return 6 * 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {

        String target = Main.getInstance().sanctionsCorrespond.get(player);
        String targetP = Main.getInstance().targetPremium.get(player);
        inv.setItem(4, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(target).setName("§cJoueur: §f" + target).setLore(targetP).toItemStack());
        inv.setItem(20, new ItemBuilder(Material.ROTTEN_FLESH).setName("§cSanction §8❙ §fMauvais langage").setLore("§r", "§cType §8❙ §fMute", "§cTemps §8❙ §f10 minutes").toItemStack());
        inv.setItem(21, new ItemBuilder(Material.TNT).setName("§cSanction §8❙ §fSpam / Flood").setLore("§r", "§cType §8❙ §fMute", "§cTemps §8❙ §f10 minutes").toItemStack());
        inv.setItem(22, new ItemBuilder(Material.BOOK).setName("§cSanction §8❙ §fContournement Protection Chat").setLore("§r", "§cType §8❙ §fMute", "§cTemps §8❙ §f20 minutes").toItemStack());
        inv.setItem(23, new ItemBuilder(Material.COAL, 1, (byte) 0).setName("§cSanction §8❙ §fVantardise").setLore("§r", "§cType §8❙ §fMute", "§cTemps §8❙ §f15 minutes").toItemStack());
        inv.setItem(24, new ItemBuilder(Material.GOLD_SWORD).setName("§cSanction §8❙ §fProvocation").setLore("§r", "§cType §8❙ §fMute", "§cTemps §8❙ §f30 minutes").toItemStack());
        inv.setItem(29, new ItemBuilder(Material.BANNER, 1, (byte) 0).setName("§cSanction §8❙ §fMenaces DDOS / DOX / Hack").setLore("§r", "§cType §8❙ §fBannissement", "§cTemps §8❙ §f7 jours").toItemStack());
        inv.setItem(30, new ItemBuilder(Material.EYE_OF_ENDER).setName("§cSanction §8❙ §fIncitation à l'infraction").setLore("§r", "§cType §8❙ §fMute", "§cTemps §8❙ §f30 minutes").toItemStack());
        inv.setItem(31, new ItemBuilder(Material.SIGN).setName("§cSanction §8❙ §fPublicité / Lien interdit").setLore("§r", "§cType §8❙ §fMute", "§cTemps §8❙ §f1 heure").toItemStack());
        inv.setItem(32, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 0).setName("§cSanction §8❙ §fMenaces IG").setLore("§r", "§cType §8❙ §fBannissement", "§cTemps §8❙ §f1 jour").toItemStack());
        inv.setItem(33, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 1).setName("§cSanction §8❙ §fMenaces IRL").setLore("§r", "§cType §8❙ §fBanissement", "§cTemps §8❙ §f7 jours").toItemStack());
        if(LevelMod.getModLevel(player) > 2){
            inv.setItem(53, new ItemBuilder(Material.ARROW).setName("§cRetour").setLore("§7§oVers: Menu Principal").toItemStack());
        }

    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        switch (current.getType()){
            case ARROW:
                if(current.getItemMeta().getLore().get(0).endsWith("Menu Principal")){
                    Main.getInstance().getGuiManager().open(player, GuiSanctionMain.class);
                    break;
                }
        }
    }
}
