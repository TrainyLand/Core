package fr.shall0wer.trainycore.listeners;

import fr.shall0wer.trainycore.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerClicksListener implements Listener {

    @EventHandler
    public void verifOnLeftClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(Main.getInstance().cpsverif.containsKey(player.getUniqueId()) || Main.getInstance().cpsverifright.containsKey(player.getUniqueId())){
            if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                Double cps = Main.getInstance().cpsverif.get(player.getUniqueId());
                cps++;
                Main.getInstance().cpsverif.put(player.getUniqueId(), cps);
                return;
            }
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                Double cps = Main.getInstance().cpsverifright.get(player.getUniqueId());
                cps++;
                Main.getInstance().cpsverifright.put(player.getUniqueId(), cps);
                return;
            }
        }
    }
}
