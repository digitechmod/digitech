package com.buildercgr.digitech.worldgen;

import com.buildercgr.digitech.Digitech;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MICROCHIP_ORE_PLACED_KEY = registerKey("microchip_ore_placed");
    public static final ResourceKey<PlacedFeature> PLASTIC_ORE_PLACED_KEY = registerKey("plastic_ore_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, MICROCHIP_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MICROCHIP_ORE_KEY),
                ModOrePlacement.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));

        register(context, PLASTIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PLASTIC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(64, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(30))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Digitech.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}