package com.eripe14.backups.backup;

import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BackupService {

    private final BackupRepository backupRepository;

    public Backup createBackup(Player player, String killerName, String deathReason) {
        Backup backup = this.backupRepository.findOrCreateByPath(UUID.randomUUID());

        backup.setPlayerUniqueId(player.getUniqueId());
        backup.setPlayerName(player.getName());
        backup.setBackupDetails(this.getBackupDetails(player, killerName, deathReason));
        backup.setBackupContent(this.getBackupContent(player));
        backup.save();

        return backup;
    }

    public void grantBackup(Player granter, Backup backup) {
        BackupDetails backupDetails = backup.getBackupDetails();
        backupDetails.setGranted(true);
        backupDetails.setGranterName(granter.getName());

        backup.setBackupDetails(backupDetails);
        backup.save();
    }

    public void receiveBackup(Player player, Backup backup) {
        player.getInventory().clear();
        BackupDetails backupDetails = backup.getBackupDetails();
        player.setExp(backupDetails.getExperience());

        BackupContent backupContent = backup.getBackupContent();
        backupContent.getArmorContent().forEach((slot, item) -> player.getInventory().setItem(slot, item));
        backupContent.getInventoryContent().forEach((slot, item) -> player.getInventory().setItem(slot, item));

        player.getInventory().setItemInOffHand(backupContent.getOffHandItem());

        backupDetails.setReceived(true);
        backup.setBackupDetails(backupDetails);
        backup.save();
    }

    private BackupDetails getBackupDetails(Player player, String killerName, String deathReason) {
        BackupDetails backupDetails = new BackupDetails();
        backupDetails.setCreatedAt(Instant.now());
        backupDetails.setKillerName(killerName);
        backupDetails.setDeathReason(deathReason);
        backupDetails.setGranted(false);
        backupDetails.setReceived(false);
        backupDetails.setGranterName("Brak");
        backupDetails.setPing(player.getPing());
        backupDetails.setExperience(player.getExp());

        return backupDetails;
    }

    private BackupContent getBackupContent(Player player) {
        BackupContent backupContent = new BackupContent();
        backupContent.setOffHandItem(player.getInventory().getItemInOffHand());
        backupContent.setInventoryContent(this.getInventoryContent(player));
        backupContent.setArmorContent(this.getArmorContent(player));
        backupContent.setEnderChestContent(this.getEnderChestContent(player));

        return backupContent;
    }

    private Map<Integer, ItemStack> getInventoryContent(Player player) {
        PlayerInventory inventory = player.getInventory();
        Map<Integer, ItemStack> content = new HashMap<>();

        for (int i = 0; i < 36; i++) {
            @Nullable ItemStack item = inventory.getItem(i);
            if (item == null) {
                continue;
            }

            content.put(i, item);
        }

        return content;
    }

    private Map<Integer, ItemStack> getArmorContent(Player player) {
        PlayerInventory inventory = player.getInventory();
        Map<Integer, ItemStack> content = new HashMap<>();

        int[] armorSlots = {36, 37, 38, 39};
        for (int i : armorSlots) {
            @Nullable ItemStack item = inventory.getItem(i);
            if (item == null) {
                continue;
            }

            content.put(i, item);
        }

        return content;
    }

    private Map<Integer, ItemStack> getEnderChestContent(Player player) {
        Inventory enderChest = player.getEnderChest();
        Map<Integer, ItemStack> content = new HashMap<>();

        for (int i = 0; i <= enderChest.getSize(); i++) {
            @Nullable ItemStack item = enderChest.getItem(i);
            if (item == null) {
                continue;
            }

            content.put(i, item);
        }

        return content;
    }

    public List<Backup> getBackups(String name) {
        return this.backupRepository.getPlayerBackups(name).toList();
    }

    public List<Backup> getGrantedBackupsSync(String name) {
        return this.backupRepository.getPlayerBackups(name)
                .filter(backup -> backup.getBackupDetails().isGranted() && !backup.getBackupDetails().isReceived())
                .toList();
    }

    public CompletableFuture<List<Backup>> getGrantedBackups(String name) {
        return CompletableFuture.supplyAsync(() -> this.backupRepository.getPlayerBackups(name)
                .filter(backup -> backup.getBackupDetails().isGranted())
                .toList());
    }

}