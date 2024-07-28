package com.github.Ringoame196.Commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class TabComplete : TabCompleter {
    override fun onTabComplete(player: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        if (player !is Player) { return mutableListOf() } // プレイヤー以外 処理をしない
        val playerItem = player.inventory.itemInMainHand
        return when (args.size) {
            1 -> mutableListOf("display", "lore", "customModelData")
            else -> acquisitionItemMeta(playerItem, args.size - 2, args[0])
        }
    }
    private fun acquisitionItemMeta(item: ItemStack, number: Int, type: String): MutableList<String> {
        val tabList = mutableListOf("!reset")
        val itemMeta = item.itemMeta ?: return tabList
        when (type) {
            "display" -> tabList.add(itemMeta.displayName.replace("§", "&")) // 色変え対応
            "lore" -> tabList.add(acquisitionLore(itemMeta, number).replace("§", "&")) // 色変え対応
            "customModelData" -> tabList.add(acquisitionCustomModelData(itemMeta))
        }
        return tabList
    }

    private fun acquisitionCustomModelData(itemMeta: ItemMeta): String {
        return if (itemMeta.hasCustomModelData()) { // カスタムモデルデータが設定されているなら 設定されている値を返す
            itemMeta.customModelData.toString()
        } else { // カスタムモデルデータが未設定なら 空を返す
            ""
        }
    }
    private fun acquisitionLore(itemMeta: ItemMeta, number: Int): String {
        val lore = itemMeta.lore ?: return "" // loreが設定されていないなら空を返す
        return if (lore.size <= number) { // 求められている値が未設定の場合 空を返す
            ""
        } else {
            lore[number] // 既に設定されているものがあれば それを返す
        }
    }
}
