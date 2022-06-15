package es.harleyhugh.civilisations.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SudoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("civilisations.sudo")) {
            sender.sendMessage(ChatColor.RED + "No Permissions For This Command!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("More Arguments Required");
            return true;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage("Player not found");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        StringBuilder commandBuffer = new StringBuilder();
        String[] argsC = Arrays.copyOfRange(args, 1, args.length);
        for (String arg: argsC) {
            commandBuffer.append(arg).append(" ");
        }
        String commandTarget = commandBuffer.toString();

        assert target != null;
        Bukkit.getLogger().info(commandTarget);
        target.performCommand(commandTarget);

        return true;
    }
}
