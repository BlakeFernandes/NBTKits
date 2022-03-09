package me.blake.kits.commands;

import me.blake.kits.Kits;
import me.blake.kits.utils.StringUtils;
import me.lucko.helper.command.context.CommandContext;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.text3.Text;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCreateCommand extends AbstractCommand {

    public KitCreateCommand(Kits consumer) {
        super(consumer, "nbtkits.create", "nkitcreate");
    }

    @Override
    void handleCommand(CommandContext<CommandSender> handler) {
        Player player = (Player) handler.sender();

        if(handler.args().size() < 1) {
            StringUtils.send(player, Text.colorize("&7/nkitcreate <name>"));
            return;
        }

        this.terminableConsumer.getKitsManager().addKit(player.getInventory().getContents(), handler.args().get(0));

        StringUtils.send(player, this.terminableConsumer.getMessage("created_kit"),
                new StringUtils.Placeholder("kit", handler.args().get(0)));
    }
}
