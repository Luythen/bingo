package com.luythen.github.bingo.random;

import org.bukkit.inventory.ItemStack;

public class Item {

    private ItemStack itemStack;
    private String url;

    public Item (String url, ItemStack itemStack) {
        this.itemStack = itemStack;
        this.url = url;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getUrl() {
        return url;
    }
}
