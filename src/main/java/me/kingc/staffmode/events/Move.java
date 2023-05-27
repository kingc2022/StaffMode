package me.kingc.staffmode.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getWalkSpeed() == 0 || event.getPlayer().getFlySpeed() == 0) { //如果玩家已被冻结
            event.setCancelled(true); //取消该移动事件，即玩家无法移动
        }
    }
}
