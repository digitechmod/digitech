package com.buildercgr.digitech.camera.GUI

import com.buildercgr.digitech.camera.event.CapturePhotoEvent.schedulePhoto
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import java.util.UUID
import net.minecraft.client.gui.GuiGraphics
import com.buildercgr.digitech.dataComponents.ModDataComponents
import com.buildercgr.digitech.items.custom.Camera
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class CameraScreen(p_96550_: Component) : Screen(p_96550_) {
    private val textures = mutableListOf<ResourceLocation>()

    private val backgroundTexture = ResourceLocation.fromNamespaceAndPath("digitech", "textures/gui/camera_screen.png")

    override fun init(){
        val centerX = width / 2
        val centerY = height / 2
        val filename = "${UUID.randomUUID()}.png"

        addRenderableWidget(
            Button.Builder(
                Component.literal("Take a photo")
            ){schedulePhoto(filename)}
                .pos(centerX-120, centerY+85)
                .size(120,40)
                .build()
        )

        addRenderableWidget(
            Button.Builder(
                Component.literal("See gallery")
            ){openGallery()}
                .pos(centerX, centerY+85)
                .size(120,40)
                .build()
        )
    }

    override fun render(graphics: GuiGraphics, x : Int, y : Int, partialTicks: Float) {
        super.render(graphics, x, y, partialTicks)

        val guiWidth = 256
        val guiHeight = 256

        val guix = (width - guiWidth) / 2
        val guih = (height - guiHeight) / 2

        graphics.blit(
            backgroundTexture,
            guix, guih,
            0.0F, 0.0F,
            guiWidth, guiHeight,
            guiWidth, guiHeight
        )
    }

    companion object{
        fun openGallery(){
            val player = Minecraft.getInstance().player ?: return
            val stack = player.mainHandItem

            if (stack.item is Camera){
                val cameraId = stack[ModDataComponents.DEVICE_ID.get()]
                Minecraft.getInstance().setScreen(GalleryScreen(cameraId!!, 0))
            }
        }
    }
}