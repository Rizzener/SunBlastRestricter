package me.rizzener.sunblastrestricter.Commands;

import me.rizzener.sunblastrestricter.tools.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandReload implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length >= 1) {
            sender.sendMessage(Utils.getMessage("messages.commands.reload.usage"));
            return true;
        }
        if (!sender.hasPermission("SunBlastRestricter.reload")) {
            sender.sendMessage(Utils.getMessage("messages.commands.reload.no-perm"));
            return true;
        } else {
            Utils.reloadConfig();
            sender.sendMessage(Utils.getMessage("messages.commands.reload.successfully"));
        }
        return false;
    }
}
