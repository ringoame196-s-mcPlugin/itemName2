package com.github.Ringoame196.Manager

import org.bukkit.inventory.meta.ItemMeta

class ItemManager {
    private val reset = "!reset"
    fun setDisplay(meta: ItemMeta, displayName: String?) {
        if (displayName == reset) { // reset用のが入力されたらリセットする
            meta.setDisplayName(null)
        } else {
            val colorSupportedText = changeColorCode(displayName ?: "") // カラーに対応しているのに変える
            meta.setDisplayName(colorSupportedText) // アイテム名を変更する
        }
    }
    fun setLore(meta: ItemMeta, arg: Array<out String>) {
        val newLore = mutableListOf<String>()
        if (arg[1] != reset) { // reset以外だった場合は新しいloreを作る
            for (i in 1 until arg.size) { // 引数から新たなloreを作る
                val colorSupportedText = changeColorCode(arg[i]) // カラーに対応しているのに変える
                newLore.add(colorSupportedText)
            }
        }
        meta.lore = newLore // 置き換える
    }
    fun setCustomModelData(meta: ItemMeta, customModuleString: String) {
        val customModel = try { // intに変換できる場合はその値を 変換できない場合はnullを渡す
            customModuleString.toInt()
        } catch (e: NumberFormatException) {
            null
        }
        meta.setCustomModelData(customModel)
    }
    private fun changeColorCode(text: String): String {
        // セクションに変えることにより 文字変えに対応したものにして返す
        return text.replace("&", "§")
    }
}
