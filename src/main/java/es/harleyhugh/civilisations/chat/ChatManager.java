package es.harleyhugh.civilisations.chat;

import es.harleyhugh.civilisations.CPlayer;
import es.harleyhugh.civilisations.Civilisations;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static es.harleyhugh.civilisations.CPlayer.getInstance;

public class ChatManager {


    private final Civilisations plugin;

    private final YamlConfiguration chatYml;
    private final File chat;


    public ChatManager(Civilisations plugin){
        this.plugin = plugin;
        chat = new File(plugin.getDataFolder(), "chats.yml");
        if (!chat.exists()) {
            try {
                chat.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        chatYml = YamlConfiguration.loadConfiguration(chat);
    }

    public void setChats(Player player, List<Chats> chats) {
        chatYml.set(player.getUniqueId().toString(), chats.stream().map(Enum::name).collect(Collectors.toList()));
        try {
            chatYml.save(chat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CPlayer p = getInstance(player);
        Chats maxChat = Chats.DEFAULT;


        for (Chats chat: chats) {
            chat.addToPlayerList(player);
            if (maxChat.getWeight() < chat.getWeight()) {
                maxChat = chat;
            }
        }


        p.setChats(chats);
        p.setMaxChat(maxChat);

    }


    public void loadPlayerChat(Player player) {

        List<Chats> chats;

        if (chatYml.contains(player.getUniqueId().toString())) {
            chats = chatYml.getStringList(player.getUniqueId().toString()).stream().map(Chats::valueOf).collect(Collectors.toList());
            try {
                chatYml.save(chat);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            chats = Collections.singletonList(Chats.DEFAULT);
        }

        this.setChats(player, chats);

    }

    public Component formatMessagePlayer(Player player, Component message) {
        Chats chat = getInstance(player).getMaxChat();
        return chat.getPrefix()
                .append(Component.text(" "))
                .append(player.displayName().color(chat.nameColour()))
                .append(Component.text(": "))
                .append(message.color(chat.chatColour()));
    }


    public void fireChat(Player player, Component message) {
        List<Chats> sendingChats = getInstance(player).getChats();
        List<UUID> playersSendTo = new ArrayList<>();
        Component formattedMessage = formatMessagePlayer(player, message);

        Bukkit.getConsoleSender().sendMessage(formattedMessage);

        for (Chats chat: sendingChats) {
            playersSendTo.removeAll(chat.getPlayerListUUID());
            playersSendTo.addAll(chat.getPlayerListUUID());
        }

        for (UUID playerUUID: playersSendTo) {
            Objects.requireNonNull(Bukkit.getPlayer(playerUUID)).sendMessage(formattedMessage);
        }


    }
}
