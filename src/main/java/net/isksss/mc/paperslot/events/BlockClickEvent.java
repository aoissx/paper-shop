package net.isksss.mc.paperslot.events;

import net.isksss.mc.paperslot.utils.Config;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BlockClickEvent implements Listener {

    /*
     * ブロックをクリックしたときのイベント
     * 登録と削除と使用
     */
    @EventHandler
    public void onBlockClickEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Set<String> tags = player.getScoreboardTags();

        // 管理者行動
        if(player.isOp() && player.isSneaking()){
            //スニーク状態でのみ実行できる。

            if(tags.contains(Config.Tags.SLOT_ADD_TAG.toString())){
                //　登録モード

                //bet取得
                ItemStack offhand = player.getInventory().getItemInOffHand();
                int bet = 1;
                if(offhand.getType() != Material.STICK){
                    player.sendMessage(Config.error("オフハンドに棒を持ってください"));
                    return;
                }else{
                    ItemMeta meta = offhand.getItemMeta();
                    String betStr = Objects.requireNonNull(meta.displayName()).toString();
                    bet = Integer.parseInt(betStr);
                }
                Block block = event.getClickedBlock();
                if(block!=null){
                    // 登録
                    if(register(block,bet)){
                        //登録完了メッセージ
                        player.sendMessage(Config.text("登録できました。"));
                    }else{
                        player.sendMessage(Config.error("登録できませんでした。"));
                    }
                }
            }else if(tags.contains(Config.Tags.SLOT_REMOVE_TAG.toString())){
                // 削除モード
            }
        }
        else {// 一般ユーザ

        }
    }

    /*
    スロットを登録する処理。
     */
    private boolean register(Block block, int bet){
        Location loc = block.getLocation();


        return true;
    }


}
