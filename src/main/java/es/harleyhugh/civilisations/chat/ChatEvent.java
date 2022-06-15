package es.harleyhugh.civilisations.chat;

import es.harleyhugh.civilisations.Civilisations;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static es.harleyhugh.civilisations.CPlayer.getInstance;

public class ChatEvent implements Listener {

    private final Civilisations plugin;

    public ChatEvent(Civilisations plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent j) {
        plugin.getChatManager().loadPlayerChat(j.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent q) {
        getInstance(q.getPlayer()).remove();
    }

    @EventHandler
    public void onChatEvent(AsyncChatEvent c) {
        c.setCancelled(true);

        plugin.getChatManager().fireChat(c.getPlayer(), c.message());
    }


}
