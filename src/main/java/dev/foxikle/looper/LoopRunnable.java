package dev.foxikle.looper;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LoopRunnable extends BukkitRunnable {

    public int iterations;
    public Player player;
    public String command;

    public LoopRunnable(int iterations, Player player, String command) {
        this.iterations = iterations;
        this.player = player;
        this.command = command;
    }

    @Override
    public void run() {
        if(player.isOnline()) {
            if(iterations >= 0) {
                iterations--;
                //Bukkit.dispatchCommand(player, command);
                player.performCommand(command);
            } else this.cancel();
        } else this.cancel();
    }
}
