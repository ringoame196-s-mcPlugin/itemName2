package com.github.Ringoame196

import com.github.Ringoame196.command.Command
import com.github.Ringoame196.command.TabComplete
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val command = getCommand("itemname")
        command!!.setExecutor(Command())
        command.tabCompleter = TabComplete()

        notificationVersionMismatch()
    }
    private fun notificationVersionMismatch() {
        val pluginVersion = description.apiVersion
        val mcVersion = Bukkit.getVersion()
        if (pluginVersion != mcVersion) {
            Bukkit.getConsoleSender().sendMessage("[${this.name}] Some items may not be compatible due to version mismatch(version:$pluginVersion)")
        }
    }
}
