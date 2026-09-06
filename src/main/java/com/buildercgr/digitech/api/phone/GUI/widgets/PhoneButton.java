package com.buildercgr.digitech.api.phone.GUI.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PhoneButton extends PhoneWidget {

    private final String text;
    private final Runnable action;

    public PhoneButton(int x, int y, int w, int h, String text, Runnable action) {
        super(x, y, w, h);
        this.text = text;
        this.action = action;
    }

    @Override
    public void render(GuiGraphics gui) {
        gui.fill(x, y, x + w, y + h, 0xFF222222);
        gui.drawString(Minecraft.getInstance().font, text, x + 10, y + 6, 0xFFFFFF);
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
