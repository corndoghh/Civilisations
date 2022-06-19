package es.harleyhugh.civilisations;

import es.harleyhugh.civilisations.chat.Chats;
import es.harleyhugh.civilisations.rank.Ranks;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CPlayer {

    private List<Chats> chats;
    private Chats maxChat;
    private UUID uuid;
    private Boolean test = false;
    private List<Ranks> ranks;
    private Ranks maxRank;


    private static final HashMap<UUID, CPlayer> uuidcPlayerHashMap = new HashMap<>();

    private CPlayer(Player player) {
        this.uuid = player.getUniqueId();
        uuidcPlayerHashMap.put(player.getUniqueId(), this);
    }

    public static CPlayer getInstance(Player player) {
        if (uuidcPlayerHashMap.containsKey(player.getUniqueId())) {
            return uuidcPlayerHashMap.get(player.getUniqueId());
        }
        return new CPlayer(player);
    }

    public void setChats(List<Chats> chats) {
        this.chats = chats;
    }

    public List<Chats> getChats() {
        return this.chats;
    }

    public void remove() {
        uuidcPlayerHashMap.remove(this.uuid);
        this.chats = null;
        this.uuid = null;
        this.test = null;
        this.ranks = null;
    }

    public void setMaxChat(Chats chat) {
        this.maxChat = chat;
    }

    public Chats getMaxChat() {
        return this.maxChat;
    }

    public void toggleTest() {
        this.test = !this.test;
    }

    public boolean getTest() {
        return this.test;
    }

    public void setRank(List<Ranks> rank) {
        this.ranks = rank;
    }

    public List<Ranks> getRank() {
        return this.ranks;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setMaxRank(Ranks maxRank) {
        this.maxRank = maxRank;
    }

    public Ranks getMaxRank() {
        return this.maxRank;
    }
}
