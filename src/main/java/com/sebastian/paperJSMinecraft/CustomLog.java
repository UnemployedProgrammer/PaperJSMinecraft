package com.sebastian.paperJSMinecraft;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CustomLog {

    public static void log(String msg) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if(onlinePlayer.getScoreboardTags().contains("paperjs_logs")) {
                onlinePlayer.sendMessage("Log:" + msg);
            }
        }

        PaperJSMinecraft.logger.info(msg);
    }

}
