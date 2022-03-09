package me.blake.kits.kits;

import lombok.Getter;
import me.blake.kits.Kits;
import me.blake.kits.storage.IStorage;
import me.blake.kits.storage.YamlStorage;
import me.blake.kits.utils.StringUtils;
import me.blake.kits.utils.Utils;
import me.lucko.helper.text.Text;
import me.blake.kits.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class KitsManager {

    private final Kits kits;
    private final KitHandler handler;

    private HashMap<String, String> messages;

    private final FileManager.Config config;
    private final FileManager.Config messageConfig;
    private final FileManager.Config kitsConfig;

    public KitsManager(Kits kits) {
        this.kits = kits;

        this.config = kits.getFileManager().getConfig("config.yml").copyDefaults(true).save();
        this.kitsConfig = kits.getFileManager().getConfig("kits.yml").copyDefaults(true).save();
        this.messageConfig = kits.getFileManager().getConfig("messages.yml").copyDefaults(true).save();

        this.handler = new KitHandler(this, getStorage());
        this.handler.loadKits();

        this.loadMessages();
    }

    private IStorage getStorage() {
        String storageType = "YAML";

        switch (storageType.toUpperCase()) {
            case "YAML":
                return new YamlStorage();
        }

        Bukkit.getLogger().severe("[NKits] An invalid storage type was provided.");
        Bukkit.getLogger().severe("[NKits] Please specify an option of [YAML]");
        Bukkit.getLogger().severe("[NKits] Disabling...");
        Bukkit.getPluginManager().disablePlugin(this.kits);
        return null;
    }

    public String getMessage(String key) {
        return this.messages.getOrDefault(key, String.format("Couldn't get message %s", key));
    }

    public void loadMessages() {
        messages = new HashMap<>();
        for (String key : messageConfig.get().getConfigurationSection("messages").getKeys(false)) {
            messages.put(key, me.lucko.helper.text.Text.colorize(messageConfig.get().getString("messages." + key)));
        }
    }
}
