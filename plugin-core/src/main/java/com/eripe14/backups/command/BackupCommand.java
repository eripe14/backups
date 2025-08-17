package com.eripe14.backups.command;

import cc.dreamcode.command.CommandBase;
import cc.dreamcode.command.annotation.*;
import cc.dreamcode.notice.bukkit.BukkitNotice;
import com.eripe14.backups.backup.menu.BackupMenu;
import com.eripe14.backups.backup.menu.FilterMode;
import com.eripe14.backups.config.MessageConfig;
import com.eripe14.backups.config.PluginConfig;
import cc.dreamcode.utilities.TimeUtil;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@Command(name = "backup")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BackupCommand implements CommandBase {

    private final PluginConfig pluginConfig;
    private final MessageConfig messageConfig;
    private final BackupMenu backupMenu;

    @Permission("backups.view")
    @Completion(arg = "targetName", value = "@all-players")
    @Executor(description = "Otwiera menu.")
    void menu(Player player, @Arg String targetName) {
        this.backupMenu.openInventory(player, targetName, FilterMode.NEWEST);
    }

    @Async
    @Permission("backups.reload")
    @Executor(path = "reload", description = "Przeladowuje konfiguracje.")
    BukkitNotice reload() {
        final long time = System.currentTimeMillis();

        try {
            this.messageConfig.load();
            this.pluginConfig.load();

            return this.messageConfig.reloaded
                    .with("time", TimeUtil.format(System.currentTimeMillis() - time));
        }
        catch (NullPointerException | OkaeriException exception) {
            exception.printStackTrace();

            return this.messageConfig.reloadError
                    .with("error", exception.getMessage());
        }
    }
}