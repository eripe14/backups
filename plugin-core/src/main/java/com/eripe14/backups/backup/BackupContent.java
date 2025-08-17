package com.eripe14.backups.backup;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class BackupContent extends Document {

    private ItemStack offHandItem;
    private Map<Integer, ItemStack> inventoryContent;
    private Map<Integer, ItemStack> armorContent;
    private Map<Integer, ItemStack> enderChestContent;

}