package net.isksss.mc.paperslot.db;

public class Base {
    private final String path = "jdbc:sqlite:plugins/paper-slot/slot.db";
    public Base(){
    }

    public String getPath(){
        return path;
    }

    
}
