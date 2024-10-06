package com.sebastian.paperJSMinecraft;

import com.sebastian.paperJSMinecraft.commands.EventsReloadCommand;
import com.sebastian.paperJSMinecraft.commands.JSExecuteCommand;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class PaperJSMinecraft extends JavaPlugin {

    public static Logger logger;
    public static File dataFolder;

    @Override
    public void onEnable() {

        logger = getLogger();

        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("javascript_run", "Run some Javascript!", new JSExecuteCommand());
            commands.register("reload_paperjs", "Reload Files!", new EventsReloadCommand());
        });
        dataFolder = getDataFolder();
        FileFinder.Events.reloadEvents(getDataFolder());
        FileFinder.reloadJS(getDataFolder());
    }



    @Override
    public void onDisable() {
        getLogger().info("PaperJS is shutting down.");
    }
}
