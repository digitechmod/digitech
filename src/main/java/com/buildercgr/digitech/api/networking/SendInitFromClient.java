package com.buildercgr.digitech.api.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class SendInitFromClient {
    public record InitDevice (String id) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<InitDevice> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("digitech", "init_device"));

        public static final StreamCodec<ByteBuf, InitDevice> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8,
                InitDevice::id,
                InitDevice::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
