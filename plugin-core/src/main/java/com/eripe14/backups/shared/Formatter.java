package com.eripe14.backups.shared;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Formatter {

    private final @Getter Map<String, Object> placeholders = new LinkedHashMap<>();

    public String format(String message) {
        for (Map.Entry<String, Object> placeholderEntry : placeholders.entrySet()) {
            String key = placeholderEntry.getKey();

            if (!message.contains(key)) {
                continue;
            }

            Object value = placeholderEntry.getValue();

            if (value == null) {
                continue;
            }

            message = message.replace(key, value.toString());
        }

        return message;
    }

    public void register(String placeholder, Object value) {
        this.placeholders.put(placeholder, value.toString());
    }

}