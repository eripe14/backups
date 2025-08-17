package com.eripe14.backups.backup;

import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import eu.okaeri.persistence.repository.annotation.DocumentIndex;
import eu.okaeri.persistence.repository.annotation.DocumentPath;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@DocumentCollection(path = "backups", keyLength = 36, indexes = {
        @DocumentIndex(path = "playerUniqueId", maxLength = 36),
        @DocumentIndex(path = "playerName", maxLength = 16)
})
public interface BackupRepository extends DocumentRepository<UUID, Backup> {

    @DocumentPath("playerName")
    Stream<Backup> getPlayerBackups(String name);

    default CompletableFuture<Collection<Backup>> getBackups() {
        return CompletableFuture.supplyAsync(this::findAll);
    }

}