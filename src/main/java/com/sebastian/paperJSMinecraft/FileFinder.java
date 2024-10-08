package com.sebastian.paperJSMinecraft;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import com.destroystokyo.paper.event.entity.EntityJumpEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import io.papermc.paper.event.entity.EntityMoveEvent;
import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileFinder {

    public static HashMap<String, String> allEvents = new HashMap<>();
    //EventName, JS File
    public static HashMap<String, String> allJSFiles = new HashMap<>();
    //Filename, Content

    public static void reloadJS(File dataFolder) {
        allJSFiles.clear();
        File scriptsDir = new File(dataFolder, "scripts");
        scriptsDir.mkdir();
        FilenameFilter jsFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".js") && !name.startsWith("#");
            }
        };
        // Get all files matching the filter
        File[] jsFiles = scriptsDir.listFiles(jsFilter);
        Thread fileReaderThread = new Thread(() -> {
            if (jsFiles != null) {
                for (File file : jsFiles) {
                    CustomLog.log("Reading file: " + file.getName());
                    try {
                        // Read all lines from the file
                        List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));

                        // Join the lines into a single string
                        String content = String.join("\n", lines);

                        // Store the filename and content in the HashMap
                        allJSFiles.put(file.getName(), content);
                        CustomLog.log("Read file: " + file.getName());
                        CustomLog.log("Content: " + content);
                    } catch (IOException e) {
                        CustomLog.log("Error reading file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            } else {
                CustomLog.log("No JavaScript files found or the folder doesn't exist.");
            }
        });

        fileReaderThread.start();

        // Wait for the thread to finish (optional)
        try {
            fileReaderThread.join();
        } catch (InterruptedException e) {
            CustomLog.log(e.getMessage());
        }
    }

    public class Events {

        public static void reloadEvents(File dataFolder) {
            allEvents.clear();
            CustomLog.log("Reloading Events...");
            dataFolder.mkdir();
            File eventsFile = new File(dataFolder, "events.yml");
            allEvents = parseEventsYamlFile(eventsFile);
        }

        public static String eventFromString(Class<?> clazz) {
            HashMap<String, Class<?>> event = new HashMap<>();
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

            // Iterate over the entries of the map
            for (Map.Entry<String, Class<?>> entry : event.entrySet()) {
                //CustomLog.log("Entry!");
                //CustomLog.log(entry.getValue().getName() + " == " + clazz.getName());
                if (entry.getValue().getSimpleName().equals(clazz.getSimpleName())) {
                    //CustomLog.log("");
                    boolean log = true;  // Default to true, TEMP-DISABLED!

                    // Set log to false for specific events that should not be logged
                    if (entry.getKey().equals("tickEventStart") ||
                            entry.getKey().equals("tickEventEnd") ||
                            entry.getKey().equals("entityMove") ||
                            entry.getKey().equals("playerMove") ||
                            entry.getKey().equals("playerVelocity") ||
                            entry.getKey().equals("entityJump") ||
                            entry.getKey().equals("playerJump")) {
                        log = false;
                    }
                    if(log) {
                        CustomLog.log(entry.getKey() + " requested Key for Event-Trigger!");
                    }
                    return entry.getKey();  // Return the matching string key
                }
            }

            return "noClassFoundErr";
        }

        public static HashMap<String,String> parseEventsYamlFile(File yamlFile) {
            if (!yamlFile.exists()) {
                CustomLog.log("YAML file does not exist, creating: " + yamlFile.getPath());

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(yamlFile))) {
                    writer.write("#example\n"); // Write content to the file
                    writer.write("# -playerLogin: playerlogin.js\n"); // Write content to the file
                    writer.write("# NO Subfolders Supported!!!!"); // Write content to the file
                    writer.write("# Remove all comments, not supported!!!!"); // Write content to the file
                    writer.write("# One file per event!"); // Write content to the file
                    CustomLog.log("File created successfully!");
                } catch (IOException e) {
                    CustomLog.log("An error occurred: " + e.getMessage());
                }

                return new HashMap<String,String>();
            }

            HashMap<String, String> events_save = new HashMap<>();

            try (InputStream inputStream = new FileInputStream(yamlFile)) {
                // Initialize SnakeYAML
                Yaml yaml = new Yaml();

                // Load the YAML file (this could be either a Map or a List)
                Object loadedYaml = yaml.load(inputStream);

                if (loadedYaml instanceof List) {
                    // Case when YAML root is a List of Maps
                    List<Map<String, String>> yamlList = (List<Map<String, String>>) loadedYaml;
                    for (Map<String, String> map : yamlList) {
                        for (String key : map.keySet()) {
                            String value = map.get(key);
                            CustomLog.log("Event: " + key + ", Filename: " + value);
                            events_save.put(key.replace("-",""), value);
                        }
                    }
                } else if (loadedYaml instanceof Map) {
                    // Case when YAML root is a Map
                    Map<String, String> yamlMap = (Map<String, String>) loadedYaml;
                    for (String key : yamlMap.keySet()) {
                        String value = yamlMap.get(key);
                        CustomLog.log("Event: " + key + ", Filename: " + value);
                        events_save.put(key.replace("-",""), value);
                    }
                } else {
                    CustomLog.log("Unexpected YAML structure");
                }
            } catch (Exception e) {
                CustomLog.log("Error reading YAML file: " + e.getMessage());
                e.printStackTrace();
            }

            return events_save;
        }
    }
}
