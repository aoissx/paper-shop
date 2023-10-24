package net.aoissx.mc.paperslot.db;

import org.bukkit.plugin.Plugin;

import net.aoissx.mc.paperslot.Paper_slot;

public class Base {
    private final String path = "jdbc:sqlite:plugins/paper-slot/slot.db";
    private Plugin plugin;
    public Base(){
        plugin = Paper_slot.getInstance();
    }

    public String getPath(){
        return path;
    }

    public Plugin getPlugin(){
        return plugin;
    }

}
