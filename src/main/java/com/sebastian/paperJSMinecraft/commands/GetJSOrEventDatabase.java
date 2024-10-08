package com.sebastian.paperJSMinecraft.commands;

import com.sebastian.paperJSMinecraft.CustomLog;
import com.sebastian.paperJSMinecraft.FileFinder;
import com.sebastian.paperJSMinecraft.JSExecute;
import com.sebastian.paperJSMinecraft.PaperJSMinecraft;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import java.util.Objects;

public class GetJSOrEventDatabase implements BasicCommand {

    @Override
    public void execute(CommandSourceStack stack, String[] args) {
        //if (args.length == 0) {
        //            stack.getSender().sendMessage(Component.text("Usage: /getconfig_files_content_database").asComponent().color(TextColor.color(255, 78, 66)));
        //            return;
        //        }

        // Join all arguments to form the JS code
        //String type = args[0];

        stack.getSender().sendMessage("-+-------------------------+-");
        stack.getSender().sendMessage(Component.text("All Events from Database: ").color(TextColor.color(181, 255, 135)));
        stack.getSender().sendMessage(" ");
        FileFinder.allEvents.forEach((key, value) -> {
            stack.getSender().sendMessage(key + " corresponds to " + value);
        });
        stack.getSender().sendMessage(" ");
        stack.getSender().sendMessage(Component.text("All Javascript Files from Database: ").color(TextColor.color(165, 229, 255)));
        stack.getSender().sendMessage(" ");
        FileFinder.allJSFiles.forEach((key, value) -> {
            stack.getSender().sendMessage(key + " corresponds to " + value);
        });
        stack.getSender().sendMessage(" ");
        stack.getSender().sendMessage("-+-------------------------+-");

        CustomLog.log(FileFinder.allJSFiles.toString());
        CustomLog.log(FileFinder.allEvents.toString());
    }
}