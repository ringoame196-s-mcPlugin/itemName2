package com.github.Ringoame196.Commands

import com.github.Ringoame196.ItemManager
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Command : CommandExecutor {
    private val itemManager = ItemManager()
    override fun onCommand(player: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (player !is Player) {
            return false
        }
        val playerClass = com.github.Ringoame196.Player(player)
        val playerItem = player.inventory.itemInMainHand
        if (playerItem.type == Material.AIR) {
            playerClass.sendErrorMessage("空気以外を持ってください")
            return false
        }
        val meta = playerItem.itemMeta ?: return false
        if (args.isEmpty() || args.size < 2) {
            return false
        }
        val menu = args[0]
        val inputText = args[1]
        val processMap = mutableMapOf(
            "display" to { itemManager.setDisplay(meta, inputText) },
            "lore" to { itemManager.setLore(meta, args) },
            "customModelData" to {
                try {
                    val customModelData = inputText.toInt()
                    itemManager.setCustomModelData(meta, customModelData)
                } catch (e: NumberFormatException) {
                    itemManager.setCustomModelData(meta, null)
                }
            }
        )
        processMap[menu]?.invoke()
        itemManager.itemSetting(player, meta, menu, inputText)
        return true
    }
}
