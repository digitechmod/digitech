package com.buildercgr.digitech.phone.apps.rememberApp

import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent

@EventBusSubscriber(value = [Dist.CLIENT])
object RememberTicksWaiter {
    private data class PendingMessage(
        val message: Component,
        var ticksLeft: Int
    )

    private val pendingMessages = mutableListOf<PendingMessage>()
    fun sendDelayedMessage(text: String, ticks: Int) {
        pendingMessages += PendingMessage(Component.literal(text), ticks)
    }

    @JvmStatic
    @SubscribeEvent
    fun onClientTick(event: ClientTickEvent.Post) {
        val iterator = pendingMessages.iterator()

        while (iterator.hasNext()) {
            val pm = iterator.next()
            pm.ticksLeft--

            if (pm.ticksLeft <= 0) {
                Minecraft.getInstance().player?.sendSystemMessage(pm.message)
                iterator.remove()
            }
        }
    }
}
