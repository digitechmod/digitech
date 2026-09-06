package com.buildercgr.digitech.api.phone.apps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public record PhoneApps(
        ResourceLocation id,
        ResourceLocation icon,
        Component name,
        AppAction action
) {

    public interface AppAction {
        void execute(Minecraft mc, Player player, ItemStack phone);
    }
}
