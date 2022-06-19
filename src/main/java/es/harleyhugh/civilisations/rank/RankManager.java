package es.harleyhugh.civilisations.rank;

import es.harleyhugh.civilisations.CPlayer;
import es.harleyhugh.civilisations.Civilisations;
import es.harleyhugh.civilisations.chat.Chats;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static es.harleyhugh.civilisations.CPlayer.getInstance;

public class RankManager {

    private final File ranks;
    private final YamlConfiguration ranksYml;

    public RankManager(Civilisations plugin) {
        ranks = new File(plugin.getDataFolder(),"ranks.yml");

        if (!ranks.exists()) {
            try {
                ranks.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ranksYml = YamlConfiguration.loadConfiguration(ranks);
    }

    public void setRank(CPlayer player, List<Ranks> ranks) {

        ranksYml.set(player.getUuid().toString(), ranks.stream().map(Enum::name).collect(Collectors.toList()));

        try {
            ranksYml.save(this.ranks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Ranks maxRank = Ranks.DEFAULT;

        for (Ranks rank: ranks) {
            if (maxRank.getWeight() < rank.getWeight()) {
                maxRank = rank;
            }
        }

        player.setRank(ranks);
        player.setMaxRank(maxRank);

    }

    public void removeRanks(Player player, List<Ranks> ranks) {
        List<Ranks> currentRanks = getInstance(player).getRank();

        if (currentRanks.isEmpty()) {
            return;
        }

        currentRanks.removeAll(ranks);

        if (currentRanks.isEmpty()) {
            currentRanks = Collections.singletonList(Ranks.DEFAULT);
        }

        getInstance(player).setRank(currentRanks);

        ranksYml.set(player.getUniqueId().toString(), currentRanks);

        try {
            ranksYml.save(this.ranks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void loadRanks(CPlayer player) {

        List<Ranks> ranksList;

        if (ranksYml.contains(player.getUuid().toString())) {
            ranksList = ranksYml.getStringList(player.getUuid().toString()).stream().map(Ranks::valueOf).collect(Collectors.toList());
        } else {
            ranksList = Collections.singletonList(Ranks.DEFAULT);
        }

        setRank(player, ranksList);

    }


}
