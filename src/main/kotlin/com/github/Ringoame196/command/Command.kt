package com.github.Ringoame196.command

import com.github.Ringoame196.manager.ItemManager
import com.github.Ringoame196.manager.PlayerManager
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.ItemMeta

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

        when (category) {
            "display" -> itemManager.setDisplay(meta, inputText)
            "lore" -> itemManager.setLore(meta, args)
            "customModelData" -> itemManager.setCustomModelData(meta, inputText)
        }

        replaceItemMeta(player, meta) // アイテム情報を置き換える
        sendChangeMessage(player, category, inputText) // 変更内容を表示
        return true
    }

    private fun replaceItemMeta(player: Player, meta: ItemMeta) {
        player.inventory.itemInMainHand.setItemMeta(meta)
    }
    private fun sendChangeMessage(player: Player, category: String, inputText: String) {
        val categoryMap = mapOf(
            "display" to "アイテム名",
            "lore" to "説明",
            "customModelData" to "カスタムモデルデータ"
        )

        player.playSound(player, Sound.BLOCK_ANVIL_USE, 1f, 1f)
        player.sendMessage(
            if (inputText == "!reset") {
                "${ChatColor.YELLOW}[itemName] ${categoryMap[category]}情報をリセットしました"
            } else {
                "${ChatColor.YELLOW}[itemName] ${categoryMap[category]}情報を変更しました"
            }
        )
    }
}
