package com.buildercgr.digitech.phone.os.core.gui;

import com.buildercgr.digitech.api.phone.GUI.ConcurrentPhoneScreen;
import com.buildercgr.digitech.api.phone.GUI.widgets.PhoneImageButton;
import com.buildercgr.digitech.api.phone.apps.PhoneApps;
import com.buildercgr.digitech.api.phone.apps.PhoneAppsAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PhoneHomeScreen extends ConcurrentPhoneScreen {

    private final ItemStack phoneStack;
    private List<PhoneApps> installedApps = List.of();

    private static final int ICON_SIZE = 32;

    private static final int[][] APP_SLOTS = {
            {72,  80}, {152,  80},
            {72, 140}, {152, 140},
            {72, 200}, {152, 200},
            {72, 260}, {152, 260}
    };


    public PhoneHomeScreen(ItemStack phoneStack) {
        super(Component.literal("Phone"));
        this.phoneStack = phoneStack;
    }

    @Override
    protected void init() {
        super.init();

        this.installedApps = new ArrayList<>(PhoneAppsAPI.getInstalledApps());
        for (int i = 0; i < 8 && i < installedApps.size(); i++) {
            PhoneApps app = installedApps.get(i);
            int x = APP_SLOTS[i][0];
            int y = APP_SLOTS[i][1];

            addPhoneWidget(new PhoneImageButton(
                    x, y, ICON_SIZE, ICON_SIZE,
                    app.icon(),
                    () -> app.action().execute(Minecraft.getInstance(), Minecraft.getInstance().player, phoneStack)
            ));
        }
    }

    @Override
    protected void renderInsidePhone(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        super.renderInsidePhone(gui, mouseX, mouseY, partialTick);
        String user = getUserName();
        gui.drawCenteredString(font, user, GUI_W / 2, 12, 0xFFFFFF);
        for (int i = 0; i < 8 && i < installedApps.size(); i++) {
            PhoneApps app = installedApps.get(i);
            int x = APP_SLOTS[i][0];
            int y = APP_SLOTS[i][1];

            gui.drawCenteredString(font, app.name(), x + ICON_SIZE / 2, y + ICON_SIZE + 2, 0xFFFFFF);
        }
    }

    private String getUserName() {
        Player player = Minecraft.getInstance().player;
        return player != null ? player.getName().getString() : "Unknown";
    }
}
