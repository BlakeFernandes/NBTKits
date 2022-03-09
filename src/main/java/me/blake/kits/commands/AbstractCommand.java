package me.blake.kits.commands;

import me.blake.kits.Kits;
import me.lucko.helper.Commands;
import me.lucko.helper.command.context.CommandContext;
import me.lucko.helper.terminable.TerminableConsumer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand {

    protected final Kits terminableConsumer;
    private final String[] names;
    private final String permission;

    protected AbstractCommand(Kits consumer, String permission, String... names) {
        this.terminableConsumer = consumer;
        this.permission = permission;
        this.names = names;
    }

    abstract void handleCommand(CommandContext<CommandSender> handler);

    public void register() {
        Commands.create()
                .assertPermission(permission)
                .handler(this::handleCommand)
                .registerAndBind(terminableConsumer, names);
    }
}
