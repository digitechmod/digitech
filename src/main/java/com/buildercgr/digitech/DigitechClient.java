package com.buildercgr.digitech;

import com.buildercgr.digitech.camera.event.CapturePhotoEvent;
import com.buildercgr.digitech.items.ModItems;
import com.buildercgr.digitech.dataComponents.ModDataComponents;
import com.buildercgr.digitech.phone.apps.ModPhoneApps;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Mod(value = Digitech.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Digitech.MODID, value = Dist.CLIENT)
public class DigitechClient {
    public DigitechClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
        public static void init_cases () {
            ItemProperties.register(ModItems.PHONE.get(),
                    ResourceLocation.fromNamespaceAndPath("digitech", "phone_case"),
                    (stack, level, entity, seed) -> {
                        Integer value = stack.get(ModDataComponents.PHONECASE.get());
                        return value == null ? 0.0F : (float) value;
                    });
        }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(DigitechClient::init_cases);
        try {
            Path dir = FMLPaths.CONFIGDIR.get()
                    .resolve("digitech")
                    .resolve("photos");

            Files.createDirectories(dir);

        } catch (IOException e) {
            Digitech.LOGGER.error("Error creating photo folder: ", e);
        }
        try {
            Path dir = FMLPaths.CONFIGDIR.get()
                    .resolve("digitech")
                    .resolve("devices");

            Files.createDirectories(dir);

        } catch (IOException e) {
            Digitech.LOGGER.error("Error creating device folder: ", e);
        }
        DigitechResourcepackGenerator.INSTANCE.generate();

        NeoForge.EVENT_BUS.addListener(
                CapturePhotoEvent.INSTANCE::onClientTick
        );
    }
}


