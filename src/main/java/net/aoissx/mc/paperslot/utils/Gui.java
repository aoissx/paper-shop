package net.aoissx.mc.paperslot.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gui {
    
    private Inventory inv;
    private int bet;
    private String title = "§a§l[PaperSlot] §r§aSlot";
    // gui item
    private Material back = Material.GRAY_STAINED_GLASS_PANE;
    private Material info = Material.PAPER;

    // button
    private int showBet = 0; // 掛け金表示

    /*
     * コンストラクタ
     */
    public Gui(int bet){
        inv = Bukkit.createInventory(null, 9*6, this.title);
        this.bet = bet;
    }
    public Gui(Inventory inv, int bet){
        this.inv = inv;
        this.bet = bet;
    }
    public Gui(){
        inv = Bukkit.createInventory(null, 9*6, this.title);
    }
    public Gui(Inventory inv){
        this.inv = inv;
    }

    /*
     * getter
     */
    public Inventory getInv(){
        return inv;
    }

    /*
     * GUIの作成
     */
    public void createGui(){
        // all gray glass
        ItemStack glass = setItemName(back, " ");
        for(int i=0;i<54;i++){
            inv.setItem(i, glass);
        }

        // info
        showInfo();
    }

    /*
     * noname item
     */
    private ItemStack setItemName(Material material, String name){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Config.simpleText(name));
        item.setItemMeta(meta);
        return item;
    }

    /*
     * 筐体情報を表示する
     */
    public void showInfo(){
        ItemStack bet = setItemName(info, "§a§l[Info]§r§a");
        inv.setItem(showBet, bet);
    }
}
