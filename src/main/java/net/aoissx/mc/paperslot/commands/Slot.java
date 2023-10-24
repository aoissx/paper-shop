package net.aoissx.mc.paperslot.commands;

import net.aoissx.mc.paperslot.utils.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class Slot implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)){
            sender.sendMessage(Config.text("Player only."));
            return false;
        }

        if (!(command.getName().equalsIgnoreCase(Config.CMD))){
            return false;
        }

        if(!p.isOp()){
            p.sendMessage(Config.text("Do not use this command."));
            return false;
        }

        if((args.length == 0)){
            p.sendMessage(Config.text("Args is not enough."));
            return false;
        }

        String subCmd = args[0];
        if(subCmd.equalsIgnoreCase(Config.SUB_CMD.ADD.toString())){
            // add command
            if(args.length >= 2){
                add(p);
                Config.bet = Integer.parseInt(args[1]);
                p.sendMessage(Config.text("Set bet: "+Config.bet));
            }else{
                p.sendMessage(Config.error("Not enough Args"));
                return false;
            }

        }else if(subCmd.equalsIgnoreCase(Config.SUB_CMD.REMOVE.toString())){
            // remove command
            remove(p);
        }

        return true;
    }

    private void add(Player p){
        Set<String> tags = p.getScoreboardTags();
        Component msg;
        String tag = Config.Tags.SLOT_ADD_TAG.toString();
        if(tags.contains(tag)){
            msg = Config.text("Disable:slot add mode");
            p.removeScoreboardTag(tag);
        }else{
            msg = Config.text("Enable:slot add mode");
            p.addScoreboardTag(tag);
        }
        p.sendMessage(msg);
    }
    private void remove(Player p){
        Set<String> tags = p.getScoreboardTags();
        Component msg;
        String tag = Config.Tags.SLOT_REMOVE_TAG.toString();
        if(tags.contains(tag)){
            msg = Config.text("Disable:slot remove mode");
            p.removeScoreboardTag(tag);
        }else{
            msg = Config.text("Enable:slot remove mode");
            p.addScoreboardTag(tag);
        }
        p.sendMessage(msg);
    }


}
