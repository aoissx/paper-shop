package net.aoissx.mc.paperslot.events;

import org.bukkit.Location;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import net.aoissx.mc.paperslot.db.Chest;
import net.aoissx.mc.paperslot.db.ChestDao;
import net.aoissx.mc.paperslot.utils.Config;
import java.util.List;
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
                int bet = Config.bet;

                Block block = event.getClickedBlock();
                if(block!=null){
                    // 登録
                    boolean regist = register(block,bet);
                    if(regist){
                        //登録完了メッセージ
                        player.sendMessage(Config.text("登録できました。BET: "+bet+" level"));

                        // blockのLocにエフェクトを表示
                        Location loc = block.getLocation();
                        loc.getWorld().spawnParticle(org.bukkit.Particle.VILLAGER_HAPPY, loc, 10);
                    }else{
                        player.sendMessage(Config.error("登録できませんでした。"));
                    }
                }
            }else if(tags.contains(Config.Tags.SLOT_REMOVE_TAG.toString())){
                // 削除モード

                Block block = event.getClickedBlock();
                if(block!=null){
                    // 削除
                    boolean delete = delete(block);
                    if(delete){
                        //削除完了メッセージ
                        player.sendMessage(Config.text("削除できました。"));
                        // blockのLocにエフェクトを表示
                        Location loc = block.getLocation();
                        loc.getWorld().spawnParticle(org.bukkit.Particle.VILLAGER_HAPPY, loc, 10);
                    }else{
                        player.sendMessage(Config.error("削除できませんでした。"));
                    }
                }
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

        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        String worldName = loc.getWorld().getName();
        Chest chest = new Chest(0,x,y,z,worldName,bet);

        // すでに存在するか確認
        ChestDao dao = new ChestDao();
        Chest searchChest = new Chest();
        searchChest.setX(x);
        searchChest.setY(y);
        searchChest.setZ(z);
        searchChest.setWorldName(worldName);
        if(dao.searchByLoc(searchChest).size() > 0){
            return false;
        }

        // 登録
        dao.insert(chest);

        return true;
    }

    /*
     * スロットを削除する処理。
     */
    public boolean delete(Block b){
        ChestDao dao = new ChestDao();
        Chest chest = new Chest();
        chest.setX(b.getX());
        chest.setY(b.getY());
        chest.setZ(b.getZ());
        chest.setWorldName(b.getWorld().getName());
        
        // chestが存在するか確認
        List<Chest> chests = dao.searchByLoc(chest);
        if(chests.size() == 0){
            return false;
        }

        chest = chests.get(0);

        // 削除
        dao.delete(chest);

        return true;
    }


}
