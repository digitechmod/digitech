package com.buildercgr.digitech.api.networking;

import com.buildercgr.digitech.phone.os.core.events.init.DeviceStorageInit;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ApiPayloadHandler {
    public static void handleDataOnMain(final SendInitFromClient.InitDevice data, final IPayloadContext context) {
        context.enqueueWork(()-> {
            DeviceStorageInit.INSTANCE.CreateDir(data.id());
                }
        );
    }
}
