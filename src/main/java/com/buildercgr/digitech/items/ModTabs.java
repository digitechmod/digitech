package com.buildercgr.digitech.items;

import com.buildercgr.digitech.Digitech;
import com.buildercgr.digitech.blocks.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.Registries;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Digitech.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DIGITECH_TAB =
            CREATIVE_MODE_TAB.register("digitech_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.digitech"))
                            .icon(() -> new ItemStack(ModItems.PHONE.get()))
                            .displayItems((parameters, output) -> {
                                if (ModBlocks.COMPUTER_ITEM.isBound()) output.accept(ModBlocks.COMPUTER_ITEM.get());
                                if (ModItems.PHONE.isBound()) output.accept(ModItems.PHONE.get());
                                if (ModItems.DIGITECH_OS_DISK.isBound()) output.accept(ModItems.DIGITECH_OS_DISK.get());
                                if (ModBlocks.MICROCHIP_BLOCK_ITEM.isBound()) output.accept(ModBlocks.MICROCHIP_BLOCK_ITEM.get());
                                if (ModItems.MICROCHIP.isBound()) output.accept(ModItems.MICROCHIP.get());
                                if (ModBlocks.PLASTIC_BLOCK_ITEM.isBound()) output.accept(ModBlocks.PLASTIC_BLOCK_ITEM.get());
                                if (ModItems.PLASTIC.isBound()) output.accept(ModItems.PLASTIC.get());
                                if (ModBlocks.IT_WORKSPACE_ITEM.isBound()) output.accept(ModBlocks.IT_WORKSPACE_ITEM.get());
                                if (ModItems.RED_CASE.isBound()) output.accept(ModItems.RED_CASE.get());
                                if (ModItems.BLUE_CASE.isBound()) output.accept(ModItems.BLUE_CASE.get());
                                if (ModItems.GREEN_CASE.isBound()) output.accept(ModItems.GREEN_CASE.get());
                                if (ModItems.PINK_CASE.isBound()) output.accept(ModItems.PINK_CASE.get());
                                if (ModItems.YELLOW_CASE.isBound()) output.accept(ModItems.YELLOW_CASE.get());
                                if (ModItems.ORANGE_CASE.isBound()) output.accept(ModItems.ORANGE_CASE.get());
                                if (ModItems.CAMERA.isBound()) output.accept(ModItems.CAMERA.get());
                                if (ModItems.PENDRIVE.isBound()) output.accept(ModItems.PENDRIVE.get());
                            })
                            .build()
            );
}
