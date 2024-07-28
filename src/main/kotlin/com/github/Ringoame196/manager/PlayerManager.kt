package com.github.Ringoame196.manager

import net.md_5.bungee.api.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player

class PlayerManager() {
    fun sendErrorMessage(message: String, player: Player) {
        player.sendMessage("${ChatColor.RED}[エラー] $message")
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f)
    }
}
