package dev.foxikle.looper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CancelLoopCommand implements TabExecutor {
    private final Looper plugin;

    public CancelLoopCommand(Looper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!label.equalsIgnoreCase("cancelloop")) return false;

        Player player;
        if (args.length >= 1 && sender.hasPermission("looper.cancel")) {
            if (Bukkit.getPlayerExact(args[0]) != null) {
                player = Bukkit.getPlayerExact(args[0]);
            } else {
                sender.sendMessage("This player does not exist!");
                return false;
            }
        } else {
            if (sender instanceof Player) {
                player = (Player) sender;
            } else {
                sender.sendMessage("You can't have loops!");
                return false;
            }
        }

        if (plugin.getLoops().containsKey(player.getUniqueId())) {
            new ArrayList<>(plugin.getLoops().get(player.getUniqueId())).forEach(loopRunnable -> {
                plugin.getLoops().get(player.getUniqueId()).remove(loopRunnable);
                loopRunnable.cancel();
            });
        }

        sender.sendMessage(ChatColor.GREEN + player.getName() + "'s loops were canceled.");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null; // null to recommand online players
    }
}
