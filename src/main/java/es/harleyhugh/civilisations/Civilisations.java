package es.harleyhugh.civilisations;

import es.harleyhugh.civilisations.chat.ChatEvent;
import es.harleyhugh.civilisations.chat.ChatManager;
import es.harleyhugh.civilisations.commands.Chat;
import es.harleyhugh.civilisations.commands.Impersonate;
import es.harleyhugh.civilisations.commands.SudoCommand;
import es.harleyhugh.civilisations.events.Command;
import es.harleyhugh.civilisations.events.TestEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Civilisations extends JavaPlugin {

    private PermissionManager permissionManager;
    private ChatManager chatManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        PluginManager pluginManager = Bukkit.getPluginManager();
        this.permissionManager = new PermissionManager();
        this.chatManager = new ChatManager(this);

        pluginManager.registerEvents(new Command(this), this);
        pluginManager.registerEvents(new ChatEvent(this), this);
        pluginManager.registerEvents(new TestEvent(), this);
        Objects.requireNonNull(getCommand("sudo")).setExecutor(new SudoCommand());
        Objects.requireNonNull(getCommand("impersonate")).setExecutor(new Impersonate());
        Objects.requireNonNull(getCommand("chat")).setExecutor(new Chat(this));
        Objects.requireNonNull(getCommand("test")).setExecutor(new Test());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PermissionManager getPermissionManager() { return this.permissionManager; }
    public ChatManager getChatManager() { return this.chatManager; }

}
