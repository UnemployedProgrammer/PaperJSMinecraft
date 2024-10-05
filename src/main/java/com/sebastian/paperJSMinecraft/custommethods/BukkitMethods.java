package com.sebastian.paperJSMinecraft.custommethods;

import com.sebastian.paperJSMinecraft.CustomLog;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public class BukkitMethods {

    public static Collection<? extends Player> getAllPlayers() {
        CustomLog.log("Requested all players!");
        return Bukkit.getOnlinePlayers();
    }

    public static Player getPlayer(String username_or_uuid, boolean isUUID) {

        if(!isUUID) {
            CustomLog.log("Requested player by USERNAME! " + username_or_uuid);
            return Bukkit.getPlayer(username_or_uuid);
        } else {
            CustomLog.log("Requested player by UUID! " + username_or_uuid);
            return Bukkit.getPlayer(UUID.fromString(username_or_uuid));
        }

    }

}
