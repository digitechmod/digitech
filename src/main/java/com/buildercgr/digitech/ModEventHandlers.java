package com.buildercgr.digitech;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

import static com.buildercgr.digitech.api.phone.apps.PhoneAppsDeferredRegister.PHONE_APPS;

public class ModEventHandlers {
    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(PHONE_APPS);
    }

}
