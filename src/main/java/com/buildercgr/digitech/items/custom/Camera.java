package com.buildercgr.digitech.items.custom;

import com.buildercgr.digitech.camera.GUI.CameraScreen;
import com.buildercgr.digitech.dataComponents.ModDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class Camera extends Item {
    public Camera(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide) {
            Minecraft.getInstance().setScreen(new CameraScreen(Component.literal("Camera")));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!stack.has(ModDataComponents.DEVICE_ID.get())) {
            stack.set(ModDataComponents.DEVICE_ID.get(), UUID.randomUUID().toString());
        }
    }

}
