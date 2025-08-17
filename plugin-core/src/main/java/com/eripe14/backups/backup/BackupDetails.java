package com.eripe14.backups.backup;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = false)
public class BackupDetails extends Document {

    private Instant createdAt;
    private String killerName;
    private String deathReason;
    private boolean granted;
    private boolean received;
    private String granterName;
    private int ping;
    private float experience;

}