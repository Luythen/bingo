package com.luythen.github.bingo.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public abstract class Game {

    private static ArrayList<BingoMatch> activeBingoMatchs = new ArrayList<>();
    private static ArrayList<Item> bingoItems;

    public static void createNewBingoMatch (int matchPlayerSize) {
        BingoMatch bingoMatch = new BingoMatch(matchPlayerSize);
        activeBingoMatchs.add(bingoMatch);

        broadcastJoinMessages(bingoMatch);
    }

    public static void JoinBingoMatch (UUID matchID, Player p) {
        BingoMatch bingoMatch = findMatchByID(matchID);
        if (bingoMatch.getMatchPlayerSize() > bingoMatch.getMatchPlayers().size()) {
            if (!bingoMatch.matchContainsPlayerByUUID(p.getUniqueId())) {
                BingoPlayer bingoPlayer = createNewBingoPlayer(p);
                bingoMatch.addPlayerToMatch(bingoPlayer);
                generate(bingoPlayer);
                p.sendMessage("Bingo >> You have joined a bingo match");
            } else {
                p.sendMessage("Bingo >> You have already join this match!");
            }
            broadcastJoinMessages(bingoMatch);
        } else {
            p.sendMessage("Bingo >> Match is already full!");
        }
    }

    public static void setBingoItems (ArrayList<Item> bingoItemsList) {
        bingoItems = bingoItemsList;
    }

    public static ArrayList<BingoMatch> getAllActiveMatches () {
        return activeBingoMatchs;
    }

    private static void generate (BingoPlayer p) {
        int itemGridX = 8;
        int itemGirdY = 8;

        int[] gridPointA = new int[]{0, 32, 64, 96};
        int gridPointB = 32;
        int girdPointC = 0;
        int gridPointD = 32;

        Random random = new Random();
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = random.nextInt(bingoItems.size());
                while (containsItem(p, bingoItems.get(num))) {
                    num = random.nextInt(bingoItems.size());
                }

                GridItem item = new GridItem(bingoItems.get(num), new int[]{gridPointA[j], gridPointB, girdPointC, gridPointD}, new int[]{itemGridX, itemGirdY});
                p.addGridItem(item);
                itemGridX+= 32;
                gridPointB += 32;
            }
            itemGridX = 8;
            itemGirdY+= 32;
            
            gridPointB = 32;
            girdPointC += 32;
            gridPointD += 32;
        }
    }

    private static boolean containsItem (BingoPlayer p, Item item) {
        for (GridItem gItem : p.getGridItems()) {
            if (gItem.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    private static BingoPlayer createNewBingoPlayer (Player p) {
        ArrayList<GridItem> gridItems = new ArrayList<>();
        BingoPlayer bingoPlayer = new BingoPlayer(p, gridItems);
        return bingoPlayer;
    }

    private static BingoMatch findMatchByID (UUID matchID) {
        for (BingoMatch bingoMatch : activeBingoMatchs) {
            if (bingoMatch.getMatchID().equals(matchID)) return bingoMatch;
        }

        return null;
    }

    private static void broadcastJoinMessages (BingoMatch bingoMatch) {
        TextComponent joinMatchTextComponent = new TextComponent("Bingo >> a new match have been created (" + bingoMatch.getMatchPlayers().size() + "/" + bingoMatch.getMatchPlayerSize() + ") [Click here]");
        joinMatchTextComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to join bingo match")));
        joinMatchTextComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bingo join " + bingoMatch.getMatchID()));

        Bukkit.spigot().broadcast(joinMatchTextComponent);
    }
    
}
