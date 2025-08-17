package com.eripe14.backups.backup.menu;

import com.eripe14.backups.adventure.Legacy;
import com.eripe14.backups.backup.Backup;
import com.eripe14.backups.config.PluginConfig;
import com.eripe14.backups.inventory.InventoryQueueService;
import com.eripe14.backups.inventory.ItemTransformer;
import com.eripe14.backups.shared.Formatter;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BackupInventoryContentMenu {

    private final PluginConfig config;
    private final InventoryQueueService inventoryQueueService;

    public void openInventory(Player player, Backup backup) {
        Gui gui = Gui.gui()
                .title(Legacy.title(this.config.inventoryContentTitle))
                .rows(this.config.inventoryContentRows)
                .disableAllInteractions()
                .create();

        Formatter formatter = new Formatter();
        this.config.inventoryContentStructure.forEach((slot, item) -> {
            this.setItem(gui, slot, ItemTransformer.transform(item, formatter, event -> { }));
        });

        for (ItemStack itemStack : backup.getBackupContent().getInventoryContent().values()) {
            gui.addItem(ItemTransformer.transform(itemStack, formatter, event -> { }));
        }

        this.setItem(gui, this.config.inventoryGoBackSlot, ItemTransformer.transform(this.config.goBackItem, formatter, event -> {
            this.inventoryQueueService.openLatest(player);
        }));

        gui.open(player);
        this.inventoryQueueService.addGui(player, gui);
    }

    private void setItem(Gui gui, int slot, GuiItem guiItem) {
        gui.setItem(slot, guiItem);
    }

}