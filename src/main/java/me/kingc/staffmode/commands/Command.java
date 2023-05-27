package me.kingc.staffmode.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] args) {
        String prefix = "§7[§6Staff§7]§r ";
        if (args.length == 0) {
            // 输入了 /staff, 显示帮助
            if (sender.hasPermission("staffmode.help")) {
                help_text(sender, prefix);
                return true;
            } else {
                sender.sendMessage(prefix + "§c你没有权限");
                return false;
            }
        } else if (args.length == 1) {
            // 输入了 /staff *, 判断子指令
            if (args[0].equalsIgnoreCase("start")) {
                if (sender.hasPermission("staffmode.start")) {
                    if (sender instanceof Player){
                        sender.setOp(true);
                        sender.sendMessage(prefix + "§a已获取 OP 权限");
                        return true;
                    } else {
                        sender.sendMessage(prefix + "§c控制台不能执行该命令！");
                        return false;
                    }
                } else {
                    sender.sendMessage(prefix + "§c你没有权限");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("stop")) {
                if (sender.hasPermission("staffmode.stop")) {
                    if (sender instanceof Player){
                        sender.setOp(false);
                        sender.sendMessage(prefix + "§a已取消 OP 权限");
                        return true;
                    } else {
                        sender.sendMessage(prefix + "§c控制台不能执行该命令！");
                        return false;
                    }
                } else {
                    sender.sendMessage(prefix + "§c你没有权限");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                if (sender.hasPermission("staffmode.help")) {
                    help_text(sender, prefix);
                    return true;
                } else {
                    sender.sendMessage(prefix + "§c你没有权限");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("hide")) {
                if (sender.hasPermission("staffmode.hide")) {
                    if (sender instanceof Player){
                        Player player = (Player) sender; // 获取发送命令的玩家对象

                        // 创建一个没有粒子效果的隐藏药水效果
                        PotionEffect potionEffect = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false, true);

                        // 为玩家添加该效果
                        player.addPotionEffect(potionEffect);

                        sender.sendMessage(prefix + "§a已隐身");

                        return true;
                    } else {
                        sender.sendMessage(prefix + "§c控制台不能执行该命令！");
                        return false;
                    }
                } else {
                    sender.sendMessage(prefix + "§c你没有权限");
                }
            } else if (args[0].equalsIgnoreCase("show")) {
                if (sender.hasPermission("staffmode.show")) {
                    if (sender instanceof Player){
                        Player player = (Player) sender; // 获取发送命令的玩家对象

                        player.removePotionEffect(PotionEffectType.INVISIBILITY);

                        sender.sendMessage(prefix + "§a已取消隐身");

                        return true;
                    } else {
                        sender.sendMessage(prefix + "§c控制台不能执行该命令！");
                        return false;
                    }
                } else {
                    sender.sendMessage(prefix + "§c你没有权限");
                }
            } else if (args[0].equalsIgnoreCase("freeze")) {
                sender.sendMessage(prefix + "§c必须指定玩家");
                return false;
            } else if (args[0].equalsIgnoreCase("unfreeze")) {
                sender.sendMessage(prefix + "§c必须指定玩家");
                return false;
            }
        } else if (args.length == 2) {
             if (args[0].equalsIgnoreCase("freeze")) {
                if (sender.hasPermission("staffmode.freeze")) {
                    Player player = Bukkit.getServer().getPlayer(args[1]);
                    if (player != null && player.isOnline()) {
                        // 玩家在线
                        player.setWalkSpeed(0);
                        player.setFlySpeed(0);
                        sender.sendMessage(prefix + "§a" + args[1] + "§a 已被冻结");
                        player.sendMessage("§c你已被管理员 " + sender.getName() + "§c 冻结");
                    } else {
                        // 玩家不在线
                        sender.sendMessage(prefix + "§c玩家不在线");
                        return false;
                    }
                } else {
                    sender.sendMessage(prefix + "§c你没有权限");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("unfreeze")) {
                 if (sender.hasPermission("staffmode.unfreeze")) {
                     Player player = Bukkit.getServer().getPlayer(args[1]);
                     if (player != null && player.isOnline()) {
                         // 玩家在线
                         player.setWalkSpeed(0.2f);
                         player.setFlySpeed(0.1f);
                         sender.sendMessage(prefix + "§a" + args[1] + "§a 已取消冻结");
                         player.sendMessage("§a你已被管理员 " + sender.getName() + "§a 取消冻结");
                     } else {
                         // 玩家不在线
                         sender.sendMessage(prefix + "§c玩家不在线");
                         return false;
                     }
                 } else {
                     sender.sendMessage(prefix + "§c你没有权限");
                     return false;
                 }
             }
        }
        return false;
    }

    private void help_text(@NotNull CommandSender sender, String prefix) {
        sender.sendMessage(prefix + "§6/staff start §7进入 StaffMode");
        sender.sendMessage(prefix + "§6/staff stop §7退出 StaffMode");
        sender.sendMessage(prefix + "§6/staff help §7显示帮助菜单");
        sender.sendMessage(prefix + "§6/staff hide §7开启隐身模式");
        sender.sendMessage(prefix + "§6/staff show §7退出隐身模式");
        sender.sendMessage(prefix + "§6/staff freeze <玩家> §7冻结玩家，让其无法移动");
        sender.sendMessage(prefix + "§6/staff unfreeze <玩家> §7取消冻结玩家，让其可以移动");
    }
}
