package com.savagevegetables.outbreak.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import com.savagevegetables.outbreak.world.OutbreakSavedData;
import net.minecraft.server.level.ServerLevel;

public class ModCommands {

    public static double overrideMimicChance = -1.0;

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("setStage")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("stage", IntegerArgumentType.integer(0, 7))
                        .executes(context -> {
                            int stage = IntegerArgumentType.getInteger(context, "stage");
                            ServerLevel level = context.getSource().getLevel();
                            OutbreakSavedData data = OutbreakSavedData.get(level);
                            data.setStage(stage);
                            context.getSource().sendSuccess(() -> Component.literal("Set Outbreak Stage to " + stage), true);
                            return 1;
                        })));

        dispatcher.register(Commands.literal("setMimicChance")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("chance", DoubleArgumentType.doubleArg(-1.0, 1.0))
                        .executes(context -> {
                            double chance = DoubleArgumentType.getDouble(context, "chance");
                            overrideMimicChance = chance;
                            context.getSource().sendSuccess(() -> Component.literal("Override Mimic Chance set to " + chance + " (-1.0 to disable override)"), true);
                            return 1;
                        })));
    }
}
