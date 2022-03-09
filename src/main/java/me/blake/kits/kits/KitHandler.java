package me.blake.kits.kits;

import lombok.AllArgsConstructor;
import me.blake.kits.storage.IStorage;
import me.blake.kits.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@AllArgsConstructor
public class KitHandler {

    private final HashMap<String, Kit> kitItems = new HashMap<>();

    private final KitsManager manager;
    private final IStorage storage;

    public void loadKits() {
        kitItems.clear();
        Map<String, Kit> kits = storage.getKits();
        kitItems.putAll(kits);
        Bukkit.getLogger().info(String.format("[NKits] Loaded %s kits", kitItems.size()));
    }

    public void giveKit(CommandSender sender, Player target, String kit) {
        Optional<Kit> kitOptional = getKit(kit);

        if (kitOptional.isEmpty()) {
            StringUtils.send(sender, manager.getMessage("invalid_kit"), new StringUtils.Placeholder("kit", kit));
            return;
        }

        if (target == null || !target.isOnline()) {
            StringUtils.send(sender, manager.getMessage("invalid_player"));
            return;
        }

        kitOptional.get().giveKit(target);

        StringUtils.send(target,
                manager.getMessage("received_kit"),
                new StringUtils.Placeholder("kit", kit));
        StringUtils.send(sender,
                manager.getMessage("given_kit"),
                new StringUtils.Placeholder("player", target.getName()),
                new StringUtils.Placeholder("kit", kit));
    }



    public Optional<Kit> createKit(CommandSender sender, String kitName, ItemStack[] items, long cooldown) {
        Optional<Kit> kitOptional = getKit(kitName);

        if (kitOptional.isPresent()) {
            StringUtils.send(sender, manager.getMessage("kit_exists"));
            return Optional.empty();
        }

        Kit kit = Kit.create(items, cooldown);

        this.kitItems.put(kitName, kit);
        this.storage.addKit(kitName, kit);

        StringUtils.send(sender, manager.getMessage("kit_created"), new StringUtils.Placeholder("kit", kitName));

        return Optional.of(kit);
    }

    public Kit updateKit(CommandSender sender, String kitName, ItemStack[] items, long cooldown) {
        Optional<Kit> kitOptional = getKit(kitName);

        if (kitOptional.isPresent()) {
            this.kitItems.remove(kitName);
            this.storage.removeKit(kitName);
        }

        return createKit(sender, kitName, items, cooldown).get();
    }

    public void removeKit(CommandSender sender, String kitName) {
        this.kitItems.remove(kitName);
        this.storage.removeKit(kitName);
    }

    public Optional<Kit> getKit(String kit) {
        return Optional.ofNullable(this.kitItems.get(kit));
    }
}
