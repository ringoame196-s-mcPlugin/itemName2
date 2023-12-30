package com.github.Ringoame196

import com.github.Ringoame196.Commands.ItemName
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        getCommand("itemName")!!.setExecutor(ItemName())
    }

    override fun onDisable() {
        super.onDisable()
    }
}
