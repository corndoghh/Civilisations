package es.harleyhugh.civilisations.inventory.inventoryEvents;

import es.harleyhugh.civilisations.inventory.enums.GuiInteractType;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GuiInteract extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;
    private GuiInteractType guiInteractType;

    public GuiInteract(GuiInteractType guiInteractType) {
        this.guiInteractType = guiInteractType;


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
}
