package com.eripe14.backups.backup.menu;

import com.eripe14.backups.adventure.Legacy;
import com.eripe14.backups.backup.Backup;
import com.eripe14.backups.backup.BackupDetails;
import com.eripe14.backups.backup.BackupService;
import com.eripe14.backups.config.MessageConfig;
import com.eripe14.backups.config.PluginConfig;
import com.eripe14.backups.inventory.InventoryQueueService;
import com.eripe14.backups.inventory.ItemTransformer;
import com.eripe14.backups.shared.Formatter;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BackupManageMenu {

    private final PluginConfig config;
    private final MessageConfig messageConfig;
    private final BackupService backupService;
    private final InventoryQueueService inventoryQueueService;
    private final BackupInventoryContentMenu backupInventoryContentMenu;
    private final BackupEnderChestContentMenu backupEnderChestContentMenu;
    private final BackupArmorContentMenu backupArmorContentMenu;

    public void openInventory(Player player, Backup backup) {
        Gui gui = Gui.gui()
                .title(Legacy.title(this.config.backupManageTitle))
                .rows(this.config.backupManageRows)
                .disableAllInteractions()
                .create();

        BackupDetails backupDetails = backup.getBackupDetails();

        Formatter formatter = new Formatter();
        formatter.register("{uuid}", backup.getPlayerUniqueId());
        formatter.register("{killer}", backupDetails.getKillerName());
        formatter.register("{reason}", backupDetails.getDeathReason());
        formatter.register("{experience}", backupDetails.getExperience());
        formatter.register("{last_granter}", backupDetails.getGranterName());
        formatter.register("{ping}", backupDetails.getPing());
        formatter.register("{created_at}", this.formatInstant(backupDetails.getCreatedAt()));

        if (this.config.fillEmptySlots) {
            gui.getFiller().fill(ItemTransformer.transform(this.config.backupManageFiller, formatter, event -> {}));
        }

        this.setItem(
                gui,
                this.config.backupManageInventorySlot,
                ItemTransformer.transform(this.config.backupManageInventoryItem.clone(), formatter, event -> {
                    this.backupInventoryContentMenu.openInventory(player, backup);
                })
        );

        this.setItem(
                gui,
                this.config.backupManageEnderChestSlot,
                ItemTransformer.transform(this.config.backupManageEnderChestItem, formatter, event -> {
                    this.backupEnderChestContentMenu.openInventory(player, backup);
                })
        );

        this.setItem(
                gui,
                this.config.backupManageArmorSlot,
                ItemTransformer.transform(this.config.backupManageArmorItem, formatter, event -> {
                    this.backupArmorContentMenu.openInventory(player, backup);
                })
        );

        this.setItem(
                gui,
                this.config.backupInfoSlot,
                ItemTransformer.transform(this.config.backupInfoItem.clone(), formatter, event -> {})
        );

        this.setItem(
                gui,
                this.config.backupManageGoBackSlot,
                ItemTransformer.transform(this.config.goBackItem, formatter, event -> {
                    this.inventoryQueueService.openLatest(player);
                })
        );

        this.setItem(
                gui,
                this.config.backupManageGrantSlot,
                ItemTransformer.transform(this.config.grantBackupItem, formatter, event -> {
                    if (backup.getBackupDetails().isGranted()) {
                        this.messageConfig.backupAlreadyGranted.send(player);
                        return;
                    }

                    player.closeInventory();
                    this.inventoryQueueService.reset(player);
                    this.backupService.grantBackup(player, backup);
                    this.messageConfig.grantedBackup.send(player);
                })
        );

        gui.open(player);
        this.inventoryQueueService.addGui(player, gui);
    }

    private String formatInstant(Instant instant) {
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern(this.config.timeFormat).format(zonedDateTime);
    }

    private void setItem(Gui gui, int slot, GuiItem guiItem) {
        gui.setItem(slot, guiItem);
    }

}