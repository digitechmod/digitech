package com.buildercgr.digitech.phone.os.core.events.init

import com.buildercgr.digitech.Digitech
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import net.minecraft.client.Minecraft
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.neoforged.fml.loading.FMLPaths
import java.nio.file.Files
import kotlin.apply

@OnlyIn(Dist.CLIENT)
object DeviceStorageInit {
    fun CreateDir (deviceId: String){
        try {
            var player = Minecraft.getInstance().player
            var baseDirectory = FMLPaths.CONFIGDIR.get()
                .resolve("digitech")
                .resolve("devices")
                .resolve(deviceId)

            var storageDir = baseDirectory.resolve("storage")
            var jsonDir = baseDirectory.resolve("device.json")

            Files.createDirectories(storageDir)

            if (!Files.exists(jsonDir)) {
                val json = JsonObject().apply{
                    player?.let{
                        addProperty("player", it.name.string)
                    }
                    addProperty("pendrive", "")
                    addProperty("id", deviceId)
                    addProperty("created_at", System.currentTimeMillis())
                }
                Files.newBufferedWriter(jsonDir).use{writer ->
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    writer.write(gson.toJson(json))
                }

            }
        }catch (problem : Exception){
            Digitech.LOGGER.error("Oops! Our bad. Try to restart the game, if it doesn't work you can report this via Github issues.")
        }
    }

    fun CreatePendriveDir (deviceId: String){
        try{
            var baseDirectory = FMLPaths.CONFIGDIR.get()
                .resolve("digitech")
                .resolve("devices")
                .resolve(deviceId)
            var storageDir = baseDirectory.resolve("storage")
            Files.createDirectories(storageDir)
        }catch (problem : Exception){
            Digitech.LOGGER.error("Pendrive missing. If you actually have one, report this on GitHub.")
        }
    }
}