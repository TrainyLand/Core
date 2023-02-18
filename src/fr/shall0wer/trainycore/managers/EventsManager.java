package fr.shall0wer.trainycore.managers;

import fr.shall0wer.trainycore.Main;
import fr.shall0wer.trainycore.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventsManager implements Listener {
    public static void registers() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerChat(), Main.getInstance());
        pm.registerEvents(new PlayerJoin(), Main.getInstance());
        pm.registerEvents(new PlayerQuit(), Main.getInstance());
        pm.registerEvents(new PlayerCancelModeration(), Main.getInstance());
        pm.registerEvents(new PlayerInteract(), Main.getInstance());
        pm.registerEvents(new PlayerEntityInteract(), Main.getInstance());
        pm.registerEvents(new PlayerClicksListener(), Main.getInstance());
    }

}
