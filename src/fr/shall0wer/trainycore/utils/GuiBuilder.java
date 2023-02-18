package fr.shall0wer.trainycore.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public interface GuiBuilder {
	public abstract String name();
	public abstract int getSize();
	public abstract void contents(Player player, Inventory inv);
	public abstract void onClick(Player player, Inventory inv, ItemStack current, int slot) throws IOException, NoSuchMethodException;
}