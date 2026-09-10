package com.buildercgr.digitech.phone.apps;

import com.buildercgr.digitech.DigitechResourcepackGenerator;
import com.buildercgr.digitech.camera.GUI.CameraScreen;
import com.buildercgr.digitech.camera.GUI.GalleryScreen;
import com.buildercgr.digitech.dataComponents.ModDataComponents;
import com.buildercgr.digitech.items.custom.phone;
import com.buildercgr.digitech.phone.apps.rememberApp.RememberApp;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;

public class ModPhoneAppsClient {

    public static void initClient() {

        ModPhoneApps.REMEMBER.get().setAction((mc, player, phone) -> {
            Minecraft.getInstance().setScreen(new RememberApp());
        });

        ModPhoneApps.GALLERY.get().setAction((mc, player, phoneItem) -> {
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() instanceof phone) {
                String phoneID = stack.get(ModDataComponents.DEVICE_ID.get());
                DigitechResourcepackGenerator.INSTANCE.generateForCamera(phoneID);
                Minecraft.getInstance().reloadResourcePacks();
                Minecraft.getInstance().setScreen(new GalleryScreen(phoneID, 0));
            }
        });

        ModPhoneApps.CAMERA.get().setAction((mc, player, phoneItem) ->
                Minecraft.getInstance().setScreen(new CameraScreen(Component.literal("Photo camera")))
        );
    }
}

