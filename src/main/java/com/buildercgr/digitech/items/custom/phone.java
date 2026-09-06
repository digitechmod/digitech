package com.buildercgr.digitech.items.custom;

import com.buildercgr.digitech.api.networking.SendInitFromClient;
import com.buildercgr.digitech.dataComponents.ModDataComponents;
import com.buildercgr.digitech.phone.os.core.gui.password_login;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.UUID;

public class phone extends Item {
    public phone(Properties properties) {
        super(properties);
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        if (level.isClientSide) return;
        if (!(entity instanceof Player player)) return;

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);

        if (data.copyTag().getBoolean("initialized")) return;

        initDevice(stack, level, entity);

        CompoundTag tag = data.copyTag();
        tag.putBoolean("initialized", true);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        String password = stack.get(ModDataComponents.PASSWORD.get());

        if (level.isClientSide) {
            Minecraft.getInstance().setScreen(new password_login(Component.literal("Login using your password"),password, stack));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    private void initDevice(ItemStack stack, Level level, Entity entity) {
        if (level.isClientSide) return;

        if (!stack.has(ModDataComponents.DEVICE_ID.get())) {
            String uuid = UUID.randomUUID().toString();
            stack.set(ModDataComponents.DEVICE_ID.get(), uuid);

            if (entity instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new SendInitFromClient.InitDevice(uuid));
            }

            if (!stack.has(ModDataComponents.PASSWORD.get())) {
                stack.set(ModDataComponents.PASSWORD.get(), "1234");
            }
        }
    }


}