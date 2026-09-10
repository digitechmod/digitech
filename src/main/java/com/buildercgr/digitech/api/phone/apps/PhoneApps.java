package com.buildercgr.digitech.api.phone.apps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PhoneApps {

    private final ResourceLocation id;
    private final ResourceLocation icon;
    private final Component name;
    private AppAction action;

    public PhoneApps(ResourceLocation id, ResourceLocation icon, Component name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    public ResourceLocation id() {
        return id;
    }

    public ResourceLocation icon() {
        return icon;
    }

    public Component name() {
        return name;
    }

    public AppAction action() {
        return action;
    }

    public void setAction(AppAction action) {
        this.action = action;
    }

    public interface AppAction {
        void execute(Minecraft mc, Player player, ItemStack phone);
    }
}
