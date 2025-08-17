package com.eripe14.backups.backup;

import cc.dreamcode.platform.bukkit.component.scheduler.Scheduler;
import cc.dreamcode.utilities.bukkit.StringColorUtil;
import com.eripe14.backups.config.MessageConfig;
import com.eripe14.backups.config.PluginConfig;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

@Scheduler(async = false, delay = 20, interval = 200)
public class BackupReceiveScheduler implements Runnable {

    private @Inject Plugin plugin;
    private @Inject BackupService backupService;
    private @Inject PluginConfig pluginConfig;
    private @Inject MessageConfig messageConfig;

    private BossBar bossBar;

    @PostConstruct
    public void initialize() {
        this.bossBar = Bukkit.createBossBar(
                StringColorUtil.fixColor(this.pluginConfig.bossBarTitle),
                this.pluginConfig.bossBarColor,
                this.pluginConfig.bossBarOverlay
        );
    }

    @Override
    public void run() {
        if (this.bossBar == null) {
            return;
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            List<Backup> backups = this.backupService.getGrantedBackupsSync(onlinePlayer.getName());

            if (backups.isEmpty()) {
                this.bossBar.removePlayer(onlinePlayer);
                return;
            }

            this.messageConfig.youHaveBackupsToReceive.send(onlinePlayer);
            this.bossBar.addPlayer(onlinePlayer);
        }
    }
}