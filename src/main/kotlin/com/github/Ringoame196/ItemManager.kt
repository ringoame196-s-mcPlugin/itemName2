package com.github.Ringoame196

import net.md_5.bungee.api.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.ItemMeta

class ItemManager {
    fun acquisitionDefaultName(player: Player, typeList: Array<out String>): MutableList<String>? {
        val playerItem = player.inventory.itemInMainHand
        val meta = playerItem.itemMeta ?: return mutableListOf()
        return when (typeList[0]) {
            "display" -> mutableListOf(meta.displayName)
            "lore" -> meta.lore ?: mutableListOf()
            "customModelData" -> try { mutableListOf(meta.customModelData.toString()) } catch (e: Exception) { mutableListOf() }
            else -> mutableListOf("${ChatColor.RED}引数が間違っています")
        }
    }
    fun setDisplay(meta: ItemMeta, displayName: String?): ItemMeta {
        meta.setDisplayName(change(displayName ?: ""))
        return meta
    }
    fun setLore(meta: ItemMeta, oldLore: Array<out String>?): ItemMeta {
        val newLore = mutableListOf<String>()
        if (oldLore != null) {
            for (i in 1 until oldLore.size) {
                newLore.add(change(oldLore[i]))
            }
        }
        meta.lore = newLore
        return meta
    }
    fun setCustomModelData(meta: ItemMeta, customModule: Int?): ItemMeta {
        meta.setCustomModelData(customModule)
        return meta
    }
    private fun change(text: String): String {
        var newText = text
        newText = changeColorCode(newText)
        newText = changeReset(newText)
        return newText
    }
    private fun changeColorCode(text: String): String {
        return text.replace("&", "§")
    }
    private fun changeReset(text: String): String {
        return if (text == "!reset") { "" } else { text }
    }
    fun itemSetting(player: Player, meta: ItemMeta, menu: String) {
        val menuMap = mapOf(
            "display" to "アイテム名",
            "lore" to "説明",
            "customModelData" to "カスタムモデルデータ"
        )
        player.inventory.itemInMainHand.setItemMeta(meta)
        player.playSound(player, Sound.BLOCK_ANVIL_USE, 1f, 1f)
        player.sendMessage("${ChatColor.YELLOW}[itemName] ${menuMap[menu]}を変更しました")
    }
}
