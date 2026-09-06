package com.buildercgr.digitech.dataComponents;

import com.buildercgr.digitech.Digitech;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Digitech.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> PHONECASE =
            register("phone_case",
                    builder -> builder
                            .persistent(Codec.INT)
                            .networkSynchronized(StreamCodec.of(
                                    RegistryFriendlyByteBuf::writeVarInt,
                                    RegistryFriendlyByteBuf::readVarInt
                            ))
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> DEVICE_ID =
            register("device_id",
                    builder -> builder
                            .persistent(Codec.STRING)
                            .networkSynchronized(StreamCodec.of(
                                    RegistryFriendlyByteBuf::writeUtf,
                                    RegistryFriendlyByteBuf::readUtf
                            ))
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> PHOTO_ID =
            register("photo_id",
                    builder-> builder
                            .persistent(Codec.INT)
                            .networkSynchronized(StreamCodec.of(
                                    RegistryFriendlyByteBuf::writeVarInt,
                                    RegistryFriendlyByteBuf::readVarInt
                            )));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> PASSWORD =
            register("password",
                    builder -> builder
                            .persistent(Codec.STRING)
                            .networkSynchronized(StreamCodec.of(
                                    RegistryFriendlyByteBuf::writeUtf,
                                    RegistryFriendlyByteBuf::readUtf
                            ))
            );

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(
            String name,
            UnaryOperator<DataComponentType.Builder<T>> builderOperator
    ) {
        return DATA_COMPONENT_TYPES.register(name,
                () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
