package me.blake.kits;

import lombok.Getter;
import me.blake.kits.commands.KitCommand;
import me.blake.kits.commands.KitCreateCommand;
import me.blake.kits.kits.KitsManager;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.blake.kits.utils.FileManager;

@Getter
public class Kits extends ExtendedJavaPlugin {

    private FileManager fileManager;
    private KitsManager kitsManager;

    @Override
    public void enable() {
        this.fileManager = new FileManager(this);
        this.kitsManager = new KitsManager(this);

        registerCommands();
    }

    @Override
    public void disable() {
    }

    public void registerCommands() {
        new KitCommand(this).register();
        new KitCreateCommand(this).register();
    }
}
