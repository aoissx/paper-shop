package net.aoissx.mc.paperslot;

import org.bukkit.plugin.java.JavaPlugin;
import net.aoissx.mc.paperslot.commands.Slot;
import net.aoissx.mc.paperslot.db.Init;
import net.aoissx.mc.paperslot.utils.Config;
import java.util.Objects;

public final class Paper_slot extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        
        // database
        Init init = new Init();
        init.init(); // init database

        // コマンド
        Objects.requireNonNull(getCommand(Config.CMD)).setExecutor(new Slot());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Paper_slot getInstance() {
        return getPlugin(Paper_slot.class);
    }
}
