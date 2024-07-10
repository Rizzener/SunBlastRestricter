package me.rizzener.sunblastrestricter.Listeners;

import com.earth2me.essentials.User;
import me.rizzener.sunblastrestricter.SunBlastRestricter;
import me.rizzener.sunblastrestricter.tools.Cooldown;
import me.rizzener.sunblastrestricter.tools.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerListeners implements Listener {
    private Cooldown cooldown = new Cooldown();

    @EventHandler(ignoreCancelled = true)
    public void onBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String key;
        if (!player.hasPermission("SunBlastRestricter.bypass")) {
            if (isInVanishMode(player) && Utils.getBoolean("settings.vanish.break-block.blocked")) {
                key = event.getPlayer().getName() + "_Vanish_BreakBlock";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.vanish.break-block.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            } else if (isInGodMode(player) && Utils.getBoolean("settings.god.break-block.blocked")) {
                key = event.getPlayer().getName() + "_God_BreakBlock";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.god.break-block.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            } else if (isInFlyMode(player) && Utils.getBoolean("settings.fly.break-block.blocked")) {
                key = event.getPlayer().getName() + "_Fly_BreakBlock";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.fly.break-block.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            }
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void onPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        String key;
        if (!player.hasPermission("SunBlastRestricter.bypass")) {
            if (isInVanishMode(player) && Utils.getBoolean("settings.vanish.pickup-items.blocked")) {
                key = event.getPlayer().getName() + "_Vanish_PickUpItem";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.vanish.pickup-items.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            } else if (isInGodMode(player) && Utils.getBoolean("settings.god.pickup-items.blocked")) {
                key = event.getPlayer().getName() + "_God_PickUpItem";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.god.pickup-items.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            } else if (isInFlyMode(player) && Utils.getBoolean("settings.fly.pickup-items.blocked")) {
                key = event.getPlayer().getName() + "_Fly_PickUpItem";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.fly.pickup-items.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            }
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        String key;
        if (!player.hasPermission("SunBlastRestricter.bypass")) {
            if (isInVanishMode(player) && Utils.getBoolean("settings.vanish.place-block.blocked")) {
                key = event.getPlayer().getName() + "_Vanish_BlockPlace";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.vanish.place-block.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            } else if (isInGodMode(player) && Utils.getBoolean("settings.god.place-block.blocked")) {
                key = event.getPlayer().getName() + "_God_BlockPlace";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.god.place-block.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            } else if (isInFlyMode(player) && Utils.getBoolean("settings.fly.place-block.blocked")) {
                key = event.getPlayer().getName() + "_Fly_BlockPlace";
                if (!cooldown.hasCooldown(key)) {
                    Utils.sendMessage(player, Utils.getMessage("settings.fly.place-block.message"));
                    cooldown.addCooldown(key, 3);
                }
                event.setCancelled(true);
            }
        }
    }
    private boolean isInVanishMode(Player player) {
        User playerEssntials = SunBlastRestricter.getInstance().getEssentials().getUser(player);
        if (playerEssntials.isVanished()) {
            return true;
        } else {
            return false;
        }
    }
    private boolean isInGodMode(Player player) {
        User playerEssntials = SunBlastRestricter.getInstance().getEssentials().getUser(player);
        if (playerEssntials.isGodModeEnabled()) {
            return true;
        } else {
            return false;
        }
    }
    private boolean isInFlyMode(Player player) {
        User playerEssntials = SunBlastRestricter.getInstance().getEssentials().getUser(player);
        if (playerEssntials.getBase().getAllowFlight()) {
            return true;
        } else {
            return false;
        }
    }
}
