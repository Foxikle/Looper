package dev.foxikle.looper;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Looper extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("loop").setExecutor(new CommandHandler(this));
        getCommand("loop").setTabCompleter(new CommandHandler(this));
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

}
