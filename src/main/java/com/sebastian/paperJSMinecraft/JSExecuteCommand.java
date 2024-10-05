package com.sebastian.paperJSMinecraft;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;

public class JSExecuteCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack stack, String[] args) {
        if (args.length == 0) {
            stack.getSender().sendMessage("Please provide a JavaScript code snippet.");
        }

        // Join all arguments to form the JS code
        String jsCode = String.join(" ", args);
        String jsResponse = new JSExecute().execute(jsCode);

        if(jsResponse == "undefined") {
            stack.getExecutor().sendMessage(Component.text(jsResponse + "").asComponent().color(TextColor.color(111, 255, 95)));
        } else {
            stack.getExecutor().sendMessage("Error:");
            stack.getExecutor().sendMessage(Component.text(jsResponse).asComponent().clickEvent(ClickEvent.clickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, jsResponse)).color(TextColor.color(255, 77, 77)));
            stack.getExecutor().sendMessage("(Click to copy)");
            CustomLog.log("Showed error to user." + stack.getExecutor().getName());
        }
    }
}
