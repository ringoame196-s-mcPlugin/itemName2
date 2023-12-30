package com.github.Ringoame196

import com.github.Ringoame196.Commands.itemName
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        getCommand("itemName")!!.setExecutor(itemName())
    }

    override fun onDisable() {
        super.onDisable()
    }
}
