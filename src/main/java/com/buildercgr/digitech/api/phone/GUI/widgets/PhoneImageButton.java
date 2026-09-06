package com.buildercgr.digitech.api.phone.GUI.widgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PhoneImageButton extends PhoneWidget {

    private final ResourceLocation icon;
    private final Runnable action;

    public PhoneImageButton(int x, int y, int w, int h, ResourceLocation icon, Runnable action) {
        super(x, y, w, h);
        this.icon = icon;
        this.action = action;
    }

    @Override
    public void render(GuiGraphics gui) {
        gui.blit(icon, x, y, 0, 0, w, h, w, h);
    }

    @Override
    public boolean click(double px, double py) {
        if (super.click(px, py)) {
            action.run();
            return true;
        }
        return false;
    }
}


