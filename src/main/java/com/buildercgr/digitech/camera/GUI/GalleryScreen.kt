package com.buildercgr.digitech.camera.GUI

import com.buildercgr.digitech.camera.event.GalleryManager
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.neoforged.fml.loading.FMLPaths
import kotlin.math.max
import kotlin.math.min
@OnlyIn(Dist.CLIENT)
class GalleryScreen(private val cameraId: String,
                    startIndex: Int): Screen(Component.literal("Gallery")) {

    private val images:List<String> = GalleryManager.loadImages(cameraId)
    private var index:Int= if (images.isEmpty()) 0 else Mth.clamp(startIndex, 0, images.size - 1)
    private var background: ResourceLocation? = null

    companion object{
        private const val IMAGE_WIDTH = 256
        private const val IMAGE_HEIGHT = 256
    }

    init{
        updateBackground()
    }
    private fun updateBackground() {
        if (images.isEmpty()) return

        background = ResourceLocation.fromNamespaceAndPath(
            "digitech_dynamic",
            "textures/gui/$cameraId/${images[index]}"
        )

    }

    override fun init(){
        val centerY = height / 2

        addRenderableWidget(
            Button.builder(Component.literal("<")) {previous()}
                .pos(20, centerY)
                .size(20, 20)
                .build()
        )

        addRenderableWidget(
            Button.builder(Component.literal(">")) { next() }
                .pos(width - 40, centerY)
                .size(20, 20)
                .build()
        )

        addRenderableWidget(
            Button.builder(Component.literal("Open photos")) { openPhotosFolder() }
                .pos(width / 2 - 110, height - 40)
                .size(100, 20)
                .build()
        )
    }

    private fun openPhotosFolder(){
        val dir = FMLPaths.CONFIGDIR.get().resolve("digitech").resolve("photos").resolve(cameraId)
        val folder = dir.toFile()
        if(!folder.exists()) folder.mkdirs()

        try{
            val os = System.getProperty("os.name").lowercase()

            when{
                os.contains("win") -> Runtime.getRuntime().exec(arrayOf("explorer.exe", folder.absolutePath))
                os.contains("mac") -> Runtime.getRuntime().exec(arrayOf("open", folder.absolutePath))
                os.contains("nux") || os.contains("nix") ->
                    Runtime.getRuntime().exec(arrayOf("xdg-open", folder.absolutePath))
                else -> Minecraft.getInstance().player?.sendSystemMessage(
                    Component.literal("You can't open this folder, sry. Maybe you have a very strange OS")
                )
            }
        } catch(problem: Exception){
            problem.printStackTrace()
            Minecraft.getInstance().player?.sendSystemMessage(
                Component.literal("A very strange error has hapened : ${problem.message}")
            )
        }
    }

    private fun next(){
        if (images.isEmpty()) return
        index = (index + 1) % images.size
        updateBackground()
    }

    private fun previous(){
        if (images.isEmpty()) return
        index = (index - 1 + images.size) % images.size
        updateBackground()
    }

    override fun renderBackground(graphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.renderBackground(graphics, mouseX, mouseY, partialTicks)
        val gn = background ?: return

        var scale = min(width / IMAGE_WIDTH, height / IMAGE_HEIGHT)
        scale = max(1, scale)

        val drawW = IMAGE_WIDTH * scale
        val drawH = IMAGE_HEIGHT * scale

        val x = (width - drawW) / 2
        val y = (height - drawH) / 2

        graphics.pose().pushPose()
        graphics.pose().translate(x.toFloat(), y.toFloat(), 0f)
        graphics.pose().scale(scale.toFloat(), scale.toFloat(), 1f)

        graphics.blit(
            background,
            0, 0,
            0f, 0f,
            IMAGE_WIDTH, IMAGE_HEIGHT,
            IMAGE_WIDTH, IMAGE_HEIGHT,
        )

        graphics.pose().popPose()
    }

    override fun render(graphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        super.render(graphics, mouseX, mouseY, partialTick)
    }

}