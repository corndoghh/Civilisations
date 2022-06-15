package es.harleyhugh.civilisations.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Impersonate implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        String[] traits = traitsFromCommand(label);

        if (traits == null) {
            return true;
        }

        if (traits[0].equalsIgnoreCase("unknown")) {
            if (!(args.length <= 1)) {
                sender.sendMessage("No traits!");
                return true;
            }
            traits = traitsFromArgs(Arrays.copyOfRange(args, 1, args.length));
        }

        if (traits == null) {
            sender.sendMessage("No traits");
            return true;
        }

        for (String trait: traits) {
           Bukkit.getLogger().info(trait);
        }





        return false;
    }

    private String[] traitsFromCommand(String command) {
        if (command.isEmpty()) { return null; }

        List<String> traits = new ArrayList<String>();

        switch (command.toLowerCase()) {
            case "ima" -> { traits.addAll(List.of("skin", "voice", "command")); }
            case "ims" -> { traits.add("skin"); }
            case "imv" -> { traits.add("voice"); }
            case "imc" -> { traits.add("command"); }
            case "im", "impersonate" -> { traits.add("unknown"); }
            default -> { traits = null; }
        }

        if (traits == null) {
            return null;
        }

        return traits.toArray(new String[0]);

    }
    
    private String[] traitsFromArgs(String[] args) {
        if (args.length == 0) { return null; }

        List<String> traits = new ArrayList<String>();

        if (Arrays.stream(args).toList().contains("-a")) { traits.addAll(List.of("skin", "voice", "command")); }
        if (Arrays.stream(args).toList().contains("-s")) { traits.add("skin"); }
        if (Arrays.stream(args).toList().contains("-v")) { traits.add("voice"); }
        if (Arrays.stream(args).toList().contains("-c")) { traits.add("command"); }

        if (traits.isEmpty()) { return null; }

        return traits.toArray(new String[0]);

    }
}
