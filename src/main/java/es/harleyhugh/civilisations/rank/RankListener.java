package es.harleyhugh.civilisations.rank;

import es.harleyhugh.civilisations.Civilisations;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static es.harleyhugh.civilisations.CPlayer.getInstance;

public class RankListener implements Listener {

    private final Civilisations plugin;

    public RankListener(Civilisations plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent p) {
        plugin.getRankManager().loadRanks(getInstance(p.getPlayer()));
    }

}
