package com.buildercgr.digitech.villager;

import com.buildercgr.digitech.blocks.ModBlocks;
import com.buildercgr.digitech.items.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.ArrayList;
import java.util.List;
public class ModVillagerTrades {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == ModVillagers.IT_TECHNICIAN.value()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.computeIfAbsent(1, k -> new ArrayList<>());
            trades.computeIfAbsent(2, k -> new ArrayList<>());
            trades.computeIfAbsent(3, k -> new ArrayList<>());
            trades.computeIfAbsent(4, k -> new ArrayList<>());
            trades.computeIfAbsent(5, k -> new ArrayList<>());

            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    new ItemStack(ModItems.MICROCHIP.get(), 6), 6, 3, 0.05f));

            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.PLASTIC.get(), 6), 6, 3, 0.05f));

            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(ModItems.PLASTIC, 6),
                    new ItemStack(Items.EMERALD, 1), 5, 6, 0.05f));

            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(ModItems.RED_CASE.get(), 1), 6, 3, 0.05f));

            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(ModItems.BLUE_CASE.get(), 1), 10, 3, 0.05f));

            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    new ItemStack(ModItems.PHONE.get(), 1), 10, 3, 0.05f));

            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 6),
                    new ItemStack(ModBlocks.COMPUTER.get(), 1), 10, 3, 0.05f));

            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.DIGITECH_OS_DISK.get(), 1), 10, 3, 0.05f));

            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    new ItemStack(ModItems.CAMERA.get(), 1), 10, 3, 0.05f));

            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(ModItems.MICROCHIP, 24),
                    new ItemStack(Items.DIAMOND, 1), 20, 3, 0.05f));
        }

    }
}