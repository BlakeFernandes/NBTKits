package me.blake.kits.storage;

import me.blake.kits.kits.Kit;
import org.bukkit.entity.Player;

import java.util.Map;

public interface IStorage {
    Map<String, Kit> getKits();
    void updatePlayerCooldown(Player player, String string, long timestamp);
    void addKit(String kitName, Kit kit);
    void updateKit(String kitName, Kit kit);
    void removeKit(String kitName);
}
