package com.buildercgr.digitech.camera.event

import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.neoforged.fml.loading.FMLPaths
import java.io.IOException
import java.nio.file.Files
@OnlyIn(Dist.CLIENT)
object GalleryManager {

    private val images = mutableListOf<String>()

    @JvmStatic
    fun loadImages(cameraId: String): List<String> {
        val dir = FMLPaths.CONFIGDIR.get()
            .resolve("digitech")
            .resolve("photos")
            .resolve(cameraId)

        if (!Files.exists(dir)) return emptyList()

        return try {
            Files.list(dir).use { files ->
                files
                    .filter { it.toString().endsWith(".png") }
                    .map { it.fileName.toString() }
                    .sorted()
                    .toList()
            }
        } catch (problem: IOException) {
            emptyList()
        }
    }
    fun getImages(): List<String> = images
}