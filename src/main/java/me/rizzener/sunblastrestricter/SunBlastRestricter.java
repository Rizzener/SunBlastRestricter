package me.rizzener.sunblastrestricter;

import com.earth2me.essentials.Essentials;
import me.rizzener.sunblastrestricter.Commands.CommandReload;
import me.rizzener.sunblastrestricter.Listeners.PlayerListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SunBlastRestricter extends JavaPlugin {

    private static SunBlastRestricter instance;
    private Essentials essentials;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

        if (essentials == null) {
            getLogger().severe("Essentials plugin not found! Disabling SunBlastRestricter.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getCommand("restricter-reload").setExecutor(new CommandReload());
        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
    }

    public static SunBlastRestricter getInstance() {
        return instance;
    }

    public Essentials getEssentials() {
        return essentials;
    }
}
