package com.neotroll.neotroll;

import org.bukkit.plugin.java.JavaPlugin;
import com.neotroll.neotroll.CrashCommand;

public class NeoTroll extends JavaPlugin {

    private static NeoTroll instance;

    @Override
    public void onEnable() {
        instance = this;

        // Регистрируем команды
        getCommand("crash").setExecutor(new CrashCommand());

        getLogger().info("NeoTroll v" + getDescription().getVersion() + " enabled!");
        getLogger().info("Created by NeoStudio - ready for some trolling!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NeoTroll disabled!");
    }

    public static NeoTroll getInstance() {
        return instance;
    }
}