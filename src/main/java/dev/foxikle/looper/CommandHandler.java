package dev.foxikle.looper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private final Looper plugin;

    public CommandHandler(Looper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            if(args.length >= 3) {
                int iterations = Integer.parseInt(args[0]);
                int interval = Integer.parseInt(args[1]);
                List<String> commandList = new ArrayList<>(List.of(args));
                commandList.remove(0);
                commandList.remove(0);
                String cmd = String.join(" ", commandList);
                LoopRunnable runnable = new LoopRunnable(iterations, player, cmd);
                runnable.runTaskTimer(plugin, 0, interval);

                if(plugin.getLoops().containsKey(player.getUniqueId()) && plugin.getLoops().get(player.getUniqueId()) != null) {
                    plugin.getLoops().get(player.getUniqueId()).add(runnable);
                } else {
                    plugin.getLoops().put(player.getUniqueId(), new ArrayList<>(List.of(runnable)));
                }

                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Usage! '/loop <times> <interval> <command with arguments>'");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You cannot do this.");
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 1) {
            return List.of("Iterations");
        } else if (args.length == 2) {
            return List.of("Period (in Ticks)");
        } else if (args.length == 3) {
            return List.of("Command");
        }
        return new ArrayList<>();
    }
}
