package com.buildercgr.digitech.blocks;

import com.buildercgr.digitech.Digitech;
import com.buildercgr.digitech.blocks.custom.Computer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.minecraft.world.level.block.SoundType.METAL;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Digitech.MODID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Digitech.MODID);


    // Microchip Block
    public static final DeferredBlock<Block> MICROCHIP_BLOCK =
            BLOCKS.registerSimpleBlock("microchip_block",
                    BlockBehaviour.Properties.of()
                            .strength(1.0f, 10.0f)
                            .sound(METAL)
                            .lightLevel(state -> 5)
            );

    public static final DeferredItem<BlockItem> MICROCHIP_BLOCK_ITEM =
            ITEMS.registerSimpleBlockItem("microchip_block", MICROCHIP_BLOCK);

    // Plastic Block
    public static final DeferredBlock<Block> PLASTIC_BLOCK =
            BLOCKS.registerSimpleBlock("plastic_block",
                    BlockBehaviour.Properties.of()
                            .strength(1.0f, 10.0f)
                            .sound(METAL)
                            .lightLevel(state -> 5)
            );

    public static final DeferredItem<BlockItem> PLASTIC_BLOCK_ITEM =
            ITEMS.registerSimpleBlockItem("plastic_block", PLASTIC_BLOCK);

    // Microchip Ore Block
    public static final DeferredBlock<Block> MICROCHIP_ORE =
            BLOCKS.registerSimpleBlock("microchip_ore",
                    BlockBehaviour.Properties.of()
                            .strength(0.5f, 5.3961517265f)
                            .requiresCorrectToolForDrops()
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .lightLevel(state -> 7)
            );

    public static final DeferredItem<BlockItem> MICROCHIP_ORE_ITEM =
            ITEMS.registerSimpleBlockItem("microchip_ore", MICROCHIP_ORE);

    // Plastic Ore Block
    public static final DeferredBlock<Block> PLASTIC_ORE =
            BLOCKS.registerSimpleBlock("plastic_ore",
                    BlockBehaviour.Properties.of()
                            .strength(0.5f, 5.3961517265f)
                            .requiresCorrectToolForDrops()
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .lightLevel(state -> 7)
            );

    public static final DeferredItem<BlockItem> PLASTIC_ORE_ITEM =
            ITEMS.registerSimpleBlockItem("plastic_ore", PLASTIC_ORE);

    public static final DeferredBlock<Block> COMPUTER = BLOCKS.register("computer",
            () -> new Computer(BlockBehaviour.Properties.of()
                    .strength(0.5f)
                    .noOcclusion()));

    public static final DeferredItem<BlockItem> COMPUTER_ITEM =
            ITEMS.registerSimpleBlockItem("computer", COMPUTER);

    public static final DeferredBlock<Block> IT_WORKSPACE =
            BLOCKS.registerSimpleBlock("it_workspace",
                    BlockBehaviour.Properties.of()
    );

    public static final DeferredItem<BlockItem> IT_WORKSPACE_ITEM =
            ITEMS.registerSimpleBlockItem("it_workspace", IT_WORKSPACE);
}

