package com.eripe14.backups.backup;

import cc.dreamcode.platform.bukkit.component.scheduler.Scheduler;
import com.eripe14.backups.config.PluginConfig;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Scheduler(delay = 20, interval = 20)
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BackupRemovalScheduler implements Runnable {

    private final PluginConfig pluginConfig;
    private final BackupRepository backupRepository;

    @Override
    public void run() {
        this.backupRepository.getBackups().thenAcceptAsync(backups -> {
            if (backups.isEmpty()) {
                return;
            }

            Instant now = Instant.now();

            for (Backup backup : backups) {
                Instant createdAt = backup.getBackupDetails().getCreatedAt();

                if (now.isAfter(createdAt.plus(this.pluginConfig.backupRemoveTime))) {
                    this.backupRepository.deleteByPath(backup.getUniqueId());
                }
            }
        });
    }
}