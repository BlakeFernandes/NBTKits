package me.blake.kits.storage;

import me.blake.kits.kits.Kit;
import org.bukkit.entity.Player;

import java.util.Map;

public class YamlStorage implements IStorage {

    @Override
    public Map<String, Kit> getKits() {
        return null;
    }

    @Override
    public void updatePlayerCooldown(Player player, String string, long timestamp) {

    }

    @Override
    public void addKit(String kitName, Kit kit) {

    }

    @Override
    public void updateKit(String kitName, Kit kit) {

    }

    @Override
    public void removeKit(String kitName) {

    }
}
