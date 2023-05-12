package com.ultrakox.idontwantportalyet1165.events;

import com.ultrakox.idontwantportalyet1165.commands.endPortalOn;
import com.ultrakox.idontwantportalyet1165.config.commonConfig;

import com.ultrakox.idontwantportalyet1165.Idontwantportalyet1165;
import com.ultrakox.idontwantportalyet1165.commands.portalOn;
import net.minecraft.block.Blocks;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@Mod.EventBusSubscriber(modid = Idontwantportalyet1165.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class modEvents {
    @SubscribeEvent
    public static void deletePortal(BlockEvent.PortalSpawnEvent event) {
        if (!commonConfig.isPortalEnabled.get()) {
            event.setCanceled(true);
            System.out.println("Is portal enabled? " + commonConfig.isPortalEnabled.get());
        } else {
            event.setCanceled(false);
            System.out.println("Is portal enabled? " + commonConfig.isPortalEnabled.get());
        }
    }

    @SubscribeEvent
    public static void deleteEndPortal(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        BlockState blockState = world.getBlockState(pos);

        if (!commonConfig.isEndPortalEnabled.get()) {
            if (blockState.getBlock() == Blocks.END_PORTAL_FRAME) {
                Item heldItem = event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem();
                if (heldItem == Items.ENDER_EYE) {
                    event.setCanceled(true);
                }
            }
        } else {
            event.setCanceled(false);
        }
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new portalOn(event.getDispatcher());
        new endPortalOn(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}