package es.harleyhugh.civilisations.chat;

import es.harleyhugh.civilisations.Civilisations;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ChatGui implements Listener {

    private final Civilisations plugin;

    public static final Inventory chatGui = Bukkit.createInventory(null, 9, Component.text("Chat settings"));

    public static void load() {
        chatGui.setItem(1, new ItemStack(Material.GRASS_BLOCK, 64));
    }

    public ChatGui(Civilisations plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (!e.getInventory().equals(chatGui)) {
            return;
        }
        e.setCancelled(true);

        //Some game breaking error here :p
        int addedAmount = 0;
        for (Integer slot: e.getNewItems().keySet()) {
            if (Objects.equals(e.getView().getInventory(slot), e.getView().getTopInventory())) {
                ItemStack existingItem = e.getInventory().getItem(slot);
                int amountPending = e.getNewItems().get(slot).getAmount();
                if (existingItem != null) {
                    amountPending -= existingItem.getAmount();
                }
                addedAmount += amountPending;
                continue;
            }
            int slotIndex = slot;
            if (slot > 35) { slotIndex -= 36; }
            e.getView().getBottomInventory().setItem(slotIndex, e.getNewItems().get(slot));

        }
        ItemStack cursor = new ItemStack(e.getOldCursor().getType());
        cursor.setAmount(0);


        if (e.getCursor() != null) {
            cursor.setAmount(e.getCursor().getAmount());
        }

        cursor.setAmount(cursor.getAmount() + addedAmount);
        e.getWhoClicked().sendMessage("a: " + cursor.getAmount());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            e.getWhoClicked().setItemOnCursor(cursor);
        }, 1);
    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(chatGui)) {
            return;
        }
        if (!Objects.equals(e.getClickedInventory(), e.getView().getTopInventory())) {
            if (!(e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY || e.getAction() == InventoryAction.SWAP_WITH_CURSOR || e.getAction() == InventoryAction.COLLECT_TO_CURSOR)) {
                return;
            }
            // 0 35
        }
        e.setCancelled(true);

        if (e.getAction() == InventoryAction.COLLECT_TO_CURSOR) {
            ItemStack item = e.getCursor();
            if (item == null || item.getMaxStackSize() <= 0 || item.getAmount() == item.getMaxStackSize()) {
                return;
            }
            Inventory bottom = e.getView().getBottomInventory();
            int amountAdded = 0;
            int i = 9;
            do {
                if (i > 35) { i -= 36; }
                if (bottom.getItem(i) == null || !Objects.requireNonNull(bottom.getItem(i)).getType().equals(item.getType())) {
                    i++;
                    continue;
                }

                if (Objects.requireNonNull(bottom.getItem(i)).isSimilar(item)) {
                    if ((amountAdded + Objects.requireNonNull(bottom.getItem(i)).getAmount() + item.getAmount()) >= item.getMaxStackSize()) {
                        Objects.requireNonNull(bottom.getItem(i)).setAmount(Objects.requireNonNull(bottom.getItem(i)).getAmount() + amountAdded);
                        amountAdded = item.getMaxStackSize() - item.getAmount();
                        Objects.requireNonNull(bottom.getItem(i)).setAmount(Objects.requireNonNull(bottom.getItem(i)).getAmount() - amountAdded);
                        break;
                    }
                    amountAdded += Objects.requireNonNull(bottom.getItem(i)).getAmount();
                    bottom.setItem(i, new ItemStack(Material.AIR));

                }
                i++;
            } while (i != 9);
            item.setAmount(amountAdded + item.getAmount());
//            e.getWhoClicked().sendMessage("Fuck 3");
        }





    }


}
