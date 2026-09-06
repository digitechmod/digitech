package com.buildercgr.digitech.phone.apps;

import com.buildercgr.digitech.DigitechResourcepackGenerator;
import com.buildercgr.digitech.api.phone.apps.PhoneApps;
import com.buildercgr.digitech.api.phone.apps.PhoneAppsRegisterKey;
import com.buildercgr.digitech.camera.GUI.CameraScreen;
import com.buildercgr.digitech.camera.GUI.GalleryScreen;
import com.buildercgr.digitech.dataComponents.ModDataComponents;
import com.buildercgr.digitech.items.custom.phone;
import com.buildercgr.digitech.phone.apps.rememberApp.RememberApp;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModPhoneApps {

    public static final DeferredRegister<PhoneApps> APPS =
            DeferredRegister.create(PhoneAppsRegisterKey.PHONE_APPS_REGISTRY_KEY, "digitech");

    public static final List<DeferredHolder<PhoneApps, PhoneApps>> REGISTERED_APPS = new ArrayList<>();

    public static final DeferredHolder<PhoneApps, PhoneApps> REMEMBER =
            register("remember_app", () -> new PhoneApps(
                    ResourceLocation.fromNamespaceAndPath("digitech", "remember_app"),
                    ResourceLocation.fromNamespaceAndPath("digitech", "textures/gui/remember_app.png"),
                    Component.literal("Remember"),

                    (mc, player, phone) -> {
                        Minecraft.getInstance().setScreen(new RememberApp());
                    }
            ));

    public static final DeferredHolder<PhoneApps, PhoneApps> GALLERY =
            register("gallery_app", () -> new PhoneApps(
                    ResourceLocation.fromNamespaceAndPath("digitech", "gallery_phone_app"),
                    ResourceLocation.fromNamespaceAndPath("digitech", "textures/gui/gallery_app.png"),
                    Component.literal("Gallery"),
                    (mc, player, phone) -> {
                        ItemStack stack = player.getMainHandItem();
                        if (stack.getItem() instanceof phone) {
                            String phoneID = stack.get(ModDataComponents.DEVICE_ID.get());
                            DigitechResourcepackGenerator.INSTANCE.generateForCamera(phoneID);
                            Minecraft.getInstance().reloadResourcePacks();
                            Minecraft.getInstance().setScreen(new GalleryScreen(phoneID, 0));
                        }
                    }
            ));

    public static final DeferredHolder<PhoneApps, PhoneApps> CAMERA =
            register("camera", ()-> new PhoneApps(
                    ResourceLocation.fromNamespaceAndPath("digitech", "phone_camera"),
                    ResourceLocation.fromNamespaceAndPath("digitech", "textures/gui/camera_app.png"),
                    Component.literal("Camera"),
                    (mc, player, phone) ->
                            Minecraft.getInstance().setScreen(new CameraScreen(Component.literal("Photo camera")))
            ));

    private static DeferredHolder<PhoneApps, PhoneApps> register(String name, Supplier<PhoneApps> sup) {
        var holder = APPS.register(name, sup);
        REGISTERED_APPS.add(holder);
        return holder;

    }
}

