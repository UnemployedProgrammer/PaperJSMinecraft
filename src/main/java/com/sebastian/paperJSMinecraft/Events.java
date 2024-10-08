package com.sebastian.paperJSMinecraft;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import com.destroystokyo.paper.event.entity.EntityJumpEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import io.papermc.paper.event.entity.EntityMoveEvent;
import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Objects;

public class Events implements Listener {

    //Tick

    @EventHandler
    public void serverTickStart(ServerTickStartEvent event) {
        //..
    }

    @EventHandler
    public void serverTickEnd(ServerTickEndEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                ////CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    //Player

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                //CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                //CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        CustomLog.log("Event-String: " + eventString);
        FileFinder.allJSFiles.forEach((key, value) -> {
            CustomLog.log(FileFinder.allEvents.get(eventString));
            if(Objects.equals(FileFinder.allEvents.get(eventString), key)) {
                CustomLog.log("Executing...");
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onPlayerEntityInteract(PlayerInteractEntityEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    //Blocks

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                //CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onBlockDestroy(BlockDestroyEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                //CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    //Entities

    @EventHandler
    public void onEntityMove(EntityMoveEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                //CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onEntityJump(EntityJumpEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                //CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        String eventString = FileFinder.Events.eventFromString(event.getClass());
        FileFinder.allJSFiles.forEach((key, value) -> {
            if(Objects.equals(eventString, key)) {
                String result = new JSExecute().executeEvent(value, event);
                CustomLog.log(event.getEventName() + " fired, result: " + result);
            }
        });
    }

}
