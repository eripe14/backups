package com.eripe14.backups.command;

import cc.dreamcode.command.CommandBase;
import cc.dreamcode.command.annotation.Command;
import cc.dreamcode.command.annotation.Executor;
import cc.dreamcode.command.annotation.Permission;
import com.eripe14.backups.backup.menu.ReceiveBackupMenu;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@Command(name = "odbierz-backup")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ReceiveBackupCommand implements CommandBase {

    private final ReceiveBackupMenu receiveBackupMenu;

    @Permission("backups.receive")
    @Executor(description = "Otwiera menu odbierania backupów.")
    void menu(Player player) {
        this.receiveBackupMenu.openInventory(player);
    }

}