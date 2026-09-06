package com.buildercgr.digitech.api.phone.apps;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class PhoneAppsRegisterKey {
    public static final ResourceKey<Registry<PhoneApps>> PHONE_APPS_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("digitech", "phone_apps"));
}

