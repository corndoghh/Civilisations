package es.harleyhugh.civilisations.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static es.harleyhugh.civilisations.CPlayer.getInstance;

public class TestEvent implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (!getInstance(p).getTest()) {
            return;
        }


    }

}
