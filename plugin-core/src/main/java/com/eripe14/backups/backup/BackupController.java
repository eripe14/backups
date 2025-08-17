package com.eripe14.backups.backup;

import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BackupController implements Listener {

    private final BackupService backupService;

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();
        EntityDamageEvent lastDamageCause = player.getLastDamageCause();

        this.backupService.createBackup(
                player,
                killer != null ? killer.getName() : "Brak",
                lastDamageCause != null
                        ? lastDamageCause.getCause().name().toLowerCase().replace("_", " ")
                        : "Brak"
        );
    }

}