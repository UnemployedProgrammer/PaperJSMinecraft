package com.sebastian.paperJSMinecraft.commands;

import com.sebastian.paperJSMinecraft.CustomLog;
import com.sebastian.paperJSMinecraft.FileFinder;
import com.sebastian.paperJSMinecraft.JSExecute;
import com.sebastian.paperJSMinecraft.PaperJSMinecraft;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;

import java.util.Objects;

public class EventsReloadCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack stack, String[] args) {
        if (args.length == 0) {
            stack.getSender().sendMessage(Component.text("Usage: /reload_paperjs events/javascript/all").asComponent().color(TextColor.color(255, 78, 66)));
            return;
        }

        // Join all arguments to form the JS code
        String type = args[0];


        if (Objects.equals(type, "events")) {
            CustomLog.log("Triggered Reloading Events...");
            FileFinder.Events.reloadEvents(PaperJSMinecraft.dataFolder);
            stack.getSender().sendMessage(Component.text("Reloaded Events!").asComponent().color(TextColor.color(98, 255, 50)));
        } else if (Objects.equals(type, "javascript")) {
            CustomLog.log("Triggered Reloading JS...");
            FileFinder.reloadJS(PaperJSMinecraft.dataFolder);
            stack.getSender().sendMessage(Component.text("Reloaded Javascript!").asComponent().color(TextColor.color(98, 255, 50)));
        } else if (Objects.equals(type, "all")) {
            CustomLog.log("Triggered Reloading Events + JS...");
            FileFinder.Events.reloadEvents(PaperJSMinecraft.dataFolder);
            FileFinder.reloadJS(PaperJSMinecraft.dataFolder);
            stack.getSender().sendMessage(Component.text("Reloaded Javascript & Events!").asComponent().color(TextColor.color(98, 255, 50)));
        } else {
            stack.getSender().sendMessage(Component.text("Usage: /reload_paperjs events/javascript/all").asComponent().color(TextColor.color(255, 78, 66)));
        }
    }
}