package com.eripe14.backups.backup.menu;

import com.eripe14.backups.adventure.Legacy;
import com.eripe14.backups.backup.Backup;
import com.eripe14.backups.backup.BackupDetails;
import com.eripe14.backups.backup.BackupService;
import com.eripe14.backups.config.PluginConfig;
import com.eripe14.backups.inventory.InventoryQueueService;
import com.eripe14.backups.inventory.ItemTransformer;
import com.eripe14.backups.shared.Formatter;
import dev.triumphteam.gui.components.GuiAction;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BackupMenu {

    private final PluginConfig config;
    private final InventoryQueueService inventoryQueueService;
    private final BackupService backupService;
    private final BackupManageMenu backupManageMenu;

    public void openInventory(Player player, String targetName, FilterMode filterMode) {
        List<Backup> backups = this.backupService.getBackups(targetName);
        Formatter formatter = new Formatter();
        formatter.register("{amount}", backups.size());

        PaginatedGui gui = Gui.paginated()
                .title(Legacy.title(formatter.format(this.config.backupsTitle)))
                .rows(this.config.backupsRows)
                .pageSize((this.config.backupsRows - 1) * 9)
                .disableAllInteractions()
                .create();

        this.setItem(
                gui,
                this.config.sortBackupsItemSlot,
                ItemTransformer.transform(
                        this.config.sortBackupsItem,
                        new Formatter(),
                        filterMode,
                        this.sortAction(gui, targetName, backups, filterMode)
                )
        );

        this.config.backupsStructure.forEach((slot, item) -> {
            this.setItem(gui, slot, ItemTransformer.transform(item, formatter, event -> {
            }));
        });

        this.setItem(gui, this.config.previousItemSlot, ItemTransformer.transform(this.config.previousPageItem, formatter, event -> {
            gui.previous();
        }));
        this.setItem(gui, this.config.forwardItemSlot, ItemTransformer.transform(this.config.forwardPageItem, formatter, event -> {
            gui.next();
        }));

        this.populateGui(gui, backups, filterMode);

        gui.open(player);
        this.inventoryQueueService.addGui(player, gui);
    }

    private GuiAction<InventoryClickEvent> sortAction(PaginatedGui gui, String target, List<Backup> backups, FilterMode filterMode) {
        return event -> {
            FilterMode newFilterMode = filterMode == FilterMode.NEWEST
                    ? FilterMode.OLDEST
                    : filterMode == FilterMode.OLDEST
                    ? FilterMode.GRANTED
                    : FilterMode.NEWEST;

            this.openInventory((Player) event.getWhoClicked(), target, newFilterMode);
        };
    }

    private void populateGui(PaginatedGui gui, List<Backup> backups, FilterMode filterMode) {
        gui.clearPageItems(true);
        List<Backup> sortedBackups = new java.util.ArrayList<>(backups);

        // Sort the backups based on the selected filter mode
        if (filterMode == FilterMode.OLDEST) {
            sortedBackups.sort(Comparator.comparing(backup -> backup.getBackupDetails().getCreatedAt()));
        }

        if (filterMode == FilterMode.NEWEST) {
            sortedBackups.sort(Comparator.comparing(backup -> backup.getBackupDetails().getCreatedAt(), Comparator.reverseOrder()));
        }

        if (filterMode == FilterMode.GRANTED) {
            sortedBackups.sort(
                    Comparator.comparing((Backup backup) -> backup.getBackupDetails().isGranted(), Comparator.reverseOrder())
                            .thenComparing(backup -> backup.getBackupDetails().getCreatedAt(), Comparator.reverseOrder())
            );
        }

        for (Backup backup : sortedBackups) {
            final Backup currentBackup = backup;
            BackupDetails backupDetails = currentBackup.getBackupDetails();

            Formatter backupFormatter = new Formatter();
            backupFormatter.register("{killer}", backupDetails.getKillerName());
            backupFormatter.register("{reason}", backupDetails.getDeathReason());
            backupFormatter.register("{experience}", backupDetails.getExperience());
            backupFormatter.register("{last_granter}", backupDetails.getGranterName());
            backupFormatter.register("{ping}", backupDetails.getPing());
            backupFormatter.register("{created_at}", this.formatInstant(backupDetails.getCreatedAt()));

            gui.addItem(ItemTransformer.transform(
                    this.config.backupItem.clone(),
                    backupFormatter,
                    event -> this.backupManageMenu.openInventory((Player) event.getWhoClicked(), currentBackup)
            ));
        }
    }

    private void setItem(PaginatedGui gui, int slot, ItemStack itemStack, GuiAction<InventoryClickEvent> action) {
        gui.setItem(slot, new GuiItem(itemStack, action));
    }

    private void setItem(PaginatedGui gui, int slot, GuiItem guiItem) {
        gui.setItem(slot, guiItem);
    }

    private String formatInstant(Instant instant) {
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern(this.config.timeFormat).format(zonedDateTime);
    }
}