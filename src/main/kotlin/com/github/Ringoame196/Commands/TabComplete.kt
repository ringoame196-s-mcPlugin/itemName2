package com.github.Ringoame196.Commands

import com.github.Ringoame196.ItemManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class TabComplete : TabCompleter {
    override fun onTabComplete(player: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        val itemManager = ItemManager()
        if (player !is Player) { return mutableListOf() }
        return when (args.size) {
            1 -> mutableListOf("display", "lore", "customModelData")
            in 2..Int.MAX_VALUE -> (itemManager.acquisitionDefaultName(player, args)?.plus(itemManager.reset))?.toMutableList() ?: mutableListOf()
            else -> mutableListOf()
        }
    }
}
