package dev.foxikle.looper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CancelAllLoops implements TabExecutor {
    private final Looper plugin;

    public CancelAllLoops(Looper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!label.equalsIgnoreCase("cancelallloops")) return false;
        if (!sender.hasPermission("looper.cancel.all")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
            return true;
        }

        plugin.getLoops().forEach((uuid, loopRunnables) -> {
            new ArrayList<>(loopRunnables).forEach(BukkitRunnable::cancel);
        });
        plugin.getLoops().clear();
        sender.sendMessage(ChatColor.GREEN + "All loops were successfully canceled.");
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return new ArrayList<>(); // null to recommand online players
    }
}
