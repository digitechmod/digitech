package com.buildercgr.digitech.phone.apps;

import com.buildercgr.digitech.api.phone.apps.PhoneApps;
import com.buildercgr.digitech.api.phone.apps.PhoneAppsRegisterKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

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
                    Component.literal("Remember")
            ));

    public static final DeferredHolder<PhoneApps, PhoneApps> GALLERY =
            register("gallery_app", () -> new PhoneApps(
                    ResourceLocation.fromNamespaceAndPath("digitech", "gallery_phone_app"),
                    ResourceLocation.fromNamespaceAndPath("digitech", "textures/gui/gallery_app.png"),
                    Component.literal("Gallery")
            ));

    public static final DeferredHolder<PhoneApps, PhoneApps> CAMERA =
            register("camera", () -> new PhoneApps(
                    ResourceLocation.fromNamespaceAndPath("digitech", "phone_camera"),
                    ResourceLocation.fromNamespaceAndPath("digitech", "textures/gui/camera_app.png"),
                    Component.literal("Camera")
            ));

    private static DeferredHolder<PhoneApps, PhoneApps> register(String name, Supplier<PhoneApps> sup) {
        var holder = APPS.register(name, sup);
        REGISTERED_APPS.add(holder);
        return holder;
    }
}
