package com.luythen.github.bingo;

import com.luythen.github.bingo.game.Game;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BingoCommand implements CommandExecutor {

    Bingo plugin;

    public BingoCommand (Bingo plugin) {
        this.plugin = plugin;
        plugin.getCommand("bingo").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only for players");
        }

        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("create") && p.isOp()) {
            try {
                int matchPlayerSize = Integer.parseInt(args[1]);
                Game.createNewBingoMatch(matchPlayerSize);
            } catch (NumberFormatException e) {
                p.sendMessage("Bingo >> you must specify match size");
            }
        } else if (args[0].equalsIgnoreCase("join") && p.isOp()) {
            try {
                UUID matchID = UUID.fromString(args[1]);
                Game.JoinBingoMatch(matchID, p);
            } catch (Exception e) {
                p.sendMessage("Bingo >> match dosen't exits");
            }
        } else {
            p.sendMessage("Hello");
        }

        return true;
    }
}
