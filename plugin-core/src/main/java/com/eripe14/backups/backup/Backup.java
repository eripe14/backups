package com.eripe14.backups.backup;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class Backup extends Document {

    private UUID playerUniqueId;
    private String playerName;
    private BackupDetails backupDetails;
    private BackupContent backupContent;

    public UUID getUniqueId() {
        return this.getPath().toUUID();
    }

}