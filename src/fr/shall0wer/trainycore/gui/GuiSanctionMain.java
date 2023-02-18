package fr.shall0wer.trainycore.gui;

import fr.shall0wer.trainyapi.playerdata.LevelMod;
import fr.shall0wer.trainyapi.utils.HeadBuilder;
import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.utils.GuiBuilder;
import fr.shall0wer.trainycore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiSanctionMain implements GuiBuilder {
    @Override
    public String name() {
        return "§cSanctions §f(§cMain§f)";
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
        inv.setItem(20, new ItemBuilder(Material.PAPER).setName("§cCatégorie §8❙ §fMessages").toItemStack());
        inv.setItem(22, new ItemBuilder(Material.IRON_SWORD).setName("§cCatégorie §8❙ §fTriche").toItemStack());
        inv.setItem(24, new ItemBuilder(Material.CHEST).setName("§cCatégorie §8❙ §fAbus").toItemStack());
        if(LevelMod.getModLevel(player) >= 5){
            inv.setItem(45, new ItemBuilder(Material.REDSTONE_BLOCK).setName("§cCatégorie §8❙ §4Admin").toItemStack());
        }
        inv.setItem(53, new ItemBuilder(Material.ARROW).setName("§cFermer").toItemStack());

    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {

        switch (current.getType()){
            case PAPER:
                if(current.getItemMeta().getDisplayName().endsWith("Messages")){
                    Main.getInstance().getGuiManager().open(player, GuiSanctionMessages.class);
                }
                break;
            case ARROW:
                player.closeInventory();
                break;
        }

    }
}
