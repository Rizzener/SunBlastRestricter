package me.rizzener.sunblastrestricter.tools;

import java.io.File;
import java.io.IOException;

import me.rizzener.sunblastrestricter.SunBlastRestricter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
    public static FileConfiguration getFile(String s) {
        File file = new File(SunBlastRestricter.getInstance().getDataFolder(), s);
        if (SunBlastRestricter.getInstance().getResource(s) == null) {
            return save(YamlConfiguration.loadConfiguration(file), s);
        }
        else {
            if (!file.exists()) {
                SunBlastRestricter.getInstance().saveResource(s, false);
            }
            return YamlConfiguration.loadConfiguration(file);
        }
    }
    public static FileConfiguration save(FileConfiguration fileConfiguration, String s) {
        try {
            fileConfiguration.save(new File(SunBlastRestricter.getInstance().getDataFolder(), s));
        }
        catch (IOException var3) {
            var3.printStackTrace();
        }
        return fileConfiguration;
    }
}
