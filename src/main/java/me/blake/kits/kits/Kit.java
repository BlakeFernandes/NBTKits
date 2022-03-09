package me.blake.kits.kits;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.blake.kits.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Kit {
    private List<ItemStack> items;
    private long cooldown;

    public List<String> getSerializedItems() {
        List<String> serializedItemsList = new ArrayList<>();

        for (ItemStack item : items) {
            if (item == null || item.getType() == Material.AIR)
                continue;
            serializedItemsList.add(Utils.serialize(item));
        }

        return serializedItemsList;
    }

    public void giveKit(Player player) {
        for (ItemStack itemStack : items) {
            int playerInventorySize = Utils.getEmptySlots(player);

            if (playerInventorySize <= 0) {
                Bukkit.getWorld(player.getWorld().getName()).dropItem(player.getLocation(), itemStack);
            } else {
                player.getInventory().addItem(itemStack);
            }
        }

        player.updateInventory();
    }

    public static Kit of(ItemStack[] items, long cooldown) {
        return new Kit(List.of(items), cooldown);
    }
}
