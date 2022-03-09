package me.blake.kits.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Utils {

    public static int getEmptySlots(Player p) {
        PlayerInventory inventory = p.getInventory();
        ItemStack[] cont = inventory.getContents();
        int i = 0;
        for (ItemStack item : cont)
            if (item != null && item.getType() != Material.AIR) {
                i++;
            }
        return 36 - i;
    }

    public static String serialize(ItemStack is) {
        try {
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(bytesOut);
            out.writeObject(is);
            out.flush();
            out.close();
            return Base64Coder.encodeLines(bytesOut.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ItemStack deserialize(String base64) {
        try {
            byte[] data = Base64Coder.decodeLines(base64);
            ByteArrayInputStream bytesIn = new ByteArrayInputStream(data);
            BukkitObjectInputStream in = new BukkitObjectInputStream(bytesIn);
            ItemStack is = (ItemStack) in.readObject();
            in.close();
            return is;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }
}
