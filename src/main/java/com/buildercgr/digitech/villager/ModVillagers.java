package com.buildercgr.digitech.villager;

import com.buildercgr.digitech.blocks.ModBlocks;
import com.buildercgr.digitech.sound.ModSounds;
import com.google.common.collect.ImmutableSet;
import com.buildercgr.digitech.Digitech;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, Digitech.MODID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, Digitech.MODID);

    public static final Holder<PoiType> DIGITECH_POI = POI_TYPES.register("digitech_poi",
            ()  -> new PoiType(ImmutableSet.copyOf(ModBlocks.IT_WORKSPACE.get().getStateDefinition().getPossibleStates()),1,1)
    );

    public static final Holder<VillagerProfession> IT_TECHNICIAN = VILLAGER_PROFESSIONS.register("it_technician",
            () -> new VillagerProfession("it_technician", holder -> holder.value() == DIGITECH_POI.value(),
                poiTypeHolder -> poiTypeHolder.value() == DIGITECH_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    ModSounds.PC_USE.get()));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
