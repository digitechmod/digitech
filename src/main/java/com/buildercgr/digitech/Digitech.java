package com.buildercgr.digitech;

import com.buildercgr.digitech.api.networking.ApiPayloadHandler;
import com.buildercgr.digitech.api.networking.SendInitFromClient;
import com.buildercgr.digitech.blocks.ModBlocks;
import com.buildercgr.digitech.phone.apps.ModPhoneApps;
import com.buildercgr.digitech.villager.ModVillagerTrades;
import com.buildercgr.digitech.items.ModItems;
import com.buildercgr.digitech.items.ModTabs;
import com.buildercgr.digitech.sound.ModSounds;
import com.buildercgr.digitech.villager.ModVillagers;
import com.mojang.logging.LogUtils;
import com.buildercgr.digitech.dataComponents.ModDataComponents;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handlers.ClientPayloadHandler;
import net.neoforged.neoforge.network.handlers.ServerPayloadHandler;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.resource.ResourcePackLoader;
import org.slf4j.Logger;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;


@Mod(Digitech.MODID)
public class Digitech {
    public static final String MODID = "digitech";
    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                SendInitFromClient.InitDevice.TYPE,
                SendInitFromClient.InitDevice.STREAM_CODEC,
                ApiPayloadHandler::handleDataOnMain
        );
    }


    public Digitech(IEventBus modEventBus) {
        modEventBus.register(Digitech.class);
        modEventBus.register(ModEventHandlers.class);
        ModSounds.register(modEventBus);
        ModPhoneApps.APPS.register(modEventBus);
        ModDataComponents.DATA_COMPONENT_TYPES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTabs.CREATIVE_MODE_TAB.register(modEventBus);
        ModVillagers.register(modEventBus);
        NeoForge.EVENT_BUS.register(ModVillagerTrades.class);
        modEventBus.addListener(this::addCreative);
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.MICROCHIP_ORE_ITEM);
            event.accept(ModBlocks.PLASTIC_ORE_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.MICROCHIP);
            event.accept(ModBlocks.MICROCHIP_BLOCK_ITEM);
            event.accept(ModItems.PLASTIC);
            event.accept(ModBlocks.PLASTIC_BLOCK_ITEM);
        }
    }

}
