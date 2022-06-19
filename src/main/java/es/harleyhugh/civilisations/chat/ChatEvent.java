package es.harleyhugh.civilisations.chat;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ChatEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancelled;
    private final Player player;
    private final String messageOrigin;
    private final Component message;

    public ChatEvent(String messageOrigin, Component message, Player player) {
        super(true);
        cancelled = false;
        this.player = player;
        this.messageOrigin = messageOrigin;
        this.message = message;
    }

    public ChatEvent(String messageOrigin, Component message) {
        cancelled = false;
        this.player = null;
        this.messageOrigin = messageOrigin;
        this.message = message;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getMessageOrigin() {
        return this.messageOrigin;
    }

    public Component getMessage() {
        return this.message;
    }

}
