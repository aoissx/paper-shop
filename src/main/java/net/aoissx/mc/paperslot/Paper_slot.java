package net.aoissx.mc.paperslot;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import net.aoissx.mc.paperslot.commands.Slot;
import net.aoissx.mc.paperslot.db.Init;
import net.aoissx.mc.paperslot.events.BlockClickEvent;
import net.aoissx.mc.paperslot.utils.Config;
import java.util.Objects;

public final class Paper_slot extends JavaPlugin {
    private static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        // config
        saveDefaultConfig();
        reload();
        
        // database
        Init init = new Init();
        init.init(); // init database

        // コマンド
        Objects.requireNonNull(getCommand(Config.CMD)).setExecutor(new Slot());

        // イベント
        getServer().getPluginManager().registerEvents(new BlockClickEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Paper_slot getInstance() {
        return getPlugin(Paper_slot.class);
    }

    /*
     * config reload
     */
    public void reload(){
        reloadConfig();
        Config.bet = getConfig().getInt("bet");
    }
}
