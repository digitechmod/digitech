package com.buildercgr.digitech.phone.apps.rememberApp

import com.buildercgr.digitech.api.networking.SendInitFromClient
import com.buildercgr.digitech.api.phone.GUI.ConcurrentPhoneScreen
import com.buildercgr.digitech.api.phone.GUI.widgets.PhoneButton
import com.buildercgr.digitech.api.phone.GUI.widgets.PhoneTextBox
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.neoforged.neoforge.network.PacketDistributor

@OnlyIn(Dist.CLIENT)
class RememberApp : ConcurrentPhoneScreen(Component.literal("RememberApp")) {
    private lateinit var textField: PhoneTextBox
    private lateinit var minutesField: PhoneTextBox

    override fun init(){
        super.init()

        textField = PhoneTextBox(20, 60, 150, 20)
        minutesField = PhoneTextBox(20, 120, 150, 20)

        addPhoneWidget(textField)
        addPhoneWidget(minutesField)
        addPhoneWidget(
            PhoneButton(
                40, 170, 110, 20,
                "Remember It",
                ::onSubimit
            )
        )
    }

    private fun onSubimit(){
        val text = textField.value
        val minutes = minutesField.value

        if (text.isEmpty() || minutes.isEmpty()){
            Minecraft.getInstance().player?.sendSystemMessage(Component.literal("Let us know when and wich message we have to remember you, please"))
        }

        val minutesInt = minutes.toIntOrNull()

        if (minutesInt == null) {
            Minecraft.getInstance().player?.sendSystemMessage(Component.literal("Minutes can't be written with letters..."))
            return
        }

        val ticks = minutesInt * 60 * 20

        Minecraft.getInstance().setScreen(null)
        RememberTicksWaiter.sendDelayedMessage("Remember: \$text", ticks)

    }

}