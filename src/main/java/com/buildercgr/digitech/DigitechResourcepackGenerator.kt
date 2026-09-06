package com.buildercgr.digitech

import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.neoforged.fml.loading.FMLPaths
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

@OnlyIn(Dist.CLIENT)
object DigitechResourcepackGenerator {
    private const val DYNAMIC_PACK_NAME = "digitech_dynamic"

    fun generateForCamera(cameraID: String) {
        try {
            val minecraftDir = FMLPaths.GAMEDIR.get()
            val resourcepackDir = minecraftDir.resolve("resourcepacks").resolve(DYNAMIC_PACK_NAME)

            if (!Files.exists(resourcepackDir.resolve("pack.mcmeta"))) {
                generate()
            }

            val texturesDir = resourcepackDir
                .resolve("assets")
                .resolve(DYNAMIC_PACK_NAME)
                .resolve("textures")
                .resolve("gui")
                .resolve(cameraID)

            val photoModelDir = resourcepackDir
                .resolve("assets")
                .resolve("digitech")
                .resolve("models")
                .resolve("item")

            val photoTextureDir = resourcepackDir
                .resolve("assets")
                .resolve("digitech")
                .resolve("textures")
                .resolve("item")

            Files.createDirectories(texturesDir)
            Files.createDirectories(photoModelDir)
            Files.createDirectories(photoTextureDir)

            val sourceDir = FMLPaths.CONFIGDIR.get()
                .resolve("digitech")
                .resolve("photos")
                .resolve(cameraID)

            if (Files.exists(sourceDir)) {
                Files.list(sourceDir).use { files ->
                    files.filter { it.toString().endsWith(".png") }
                        .forEach { p ->
                            val target = texturesDir.resolve(p.fileName)
                            copySafe(p, target)
                            val targetItem = photoTextureDir.resolve(p.fileName)
                            copySafe(p, targetItem)
                        }
                }
            }

        } catch (problem: IOException) {
            problem.printStackTrace()
        }
    }

    fun generate() {
        try {
            val minecraftDir = FMLPaths.GAMEDIR.get()
            val resourcepackDir = minecraftDir.resolve("resourcepacks").resolve(DYNAMIC_PACK_NAME)
            val photoModelDir = resourcepackDir
                .resolve("assets")
                .resolve("digitech")
                .resolve("models")
                .resolve("item")

            val texturesDir = resourcepackDir
                .resolve("assets")
                .resolve(DYNAMIC_PACK_NAME)
                .resolve("textures")
                .resolve("gui")

            copyPackMcmeta(resourcepackDir)
            Files.createDirectories(texturesDir)
            copyPhotoModel(photoModelDir)
        } catch (problem: IOException) {
            problem.printStackTrace()
        }
    }

    private fun copyPackMcmeta(resourcepackDir: Path) {
        Files.createDirectories(resourcepackDir)
        val target = resourcepackDir.resolve("pack.mcmeta")
        if (Files.exists(target)) return

        val input = javaClass.classLoader.getResourceAsStream("pack_templates/pack.mcmeta")

        if (input == null) {
            Digitech.LOGGER.error("pack.mcmeta template not found! Perhaps it's a development error, let me know on Discord")
            return
        }

        input.use {
            Files.copy(it, target)
        }
    }

    private fun copyPhotoModel(file: Path) {
        Files.createDirectories(file)
        val target = file.resolve("photo.json")
        if (Files.exists(target)) return

        val input = javaClass.classLoader.getResourceAsStream("pack_templates/photo.json")
        if (input == null) {
            Digitech.LOGGER.error("photo model template not found! Perhaps it's a development error, let me know on Discord")
            return
        }
        input.use {
            Files.copy(it, target)
        }
    }

    private fun copySafe(from: Path, objetive: Path) {
        try {
            Files.copy(from, objetive, StandardCopyOption.REPLACE_EXISTING)
        } catch (problem: IOException) {
            problem.printStackTrace()
        }
    }

}
