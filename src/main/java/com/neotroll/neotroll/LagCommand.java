package com.neotroll.neotroll;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import com.neotroll.neotroll.NeoTroll;

public class LagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("neotroll.lag")) {
            sender.sendMessage(ChatColor.RED + "No permission!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /lag <player> [seconds]");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        int seconds = 10;
        if (args.length > 1) {
            try {
                seconds = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid number!");
                return true;
            }
        }

        createLag(target, seconds);
        sender.sendMessage(ChatColor.GREEN + "Creating lag around " + target.getName() + " for " + seconds + " seconds!");

        return true;
    }

    private void createLag(Player player, int seconds) {
        for (int i = 0; i < seconds; i++) {
            Bukkit.getScheduler().runTaskLater(NeoTroll.getInstance(), () -> {
                // Создаем лаг с помощью частиц и звуков
                for (int j = 0; j < 100; j++) {
                    player.spawnParticle(Particle.FLAME, player.getLocation(), 1000, 5, 5, 5);
                    player.spawnParticle(Particle.LAVA, player.getLocation(), 1000, 5, 5, 5);
                }
                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1.0f, 1.0f);
            }, i * 20L); // 20 тиков = 1 секунда
        }
    }
}