package com.github.Ringoame196

import net.md_5.bungee.api.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player

class Player(val player: Player) {
    fun sendErrorMessage(message: String) {
        player.sendMessage("${ChatColor.RED}[エラー] $message")
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f)
    }
}
