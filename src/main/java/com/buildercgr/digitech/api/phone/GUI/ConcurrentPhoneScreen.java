package com.buildercgr.digitech.api.phone.GUI;

import com.buildercgr.digitech.api.phone.GUI.widgets.PhoneWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class ConcurrentPhoneScreen extends Screen {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.parse("digitech:textures/gui/phone.png");
    public static final int GUI_W = 256;
    public static final int GUI_H = 512;

    protected int leftPos;
    protected int topPos;
    protected float scale;

    protected ConcurrentPhoneScreen(Component title) {
        super(title);
    }

    protected final ArrayList<PhoneWidget> phoneWidgets = new ArrayList<>();

    protected void addPhoneWidget(PhoneWidget w) {
        phoneWidgets.add(w);
    }


    @Override
    protected void init() {
        super.init();

        this.scale = Math.min(
                (float) this.height / GUI_H,
                (float) this.width / GUI_W
        );

        int scaledW = (int)(GUI_W * scale);
        int scaledH = (int)(GUI_H * scale);

        this.leftPos = (this.width - scaledW) / 2;
        this.topPos = (this.height - scaledH) / 2;
    }

    protected void renderInsidePhone(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        for (PhoneWidget w : phoneWidgets) {
            w.render(gui);
        }
    }


    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {

        this.renderBackground(gui, mouseX, mouseY, partialTick);

        gui.pose().pushPose();
        gui.pose().translate(leftPos, topPos, 0);
        gui.pose().scale(scale, scale, 1f);
        gui.blit(TEXTURE, 0, 0, 0, 0, GUI_W, GUI_H, GUI_W, GUI_H);
        renderInsidePhone(gui, mouseX, mouseY, partialTick);

        gui.pose().popPose();

    }


    protected boolean clickInsidePhone(double px, double py, int button) {
        for (PhoneWidget w : phoneWidgets) {
            if (w.click(px, py)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        double px = (mouseX - leftPos) / scale;
        double py = (mouseY - topPos) / scale;

        if (clickInsidePhone(px, py, button)) {
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean charTyped(char c, int modifiers) {
        for (PhoneWidget w : phoneWidgets) {
            if (w.type(c)) return true;
        }
        return super.charTyped(c, modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (PhoneWidget w : phoneWidgets) {
            if (w.keyPressed(keyCode, scanCode, modifiers)) return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }



}
