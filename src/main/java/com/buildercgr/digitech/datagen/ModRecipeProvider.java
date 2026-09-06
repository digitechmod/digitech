package com.buildercgr.digitech.datagen;

import com.buildercgr.digitech.blocks.ModBlocks;
import com.buildercgr.digitech.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> p_323846_) {
        super(p_248933_, p_323846_);
    }

    @Override
    protected void buildRecipes(RecipeOutput p_301172_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MICROCHIP_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MICROCHIP.get())
                .unlockedBy("has_microchip", has(ModItems.MICROCHIP)).save(p_301172_);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MICROCHIP.get(), 9)
                .requires(ModBlocks.MICROCHIP_BLOCK)
                .unlockedBy("has_microchip_block", has(ModBlocks.MICROCHIP_BLOCK)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLASTIC_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.PLASTIC.get())
                .unlockedBy("has_plastic", has(ModItems.PLASTIC)).save(p_301172_);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PLASTIC.get(), 9)
                .requires(ModBlocks.PLASTIC_BLOCK)
                .unlockedBy("has_plastic_block", has(ModBlocks.PLASTIC_BLOCK)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.PHONE.get())
                .pattern("CBC")
                .pattern("BBB")
                .pattern("PBP")
                .define('B', ModItems.MICROCHIP.get())
                .define('P', ModItems.PLASTIC.get())
                .define('C', Items.GLASS)
                .unlockedBy("has_microchip", has(ModItems.MICROCHIP)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.COMPUTER.get())
                .pattern("PDP")
                .pattern("MMM")
                .pattern("IRI")
                .define('P', ModItems.PLASTIC.get())
                .define('D', Items.DIAMOND)
                .define('M', ModItems.MICROCHIP.get())
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_microchip", has(ModItems.MICROCHIP)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RED_CASE.get())
                .pattern(" DP")
                .pattern(" DP")
                .pattern(" DP")
                .define('P', ModItems.PLASTIC.get())
                .define('D', Items.RED_DYE)
                .unlockedBy("has_phone", has(ModItems.PHONE)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLUE_CASE.get())
                .pattern(" DP")
                .pattern(" DP")
                .pattern(" DP")
                .define('P', ModItems.PLASTIC.get())
                .define('D', Items.BLUE_DYE)
                .unlockedBy("has_phone", has(ModItems.PHONE)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GREEN_CASE.get())
                .pattern(" DP")
                .pattern(" DP")
                .pattern(" DP")
                .define('P', ModItems.PLASTIC.get())
                .define('D', Items.GREEN_DYE)
                .unlockedBy("has_phone", has(ModItems.PHONE)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PINK_CASE.get())
                .pattern(" DP")
                .pattern(" DP")
                .pattern(" DP")
                .define('P', ModItems.PLASTIC.get())
                .define('D', Items.PINK_DYE)
                .unlockedBy("has_phone", has(ModItems.PHONE)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.YELLOW_CASE.get())
                .pattern(" DP")
                .pattern(" DP")
                .pattern(" DP")
                .define('P', ModItems.PLASTIC.get())
                .define('D', Items.YELLOW_DYE)
                .unlockedBy("has_phone", has(ModItems.PHONE)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORANGE_CASE.get())
                .pattern(" DP")
                .pattern(" DP")
                .pattern(" DP")
                .define('P', ModItems.PLASTIC.get())
                .define('D', Items.ORANGE_DYE)
                .unlockedBy("has_phone", has(ModItems.PHONE)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.IT_WORKSPACE.get())
                .pattern("PPP")
                .pattern("MMM")
                .pattern("PPP")
                .define('M', ModItems.MICROCHIP.get())
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "planks")))
                .unlockedBy("has_microchip", has(ModItems.MICROCHIP)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.CAMERA.get())
                .pattern("GGG")
                .pattern("MMM")
                .pattern("III")
                .define('M', ModItems.MICROCHIP.get())
                .define('G', Items.GLASS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_microchip", has(ModItems.MICROCHIP)).save(p_301172_);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.PENDRIVE.get())
                .pattern("   ")
                .pattern(" M ")
                .pattern(" I ")
                .define('M', ModItems.MICROCHIP.get())
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_microchip", has(ModItems.MICROCHIP)).save(p_301172_);
    }
}