package com.neotroll.neotroll;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class FakeOpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("neotroll.fakeop")) {
            sender.sendMessage(ChatColor.RED + "No permission!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /fakeop <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        // Отправляем фейковое сообщение о выдаче OP
        target.sendMessage(ChatColor.YELLOW + "You are now op!");
        target.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_GRAY + "Server" + ChatColor.GRAY + "] " +
                ChatColor.WHITE + "Made " + target.getName() + " a server operator");

        sender.sendMessage(ChatColor.GREEN + "Sent fake OP message to " + target.getName());
        return true;
    }
}