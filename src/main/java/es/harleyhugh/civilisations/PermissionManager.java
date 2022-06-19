package es.harleyhugh.civilisations;

import org.bukkit.entity.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class PermissionManager {



    private final Map<String, List<String>> permissionMap = Map.ofEntries(
            entry("civilisations.sudo", List.of("sudo", "civilisations:sudo"))
    );


//    public boolean canRun(@NotNull Command cmd, Player player) {
//        if (permissionMap.containsKey(cmd.getName())) {
//            return player.hasPermission(permissionMap.get(cmd.getName()));
//        }
//
//        return false;
//    }

    public List<String> getBlocked(Player player) {
        List<String> mappedPerms = new ArrayList<String>(permissionMap.keySet());
        mappedPerms.removeIf(player::hasPermission);

        List<String> blockedCommands = new ArrayList<>();


        for (String perm: mappedPerms) {
            blockedCommands.addAll(permissionMap.get(perm));
        }

//        for (PermissionAttachmentInfo permission: player.getEffectivePermissions()) {
//            if (mappedPerms.contains(permission.getPermission())) {
//                Bukkit.getLogger().info("NULL attachment");
//                continue;
//            }
//            if (permission.getAttachment().getPlugin().equals(plugin) && !permissionMap.containsKey(permission.getPermission())) {
//                Bukkit.getLogger().info("1");
//                blockedCommands.add(permission.getPermission());
//            } else {
//                Bukkit.getLogger().info("2");
//            }
//        }
        return blockedCommands;
    }

//    public @NotNull Set<PermissionAttachmentInfo> getCanRun(Player player) {
//        return player.getEffectivePermissions();
//    }




}
