package com.buildercgr.digitech.items.custom;

import com.buildercgr.digitech.dataComponents.ModDataComponents;
import com.buildercgr.digitech.phone.os.core.events.init.DeviceStorageInit;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class pendrive extends Item {
    public pendrive(Properties p_41383_) {
        super(p_41383_);
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        if (level.isClientSide) return;
        if (!(entity instanceof Player player)) return;

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);

        if (data.copyTag().getBoolean("initialized")) return;

        initDevice(stack, level);

        CompoundTag tag = data.copyTag();
        tag.putBoolean("initialized", true);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    private void initDevice(ItemStack stack, Level level) {
        if (level.isClientSide) return;
        if (!stack.has(ModDataComponents.DEVICE_ID.get())) {
            String uuid = UUID.randomUUID().toString();
            stack.set(ModDataComponents.DEVICE_ID.get(), uuid);
            DeviceStorageInit.INSTANCE.CreatePendriveDir(uuid);
        }
    }

}