package net.aoissx.mc.paperslot.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.hover.content.Item;

public class Gui {
    
    private Inventory inv;
    private int bet;
    private String title = "§a§l[PaperSlot] §r§aSlot";
    // gui item
    private Material back = Material.GRAY_STAINED_GLASS_PANE;
    private Material normalFrame= Material.BLACK_STAINED_GLASS_PANE;
    private Material info = Material.PAPER;
    private Material start = Material.ENDER_EYE;
    private Material beforeStop = Material.LANTERN;
    private Material afterStop = Material.SOUL_LANTERN;
    private Material lineItem = Material.PINK_STAINED_GLASS_PANE;

    private Material beforeKirikae = Material.IRON_HORSE_ARMOR;
    private Material afterKirikae = Material.GOLDEN_HORSE_ARMOR;

    // button
    private int showBet = 0; // 掛け金表示
    private int startButton = 47; // startボタン
    private int[] stop_button = new int[]{48,49,50}; // stopボタン
    private int[][] slot = new int[][]{{12,13,14},{21,22,23},{30,31,32}}; // slot
    private int[][] col = new int[][]{{11,15},{20,24},{29,33}}; // slotの列
    private int[] kirikae = new int[]{10,19,28}; // kirikaeボタン
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
    public Inventory getInventory(){
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
        
        // frame
        setFrame();
        // line
        setLine(1);
        
        // start button
        showStartButton();
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
        ItemMeta meta = bet.getItemMeta();
        
        List<Component> loreComponent = new ArrayList<Component>();
        Component betLore = Config.simpleText("掛け金: " + this.bet);
        loreComponent.add(betLore);

        meta.lore(loreComponent);
        bet.setItemMeta(meta);
        inv.setItem(showBet, bet);
    }

    /*
     * 筐体情報を更新する
     */
    public void updateInfo(int bet){
        this.bet = bet;
        showInfo();
    }

    /*
     * Frameを表示する
     */
    private void setFrame(){
        ItemStack frame = setItemName(normalFrame, " ");
        for(int i=2;i<7;i++){
            for(int j=0;j<43;j+=9){
                int index = i+j;
                if(index == 12 || index == 13 || index == 14
                || index == 21 || index == 22 || index == 23
                || index == 30 || index == 31 || index == 32){
                    continue;
                }
                inv.setItem(index, frame);
            }
        }
    }

    /*
     * startボタンを表示する
     */
    private void showStartButton(){
        ItemStack start = setItemName(this.start, "Start");
        inv.setItem(this.startButton, start);
    }

    /*
     * stopボタンを表示する
     */
    public void setStopButton(){
        ItemStack stop = setItemName(this.beforeStop, "Stop");
        for(int i=0;i<3;i++){
            inv.setItem(this.stop_button[i], stop);
        }
    }

    /*
     * stopボタンを更新する
     * slot: 0~2
     */
    public void updateStopButton(int slot){
        ItemStack stop = setItemName(this.afterStop, "Stop");
        inv.setItem(this.stop_button[slot], stop);
    }

    /*
     * slot retu
     * line: 0~2
     */
    public void setLine(int line){
        ItemStack lineItem = setItemName(this.lineItem, " ");
        for(int i=0;i<2;i++){
            inv.setItem(this.col[line][i], lineItem);
        }
    }

    /*
     * reload gui
     */
    public void reloadGui(){
        inv.clear();
        createGui();
    }

    /*
     * kirikaeボタンを表示する
     * line: 0,2
     */
    public void onKirikae(int line){
        ItemStack kirikae = setItemName(this.afterKirikae, " ");
        inv.setItem(this.kirikae[line], kirikae);
        setLine(line);
    }

    /*
     * kirikaeボタンを非表示にする
     * line: 0,2
     */
    public void offKirikae(int line){
        ItemStack kirikae = setItemName(this.beforeKirikae, " ");
        inv.setItem(this.kirikae[line], kirikae);
        ItemStack lineItem = setItemName(this.lineItem, " ");
        for(int i=0;i<2;i++){
            inv.setItem(this.col[line][i], lineItem);
        }
    }
}
