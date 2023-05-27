package me.kingc.staffmode;

import me.kingc.staffmode.commands.Command;
import me.kingc.staffmode.events.Move;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;


public final class StaffMode extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger logger = getLogger();
        logger.info("[StaffMode] Plugin enabled");
        logger.info("[StaffMode] Author: kingc");

        // 注册命令

        Objects.requireNonNull(getCommand("staff")).setExecutor(new Command());

        // 注册监听器

        Move listener = new Move();
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        Logger logger = getLogger();
        logger.info("[StaffMode] Plugin disabled");
    }
}
