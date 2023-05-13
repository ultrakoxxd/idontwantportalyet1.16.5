package com.ultrakox.idontwantportalyet1165.commands;

import com.ultrakox.idontwantportalyet1165.config.commonConfig;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
public class endPortalOn {
    public endPortalOn(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermissionLevel(2)).then(Commands.literal("end").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    commonConfig.isEndPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    commonConfig.SPEC.save();
                    return isEnabled(command.getSource());
                }))
        )));
    }

    private int isEnabled(CommandSource source) throws CommandSyntaxException {
        if(Boolean.valueOf(String.valueOf(commonConfig.isEndPortalEnabled.get())) == true){
            return 1;
        }else{
            return 0;
        }
    }
}
