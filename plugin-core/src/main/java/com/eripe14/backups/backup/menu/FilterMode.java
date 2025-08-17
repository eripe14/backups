package com.eripe14.backups.backup.menu;

import lombok.Getter;

public enum FilterMode {

    OLDEST("od najstarszych"),
    NEWEST("od najnowszych"),
    GRANTED("od przyznanych");

    private final @Getter String translation;

    FilterMode(String translation) {
        this.translation = translation;
    }

}