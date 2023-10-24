package net.aoissx.mc.paperslot.db;

import net.aoissx.mc.paperslot.Paper_slot;

public class Base {
    private final String path = "jdbc:sqlite:plugins/paper-slot/slot.db";
    private Paper_slot plugin;
    public Base(){
        plugin = Paper_slot.getInstance();
    }

    public String getPath(){
        return path;
    }

    public Paper_slot getPlugin(){
        return plugin;
    }

}
