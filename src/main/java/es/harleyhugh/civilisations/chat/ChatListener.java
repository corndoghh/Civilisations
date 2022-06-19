package es.harleyhugh.civilisations.chat;

import es.harleyhugh.civilisations.Civilisations;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static es.harleyhugh.civilisations.CPlayer.getInstance;

public class ChatListener implements Listener {

    private final Civilisations plugin;

    public ChatListener(Civilisations plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent j) {
        plugin.getChatManager().loadPlayerChat(j.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent q) {

        ChatEvent chatEvent = new ChatEvent("Leave", q.quitMessage());
        Bukkit.getPluginManager().callEvent(chatEvent);

        if (!chatEvent.isCancelled()) {
            q.quitMessage(null);
        }

        plugin.getChatManager().chatCleanUp(q.getPlayer(), getInstance(q.getPlayer()).getChats());
    }

    @EventHandler
    public void onChatEvent(AsyncChatEvent c) {
        c.setCancelled(true);

        ChatEvent chatEvent = new ChatEvent("Chat", c.message(), c.getPlayer());
        Bukkit.getPluginManager().callEvent(chatEvent);

        if (chatEvent.isCancelled()) {
            return;
        }

        plugin.getChatManager().fireChat(c.getPlayer(), c.message());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        ChatEvent chatEvent = new ChatEvent("Death", e.deathMessage());
        Bukkit.getPluginManager().callEvent(chatEvent);

        if (chatEvent.isCancelled()) {
            return;
        }

        e.deathMessage(null);
    }


}
