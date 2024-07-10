package me.rizzener.sunblastrestricter.tools;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {
    private Map<String, Long> cooldowns = new HashMap();


    public void addCooldown(String key, int seconds) {
        this.cooldowns.put(key, System.currentTimeMillis() + (long)(seconds * 1000));
    }

    public boolean hasCooldown(String key) {
        if (this.cooldowns.get(key) != null) {
            if ((Long)this.cooldowns.get(key) > System.currentTimeMillis()) {
                return true;
            }

            this.cooldowns.remove(key);
        }

        return false;
    }
}
