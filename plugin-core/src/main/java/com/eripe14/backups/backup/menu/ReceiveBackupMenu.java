package com.eripe14.backups.backup.menu;

import cc.dreamcode.utilities.bukkit.InventoryUtil;
import com.eripe14.backups.adventure.Legacy;
import com.eripe14.backups.backup.Backup;
import com.eripe14.backups.backup.BackupService;
import com.eripe14.backups.config.MessageConfig;
import com.eripe14.backups.config.PluginConfig;
import com.eripe14.backups.inventory.ItemTransformer;
import com.eripe14.backups.shared.Formatter;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ReceiveBackupMenu {

    private final BackupService backupService;
    private final PluginConfig pluginConfig;
    private final MessageConfig messageConfig;

    public void openInventory(Player player) {
        Gui gui = Gui.gui()
                .title(Legacy.title(this.pluginConfig.receiveBackupsTitle))
                .rows(this.pluginConfig.receiveBackupsRows)
                .disableAllInteractions()
                .create();

        Formatter formatter = new Formatter();

        this.pluginConfig.receiveBackupsStructure.forEach((slot, item) -> {
            this.setItem(gui, slot, ItemTransformer.transform(item, formatter, event -> {
            }));
        });

        List<Backup> backups = this.backupService.getGrantedBackupsSync(player.getName());
        for (Backup backup : backups) {
            gui.addItem(ItemTransformer.transform(this.pluginConfig.receiveBackupItem, formatter, event -> {
                if (player.getInventory().firstEmpty() != 0) {
                    this.messageConfig.emptyInventoryRequired.send(player);
                    return;
                }

                this.backupService.receiveBackup(player, backup);
                this.messageConfig.receivedBackup.send(player);
                player.closeInventory();
            }));
        }

        gui.open(player);
    }

    private void setItem(Gui gui, int slot, GuiItem guiItem) {
        gui.setItem(slot, guiItem);
    }

}