package com.eripe14.backups;

import cc.dreamcode.command.bukkit.BukkitCommandProvider;
import cc.dreamcode.notice.serializer.BukkitNoticeSerializer;
import cc.dreamcode.platform.DreamVersion;
import cc.dreamcode.platform.bukkit.DreamBukkitConfig;
import cc.dreamcode.platform.bukkit.DreamBukkitPlatform;
import cc.dreamcode.platform.bukkit.component.ConfigurationResolver;
import cc.dreamcode.platform.component.ComponentService;
import cc.dreamcode.platform.other.component.DreamCommandExtension;
import cc.dreamcode.platform.persistence.DreamPersistence;
import cc.dreamcode.platform.persistence.component.DocumentPersistenceResolver;
import cc.dreamcode.platform.persistence.component.DocumentRepositoryResolver;
import cc.dreamcode.utilities.bukkit.StringColorUtil;
import com.eripe14.backups.backup.*;
import com.eripe14.backups.backup.menu.*;
import com.eripe14.backups.command.BackupCommand;
import com.eripe14.backups.command.ReceiveBackupCommand;
import com.eripe14.backups.command.handler.InvalidInputHandlerImpl;
import com.eripe14.backups.command.handler.InvalidPermissionHandlerImpl;
import com.eripe14.backups.command.handler.InvalidSenderHandlerImpl;
import com.eripe14.backups.command.handler.InvalidUsageHandlerImpl;
import com.eripe14.backups.command.result.BukkitNoticeResolver;
import com.eripe14.backups.config.MessageConfig;
import com.eripe14.backups.config.PluginConfig;
import com.eripe14.backups.inventory.InventoryQueueService;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.commons.SerdesCommons;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.configs.yaml.bukkit.serdes.serializer.ItemMetaSerializer;
import eu.okaeri.configs.yaml.bukkit.serdes.serializer.ItemStackSerializer;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.tasker.bukkit.BukkitTasker;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class BackupPlugin extends DreamBukkitPlatform implements DreamBukkitConfig, DreamPersistence {

    @Getter private static BackupPlugin instance;

    @Override
    public void load(@NonNull ComponentService componentService) {
        instance = this;
    }

    @Override
    public void enable(@NonNull ComponentService componentService) {
        componentService.setDebug(false);

        this.registerInjectable(BukkitTasker.newPool(this));

        this.registerInjectable(BukkitCommandProvider.create(this));
        componentService.registerExtension(DreamCommandExtension.class);

        componentService.registerResolver(ConfigurationResolver.class);
        componentService.registerComponent(MessageConfig.class);

        componentService.registerComponent(BukkitNoticeResolver.class);
        componentService.registerComponent(InvalidInputHandlerImpl.class);
        componentService.registerComponent(InvalidPermissionHandlerImpl.class);
        componentService.registerComponent(InvalidSenderHandlerImpl.class);
        componentService.registerComponent(InvalidUsageHandlerImpl.class);

        componentService.registerComponent(PluginConfig.class, pluginConfig -> {
            // register persistence + repositories
            this.registerInjectable(pluginConfig.storageConfig);

            componentService.registerResolver(DocumentPersistenceResolver.class);
            componentService.registerComponent(DocumentPersistence.class);
            componentService.registerResolver(DocumentRepositoryResolver.class);

            // enable additional logs and debug messages
            componentService.setDebug(pluginConfig.debug);
        });

        componentService.registerComponent(BackupRepository.class);
        componentService.registerComponent(BackupService.class);
        componentService.registerComponent(BackupController.class);
        componentService.registerComponent(InventoryQueueService.class);
        componentService.registerComponent(BackupInventoryContentMenu.class);
        componentService.registerComponent(BackupEnderChestContentMenu.class);
        componentService.registerComponent(BackupArmorContentMenu.class);
        componentService.registerComponent(BackupManageMenu.class);
        componentService.registerComponent(BackupMenu.class);
        componentService.registerComponent(BackupReceiveScheduler.class);
        componentService.registerComponent(ReceiveBackupMenu.class);
        componentService.registerComponent(BackupRemovalScheduler.class);
        componentService.registerComponent(BackupCommand.class);
        componentService.registerComponent(ReceiveBackupCommand.class);
    }

    @Override
    public void disable() {
        // features need to be call when server is stopping
    }

    @Override
    public @NonNull DreamVersion getDreamVersion() {
        return DreamVersion.create("Backups", "1.0.0", "1.0.0");
    }

    @Override
    public @NonNull OkaeriSerdesPack getConfigSerdesPack() {
        return registry -> {
            registry.register(new BukkitNoticeSerializer());
            registry.register(new SerdesCommons());
        };
    }

    @Override
    public @NonNull OkaeriSerdesPack getPersistenceSerdesPack() {
        return registry -> {
            registry.register(new SerdesBukkit());

            registry.registerExclusive(ItemStack.class, new ItemStackSerializer(true));
            registry.registerExclusive(ItemMeta.class, new ItemMetaSerializer());
        };
    }

}