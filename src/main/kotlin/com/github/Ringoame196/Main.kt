package com.github.Ringoame196

import com.github.Ringoame196.command.Command
import com.github.Ringoame196.command.TabComplete
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val command = getCommand("itemname")
        command!!.setExecutor(Command())
        command.tabCompleter = TabComplete()
    }
}
