package com.luythen.github.bingo.game;

import org.bukkit.inventory.ItemStack;

import java.awt.image.BufferedImage;

public class Item {

    private ItemStack itemStack;
    private BufferedImage image;

    public Item (BufferedImage image, ItemStack itemStack) {
        this.itemStack = itemStack;
        this.image = image;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public BufferedImage getImage() {
        return image;
    }
}
