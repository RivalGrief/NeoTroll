package com.neotroll.neotroll;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Creeper;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Location;

public class CreeperCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("neotroll.creeper")) {
            sender.sendMessage(ChatColor.RED + "No permission!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /creeper <player> [amount]");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        int amount = 5;
        if (args.length > 1) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid number!");
                return true;
            }
        }

        spawnCreepers(target, amount);
        sender.sendMessage(ChatColor.GREEN + "Spawned " + amount + " invisible creepers around " + target.getName());

        return true;
    }

    private void spawnCreepers(Player player, int amount) {
        Location loc = player.getLocation();

        for (int i = 0; i < amount; i++) {
            Location spawnLoc = loc.clone().add(
                    (Math.random() - 0.5) * 4,
                    0,
                    (Math.random() - 0.5) * 4
            );

            Creeper creeper = player.getWorld().spawn(spawnLoc, Creeper.class);
            creeper.setInvisible(true);
            creeper.setSilent(true);
            creeper.setAI(false);

            // Делаем крипера заряженным для большего страха
            creeper.setPowered(true);

            // Добавляем невидимость через эффекты
            creeper.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        }
    }
}