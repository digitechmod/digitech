package com.buildercgr.digitech.api.phone.GUI.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class PhoneTextBox extends PhoneWidget {

    public String value = "";
    public boolean focused = false;

    public PhoneTextBox(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void render(GuiGraphics gui) {
        gui.fill(x, y, x + w, y + h, 0xFF000000);
        gui.drawString(Minecraft.getInstance().font, value, x + 4, y + 6, 0xFFFFFF);
    }

    @Override
    public boolean click(double px, double py) {
        focused = super.click(px, py);
        return focused;
    }

    @Override
    public boolean type(char c) {
        if (!focused) return false;

        if (c == '\b') {
            if (!value.isEmpty()) value = value.substring(0, value.length() - 1);
            return true;
        }

        if (!Character.isISOControl(c)) {
            value += c;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!focused) return false;
        if (keyCode == GLFW.GLFW_KEY_BACKSPACE) {
            if (!value.isEmpty()) {
                value = value.substring(0, value.length() - 1);
            }
            return true;
        }

        return false;
    }

}

