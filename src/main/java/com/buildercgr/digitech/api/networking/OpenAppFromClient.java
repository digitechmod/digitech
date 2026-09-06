package com.buildercgr.digitech.api.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class OpenAppFromClient {
    public record Open_APP(String id) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<Open_APP> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("digitech", "init_device"));

        public static final StreamCodec<ByteBuf, Open_APP> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8,
                Open_APP::id,
                Open_APP::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
