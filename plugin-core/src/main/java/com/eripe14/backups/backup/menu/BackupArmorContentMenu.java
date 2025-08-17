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
public class BackupArmorContentMenu {

    private final PluginConfig config;
    private final InventoryQueueService inventoryQueueService;

    public void openInventory(Player player, Backup backup) {
        Gui gui = Gui.gui()
                .title(Legacy.title(this.config.armorTitle))
                .rows(this.config.armorRows)
                .disableAllInteractions()
                .create();

        Formatter formatter = new Formatter();

        for (ItemStack itemStack : backup.getBackupContent().getArmorContent().values()) {
            gui.addItem(ItemTransformer.transform(itemStack, formatter, event -> {}));
        }

        if (this.config.armorFillEmptySlots) {
            gui.getFiller().fill(ItemTransformer.transform(this.config.armorFiller, formatter, event -> {}));
        }

        this.setItem(gui, this.config.armorGoBackSlot, ItemTransformer.transform(this.config.goBackItem, formatter, event -> {
            this.inventoryQueueService.openLatest(player);
        }));

        gui.open(player);
        this.inventoryQueueService.addGui(player, gui);
    }

    private void setItem(Gui gui, int slot, GuiItem guiItem) {
        gui.setItem(slot, guiItem);
    }

}