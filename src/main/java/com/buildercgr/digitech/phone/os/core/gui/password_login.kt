package com.buildercgr.digitech.phone.os.core.gui

import com.buildercgr.digitech.api.phone.GUI.ConcurrentPhoneScreen
import com.buildercgr.digitech.api.phone.GUI.widgets.PhoneButton
import com.buildercgr.digitech.api.phone.GUI.widgets.PhoneTextBox
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class password_login(title: Component, private val password: String, private val phoneStack: ItemStack) : ConcurrentPhoneScreen(title) {

    lateinit var textBox: PhoneTextBox

    override fun init(){
        super.init()
        textBox = PhoneTextBox(20, 80, 150, 20)

        addPhoneWidget(textBox)

        addPhoneWidget(PhoneButton(50, 140,100,20,"accept", ::onSubimit))
        addPhoneWidget(PhoneButton(50, 170,100,20,"cancel", ::onCancel))
    }

    fun onSubimit () {
        if (textBox.value == password) {
            Minecraft.getInstance().player?.sendSystemMessage(Component.literal("Your password is correct. Welcome to your phone!"))
            Minecraft.getInstance().setScreen(PhoneHomeScreen(phoneStack))
        }else{
            Minecraft.getInstance().player?.sendSystemMessage(Component.literal("Your password is not correct. Please use your own phone, no other one"))
        }
    }
    fun onCancel(){Minecraft.getInstance().setScreen(null)}
}