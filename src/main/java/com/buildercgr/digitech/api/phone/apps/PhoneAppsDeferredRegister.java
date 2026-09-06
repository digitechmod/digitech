package com.buildercgr.digitech.api.phone.apps;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

import static com.buildercgr.digitech.api.phone.apps.PhoneAppsRegisterKey.PHONE_APPS_REGISTRY_KEY;

public class PhoneAppsDeferredRegister {

    public static final Registry<PhoneApps> PHONE_APPS = new RegistryBuilder<>(PHONE_APPS_REGISTRY_KEY)
            .sync(true)
            .defaultKey(ResourceLocation.fromNamespaceAndPath("digitech", "phone_apps"))
            .maxId(256)
            .create();
}

