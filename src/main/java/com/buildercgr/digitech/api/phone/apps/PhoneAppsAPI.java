package com.buildercgr.digitech.api.phone.apps;

import net.minecraft.client.Minecraft;
import java.util.List;

public class PhoneAppsAPI {

    public static List<PhoneApps> getInstalledApps() {
        return PhoneAppsDeferredRegister.PHONE_APPS
                .stream()
                .toList();
    }
}