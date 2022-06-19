package es.harleyhugh.civilisations.commands;

import es.harleyhugh.civilisations.Civilisations;
import es.harleyhugh.civilisations.chat.Chats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static es.harleyhugh.civilisations.CPlayer.getInstance;

public class Chat implements CommandExecutor {

    private final Civilisations plugin;

    public Chat(Civilisations plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        List<String> currentChats = getInstance(player).getChats().stream().map(Chats::name).collect(Collectors.toList());
        List<String> chatsBuffer = Arrays.stream(Arrays.copyOfRange(args, 1, args.length)).map(String::toUpperCase).toList();


        switch (args[0].toLowerCase()) {
            case "-r" -> {
                currentChats.removeAll(chatsBuffer);
                if (currentChats.isEmpty()) {
                    currentChats = Collections.singletonList("DEFAULT");
                }
                plugin.getChatManager().setChats(player, currentChats.stream().map(Chats::valueOf).collect(Collectors.toList()));
            }
            case "-a" -> {
                currentChats.removeAll(chatsBuffer);
                currentChats.addAll(chatsBuffer);
                plugin.getChatManager().setChats(player, currentChats.stream().map(Chats::valueOf).collect(Collectors.toList()));
            }
            case "gui" -> {
                plugin.getChatManager().openGui(player);
            }
        }

        return true;
    }
}
