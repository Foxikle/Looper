package dev.foxikle.looper;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class Looper extends JavaPlugin {

    private final Map<UUID, List<LoopRunnable>> loops = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("loop").setExecutor(new CommandHandler(this));
        getCommand("loop").setTabCompleter(new CommandHandler(this));
        getCommand("cancelloop").setExecutor(new CancelLoopCommand(this));
        getCommand("cancelloop").setTabCompleter(new CancelLoopCommand(this));
        getCommand("cancelallloops").setExecutor(new CancelLoopCommand(this));
        getCommand("cancelallloops").setTabCompleter(new CancelLoopCommand(this));
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    public Map<UUID, List<LoopRunnable>> getLoops() {
        return loops;
    }
}
