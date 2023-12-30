package com.github.Ringoame196

import net.md_5.bungee.api.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.ItemMeta

class ItemManager {
    val reset = "!reset"
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
    fun setDisplay(meta: ItemMeta, displayName: String?) {
        meta.setDisplayName(changeColorCode(displayName ?: ""))
        if (displayName == reset) {
            meta.setDisplayName(null)
        }
    }
    fun setLore(meta: ItemMeta, oldLore: Array<out String>) {
        val newLore = mutableListOf<String>()
        for (i in 1 until oldLore.size) {
            newLore.add(changeColorCode(oldLore[i]))
        }
        meta.lore = newLore
        if (oldLore[1] == reset) {
            meta.lore = null
        }
    }
    fun setCustomModelData(meta: ItemMeta, customModule: Int?) {
        meta.setCustomModelData(customModule)
    }
    private fun changeColorCode(text: String): String {
        return text.replace("&", "§")
    }
    fun itemSetting(player: Player, meta: ItemMeta, menu: String, inputText: String) {
        val menuMap = mapOf(
            "display" to "アイテム名",
            "lore" to "説明",
            "customModelData" to "カスタムモデルデータ"
        )
        val action = if (inputText == "!reset") { "${ChatColor.RED}リセット" } else { "変更" }
        player.inventory.itemInMainHand.setItemMeta(meta)
        player.playSound(player, Sound.BLOCK_ANVIL_USE, 1f, 1f)
        player.sendMessage("${ChatColor.YELLOW}[itemName] ${menuMap[menu]}情報を${action}しました")
    }
}
