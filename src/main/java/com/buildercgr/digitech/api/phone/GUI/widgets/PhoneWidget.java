package com.buildercgr.digitech.api.phone.GUI.widgets;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PhoneWidget {

    public int x, y, w, h;

    public PhoneWidget(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void render(GuiGraphics gui);

    public boolean click(double px, double py) {
        return px >= x && px <= x + w && py >= y && py <= y + h;
    }

    public boolean type(char c) {
        return false;
    }
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) { return false; }

}

