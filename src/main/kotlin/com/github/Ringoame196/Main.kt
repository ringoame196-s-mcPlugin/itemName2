package com.github.Ringoame196

import com.github.Ringoame196.Commands.Command
import com.github.Ringoame196.Commands.TabComplete
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val command = getCommand("itemName")
        command!!.setExecutor(Command())
        command.tabCompleter = TabComplete()
    }
}
