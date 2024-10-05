package com.sebastian.paperJSMinecraft;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.logging.Logger;

public final class PaperJSMinecraft extends JavaPlugin {

    public static Logger logger;

    @Override
    public void onEnable() {

        logger = getLogger();

        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("javascript_run", "Run some Javascript!", new JSExecuteCommand());
        });

        FileFinder.Events.reloadEvents(getDataFolder());
    }



    @Override
    public void onDisable() {
        getLogger().info("PaperJS is shutting down.");
    }
}
