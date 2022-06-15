package es.harleyhugh.civilisations.commands;

import es.harleyhugh.civilisations.Civilisations;
import es.harleyhugh.civilisations.chat.Chats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Chat implements CommandExecutor {

    private final Civilisations plugin;

    public Chat(Civilisations plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        plugin.getChatManager().setChats(((Player) sender), List.of(Chats.DEFAULT, Chats.ADMIN));
        sender.sendMessage("Set to stuff@");
        return true;
    }
}
