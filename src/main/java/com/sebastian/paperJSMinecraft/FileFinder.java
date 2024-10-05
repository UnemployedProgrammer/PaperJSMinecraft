package com.sebastian.paperJSMinecraft;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import com.destroystokyo.paper.event.entity.EntityJumpEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileFinder {

    public static Map<String, String> allEvents = new HashMap<>();
    //EventName, JS File

    public class Events {

        public static void reloadEvents(File dataFolder) {
            CustomLog.log("Reloading Events...");
            File eventsFile = new File(dataFolder, "events.yml");
            allEvents = parseEventsYamlFile(eventsFile);
        }

        public static void eventFromString() {
            HashMap<String, Class> event = new HashMap<>();
            //Tick
            event.put("tickEventStart", ServerTickStartEvent.class);
            event.put("tickEventEnd", ServerTickEndEvent.class);
            //Player
            event.put("playerMove", PlayerMoveEvent.class);
            event.put("playerVelocity", PlayerVelocityEvent.class);
            event.put("playerJump", PlayerJumpEvent.class);
            event.put("playerDeath", PlayerDeathEvent.class);
            event.put("playerChat", PlayerChatEvent.class);
            event.put("playerLogin", PlayerJoinEvent.class);
            event.put("playerLeave", PlayerQuitEvent.class);
            event.put("playerDrop", PlayerDropItemEvent.class);
            event.put("playerPickupItem", PlayerPickupItemEvent.class);
            event.put("playerBlockInteract", PlayerInteractEvent.class);
            event.put("playerEntityInteract", PlayerInteractEntityEvent.class);
            //Blocks
            event.put("blockPlace", BlockPlaceEvent.class);
            event.put("blockDestroy", BlockDestroyEvent.class);
            //Entities
            event.put("entityMove", EntityMoveEvent.class);
            event.put("entityJump", EntityJumpEvent.class);
            event.put("entityDeath", EntityDeathEvent.class);
        }

        public static Map<String,String> parseEventsYamlFile(File yamlFile) {
            if (!yamlFile.exists()) {
                CustomLog.log("YAML file does not exist: " + yamlFile.getPath());
                return new HashMap<String,String>();
            }

            try (InputStream inputStream = new FileInputStream(yamlFile)) {
                // Initialize SnakeYAML
                Yaml yaml = new Yaml();

                // Parse the YAML file into a List of Maps
                List<Map<String, String>> yamlList = yaml.load(inputStream);

                // Iterate over the list and process each entry
                Map<String,String> events_save = new HashMap<String, String>();
                for (Map<String, String> map : yamlList) {
                    for (String key : map.keySet()) {
                        String value = map.get(key);
                        CustomLog.log("Event: " + key + ", Filename: " + value);
                        events_save.put(key,value);
                    }
                }
            } catch (Exception e) {
                CustomLog.log("Error reading YAML file: " + e.getMessage());
                e.printStackTrace();
            }
            return new HashMap<String,String>();
        }
    }
}
