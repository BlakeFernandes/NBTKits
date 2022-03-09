package me.blake.kits.commands;

import me.blake.kits.Kits;
import me.blake.kits.utils.StringUtils;
import me.lucko.helper.Commands;
import me.lucko.helper.command.context.CommandContext;
import me.lucko.helper.terminable.TerminableConsumer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand extends AbstractCommand {

    public KitCommand(Kits consumer) {
        super(consumer, "nbtkits.view", "nkit");
    }

    @Override
    public void handleCommand(CommandContext<CommandSender> handler) {
        Player player = (Player) handler.sender();
        Player target = player;

        if(handler.args().size() < 1) {
            StringUtils.send(player, this.terminableConsumer.getMessage("help"),
                    new StringUtils.Placeholder("kits", String.join(", ", this.terminableConsumer.getKitsManager().getKitItems().keySet())));
            return;
        }

        if (handler.args().size() > 1) target = Bukkit.getPlayer(handler.args().get(1));

        this.terminableConsumer.getKitsManager().giveKit(player, target, handler.args().get(0));
    }
}
