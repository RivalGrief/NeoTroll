package com.neotroll.neotroll;

import org.bukkit.plugin.java.JavaPlugin;
import com.neotroll.neotroll.CrashCommand;
import com.neotroll.neotroll.FakeOpCommand;
import com.neotroll.neotroll.LagCommand;
import com.neotroll.neotroll.CreeperCommand;

public class NeoTroll extends JavaPlugin {

    private static NeoTroll instance;

    @Override
    public void onEnable() {
        instance = this;

        // Регистрируем все команды
        getCommand("crash").setExecutor(new CrashCommand());
        getCommand("fakeop").setExecutor(new FakeOpCommand());
        getCommand("lag").setExecutor(new LagCommand());
        getCommand("creeper").setExecutor(new CreeperCommand());

        getLogger().info("NeoTroll v" + getDescription().getVersion() + " enabled!");
        getLogger().info("Now with 5 different trolling commands!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NeoTroll disabled!");
    }

    public static NeoTroll getInstance() {
        return instance;
    }
}