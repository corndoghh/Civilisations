package es.harleyhugh.civilisations;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static es.harleyhugh.civilisations.CPlayer.getInstance;

public class Test implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof  Player)) {
            return true;
        }

        getInstance((Player) sender).toggleTest();

        return true;
    }


}
