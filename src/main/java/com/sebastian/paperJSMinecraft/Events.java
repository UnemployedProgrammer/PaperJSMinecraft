package com.sebastian.paperJSMinecraft;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class Events implements Listener {

    //Tick

    @EventHandler
    public void serverTickStart(ServerTickStartEvent event) {
        // ...
    }

    @EventHandler
    public void serverTickEnd(ServerTickEndEvent event) {

    }

    //Player

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerVelocityEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerJumpEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerDeathEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerChatEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerLoginEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerQuitEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerDropItemEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerPickItemEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerInteractEvent event) {

    }

    @EventHandler
    public void onPlayerMove(PlayerInteractEntityEvent event) {

    }

    //Blocks

    @EventHandler
    public void onPlayerMove(BlockPlaceEvent event) {

    }

    @EventHandler
    public void onPlayerMove(BlockDestroyEvent event) {

    }

}
