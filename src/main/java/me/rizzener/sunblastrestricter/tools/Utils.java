package me.rizzener.sunblastrestricter.tools;

import me.rizzener.sunblastrestricter.SunBlastRestricter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static FileConfiguration config;
    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("config.yml"));
    }
    public static boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }
    public static String getString(String path) {
        return getConfig().getString(path);
    }
    public static String getMessage(String s) {
        String prefix = getConfig().getString(colorizeHex("messages.prefix"));
        String message = getConfig().getString(s);
        return colorizeHex(prefix + message);
    }
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F0-9]{6})|<##([a-fA-F0-9]{6})>");
    private static final String COLOR_CHAR = "\u00A7";

    public static String colorizeHex(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            String replaceSharp = group.replace('#', 'x');
            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder(COLOR_CHAR + "x");
            for (char c : ch)
                builder.append(COLOR_CHAR).append(c);
            matcher.appendReplacement(buffer, builder.toString());
        }
        matcher.appendTail(buffer);
        String coloredMessage = buffer.toString();
        coloredMessage = coloredMessage.replace("&", COLOR_CHAR);
        return coloredMessage;
    }
    public static void onEnableMessage() {
        Bukkit.getConsoleSender().sendMessage(colorizeHex(""));
        Bukkit.getConsoleSender().sendMessage(colorizeHex("&6&l┏ &fПлагин: &eSunBlastPvPFinder &8| &fВерсия: &e" + SunBlastRestricter.getPlugin(SunBlastRestricter.class).getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(colorizeHex("&6&l⎪ &fПоследнее обновление плагина: &e22.06.2024"));
        Bukkit.getConsoleSender().sendMessage(colorizeHex("&6&l┗ &fСоздатель плагина: &eRizzener (https://t.me/SunBlast_support)"));
        Bukkit.getConsoleSender().sendMessage(colorizeHex(""));
    }
    public static String format(int time) {
        int days = time / 86400;
        int hours = time % 86400 / 3600;
        int minutes = time % 3600 / 60;
        int seconds = time % 60;
        StringBuilder builder = new StringBuilder();
        if (days > 0) {
            builder.append(getString("time.days").replace("%size%", "" + days)).append(" ");
        }

        if (hours > 0) {
            builder.append(getString("time.hours").replace("%size%", "" + hours)).append(" ");
        }

        if (minutes > 0) {
            builder.append(getString("time.minutes").replace("%size%", "" + minutes)).append(" ");
        }

        if (seconds > 0) {
            builder.append(getString("time.seconds").replace("%size%", "" + seconds)).append(" ");
        }

        String format;
        return !(format = builder.toString().trim()).isEmpty() ? format : getString("time.now");
    }

    public static void sendMessage(CommandSender player, String text) {
        String[] var2 = text.split(";");
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String line = var2[var4];
            line = line.replace("<player>", player.getName());
            if (line.startsWith("title:")) {
                if (player instanceof Player) {
                    sendTitle((Player) player, line.split("title:")[1]);
                }
            } else if (line.startsWith("actionbar:")) {
                if (player instanceof Player) {
                    sendActionBar((Player) player, line.split("actionbar:")[1]);
                }
            } else {
                player.sendMessage(colorizeHex(line));
            }
        }
    }
    public static void sendTitle(Player player, String text, int... times) {
        int fadein = times.length > 0 ? times[0] : 10;
        int stay = times.length > 1 ? times[1] : 70;
        int fadeout = times.length > 2 ? times[2] : 20;

        text = ChatColor.translateAlternateColorCodes('&', text);
        String[] args = text.split("%nl%");
        String title = args.length > 0 ? args[0] : "";
        String subtitle = args.length > 1 ? args[1] : "";

        player.sendTitle(title, subtitle, fadein, stay, fadeout);
    }
    public static void reloadConfig() {
        config = Config.getFile("config.yml");
    }

    public static void sendActionBar(Player player, String s) {
        String message = colorizeHex(s);
        player.sendActionBar(message);
    }
}
