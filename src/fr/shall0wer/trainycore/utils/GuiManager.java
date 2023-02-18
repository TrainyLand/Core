package fr.shall0wer.trainycore.utils;

import fr.shall0wer.trainycore.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class GuiManager implements Listener {


    @EventHandler
	public void onClick(InventoryClickEvent event){
	
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getInventory();
		ItemStack current = event.getCurrentItem();

		
		if(event.getCurrentItem() == null) return;

		Main.getInstance().getRegisteredMenus().values().stream()
		.filter(menu -> inv.getName().equalsIgnoreCase(menu.name()))
		.forEach(menu -> {
			try {
				menu.onClick(player, inv, current, event.getSlot());
			} catch (IOException | NoSuchMethodException e) {
				e.printStackTrace();
			}
			event.setCancelled(true);
		});
	}

	public void addMenu(GuiBuilder m){
		Main.getInstance().getRegisteredMenus().put(m.getClass(), m);
	}

	public void open(Player player, Class<? extends GuiBuilder> gClass) {
		
		if(!Main.getInstance().getRegisteredMenus().containsKey(gClass)) return;

		GuiBuilder menu = Main.getInstance().getRegisteredMenus().get(gClass);
		Inventory inv = Bukkit.createInventory(null, menu.getSize(), menu.name());
		menu.contents(player, inv);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				player.openInventory(inv);
			}
			
		}.runTaskLater(Main.getInstance(), 1);
		
	}

}