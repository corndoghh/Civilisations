package es.harleyhugh.civilisations.events;

import es.harleyhugh.civilisations.Civilisations;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;

public class Command implements Listener {

    private final Civilisations plugin;

    public Command(Civilisations plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTab(PlayerCommandSendEvent e) {
        List<String> blockedCommands = plugin.getPermissionManager().getBlocked(e.getPlayer());
        e.getCommands().removeAll(blockedCommands);
    }

}
