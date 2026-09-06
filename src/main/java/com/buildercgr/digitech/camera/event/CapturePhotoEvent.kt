package com.buildercgr.digitech.camera.event

import com.buildercgr.digitech.Digitech
import com.buildercgr.digitech.DigitechResourcepackGenerator
import com.buildercgr.digitech.items.custom.Camera
import net.minecraft.client.Minecraft
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.ClientTickEvent
import com.buildercgr.digitech.dataComponents.ModDataComponents
import com.buildercgr.digitech.items.custom.phone
import com.mojang.blaze3d.platform.NativeImage
import net.minecraft.client.Screenshot
import net.minecraft.network.chat.Component
import net.neoforged.fml.loading.FMLPaths
import java.io.IOException
import java.nio.file.Files

@OnlyIn(Dist.CLIENT)
object CapturePhotoEvent {

    private var delayTicks = -1
    private var pendingFile: String? = null

    fun schedulePhoto(fileName: String){
        delayTicks = 15
        pendingFile = fileName
        Minecraft.getInstance().setScreen(null)
    }

    @SubscribeEvent
    fun onClientTick(event: ClientTickEvent.Post){
        if (delayTicks < 0) return

        delayTicks--
        if (delayTicks > 0) return

        pendingFile?.let{takeWorldPhoto(it)}
        delayTicks = -1
        pendingFile=null
    }

    private fun takeWorldPhoto(fileName: String){
        val mc = Minecraft.getInstance()

        val level = mc.level ?: return
        val player = mc.player ?: return

        val stack = player.mainHandItem

        if (stack.item !is Camera && stack.item !is phone) return

        val id = stack[ModDataComponents.DEVICE_ID.get()] ?: return
        if (id.isEmpty()) return

        try {
            val directory = FMLPaths.CONFIGDIR.get()
                .resolve("digitech")
                .resolve("photos")
                .resolve(id)

            Files.createDirectories(directory)

            val file = directory.resolve(fileName)

            val oldHideGui = mc.options.hideGui
            mc.options.hideGui = true

            val target = mc.mainRenderTarget
            val img = Screenshot.takeScreenshot(target)
            val photo = cropCenter256(img)

            photo.writeToFile(file)
            photo.close()
            img.close()

            mc.options.hideGui = oldHideGui

            DigitechResourcepackGenerator.generateForCamera(id)
            Minecraft.getInstance().reloadResourcePacks()

            player.sendSystemMessage(Component.literal("Your awesome photo was stored on the camera"))
        } catch (e: IOException) {
            Digitech.LOGGER.error("There's an error on your photo, or maybe in our code :(", e)
        }
    }

    private fun cropCenter256(src: NativeImage): NativeImage {
        val size = 256

        val srcW = src.width
        val srcH = src.height

        val x0 = (src.width - size) / 2
        val y0 = (src.height - size) / 2

        val out = NativeImage(size, size, false)

        for (x in 0 until size) {
            for (y in 0 until size) {
                out.setPixelRGBA(
                    x,y,
                    src.getPixelRGBA(x+x0, y+y0)
                )
            }
        }
        return out
    }
}