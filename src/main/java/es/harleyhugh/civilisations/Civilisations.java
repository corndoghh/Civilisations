package es.harleyhugh.civilisations;

import es.harleyhugh.civilisations.chat.ChatGui;
import es.harleyhugh.civilisations.chat.ChatListener;
import es.harleyhugh.civilisations.chat.ChatManager;
import es.harleyhugh.civilisations.commands.Chat;
import es.harleyhugh.civilisations.commands.Impersonate;
import es.harleyhugh.civilisations.commands.SudoCommand;
import es.harleyhugh.civilisations.events.Command;
import es.harleyhugh.civilisations.inventory.InventoryManager;
import es.harleyhugh.civilisations.rank.RankListener;
import es.harleyhugh.civilisations.rank.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Civilisations extends JavaPlugin {

    private PermissionManager permissionManager;
    private ChatManager chatManager;
    private RankManager rankManager;
    private InventoryManager inventoryManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        PluginManager pluginManager = Bukkit.getPluginManager();
        this.permissionManager = new PermissionManager();
        this.chatManager = new ChatManager(this);
        this.rankManager = new RankManager(this);
        this.inventoryManager = new InventoryManager();

        pluginManager.registerEvents(new Command(this), this);
        pluginManager.registerEvents(new ChatListener(this), this);
        pluginManager.registerEvents(new RankListener(this), this);
        pluginManager.registerEvents(new ChatGui(this), this);
        Objects.requireNonNull(getCommand("sudo")).setExecutor(new SudoCommand());
        Objects.requireNonNull(getCommand("impersonate")).setExecutor(new Impersonate());
        Objects.requireNonNull(getCommand("chat")).setExecutor(new Chat(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PermissionManager getPermissionManager() { return this.permissionManager; }
    public ChatManager getChatManager() { return this.chatManager; }
    public RankManager getRankManager() {return this.rankManager; }
    public InventoryManager getInventoryManager() { return this.inventoryManager; }

}
