package fr.shall0wer.trainycore.listeners;

import fr.shall0wer.trainyapi.playerdata.Moderation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(Moderation.moderateurs.contains(e.getPlayer())){
            Moderation.moderateurs.remove(e.getPlayer());
        }
    }
}
