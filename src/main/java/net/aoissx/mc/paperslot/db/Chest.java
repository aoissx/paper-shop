package net.aoissx.mc.paperslot.db;

public class Chest {
    private int chestId;
    private int x;
    private int y;
    private int z;
    private String worldName;
    private int bet;

    public Chest(int chestId, int x, int y, int z, String worldName, int bet){
        this.chestId = chestId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
        this.bet = bet;
    }
    public Chest(){

    }

    public int getChestId(){
        return this.chestId;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getZ(){
        return this.z;
    }

    public String getWorldName(){
        return this.worldName;
    }

    public int getBet(){
        return this.bet;
    }

    public void setChestId(int chestId){
        this.chestId = chestId;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setZ(int z){
        this.z = z;
    }

    public void setWorldName(String worldName){
        this.worldName = worldName;
    }

    public void setBet(int bet){
        this.bet = bet;
    }

}