package me.blake.kits.gui;

import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;

public class KitGui extends PaginatedGui {
    public KitGui(Function<PaginatedGui, List<Item>> content, Player player, PaginatedGuiBuilder model) {
        super(content, player, model);
    }
}
