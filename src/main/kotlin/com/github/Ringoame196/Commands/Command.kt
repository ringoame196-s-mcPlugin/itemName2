package com.github.Ringoame196.Commands

import com.github.Ringoame196.ItemManager
import com.github.Ringoame196.PlayerManager
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Command : CommandExecutor {
    private val itemManager = ItemManager()
    override fun onCommand(player: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val playerManager = PlayerManager()
        // プレイヤー以外実行不可
        if (player !is Player) {
            player.sendMessage("${ChatColor.RED}プレイヤー以外実行することはできません")
            return true
        }

        val playerItem = player.inventory.itemInMainHand // メインハンドに持っているアイテム
        if (playerItem.type == Material.AIR) {
            playerManager.sendErrorMessage("空気 または 非対応のアイテムです", player)
            return true
        }

        val meta = playerItem.itemMeta
        // metaがnullだった場合
        if (meta == null) {
            playerManager.sendErrorMessage("アイテム情報を取得できませんでした", player)
            return true
        }

        // 入力が不十分だった場合
        if (args.isEmpty() || args.size < 2) {
            return false
        }
        val category = args[0]
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
        processMap[category]?.invoke()
        itemManager.itemSetting(player, meta, category, inputText)
        return true
    }
}
