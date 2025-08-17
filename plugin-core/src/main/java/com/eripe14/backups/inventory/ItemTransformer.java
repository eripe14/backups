package com.eripe14.backups.inventory;

import com.eripe14.backups.adventure.LegacyColorPostProcessor;
import com.eripe14.backups.adventure.LegacyColorPreProcessor;
import com.eripe14.backups.adventure.MiniMessageHolder;
import com.eripe14.backups.backup.menu.FilterMode;
import com.eripe14.backups.shared.Formatter;
import dev.triumphteam.gui.components.GuiAction;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public final class ItemTransformer implements MiniMessageHolder {

    public static GuiItem transform(ItemStack itemStack, Formatter formatter, GuiAction<InventoryClickEvent> action) {
        ItemStack clone = itemStack.clone();
        ItemMeta itemMeta = clone.getItemMeta();

        if (itemMeta == null) {
            return new GuiItem(clone, action);
        }

        if (itemMeta.hasDisplayName()) {
            try {
                String serialized = formatter.format(MINI_MESSAGE.serialize(itemMeta.displayName()));
                Component parsed = MINI_MESSAGE.deserialize(serialized);
                itemMeta.displayName(parsed);
            } catch (Exception e) {
                // Fallback if serialization fails
                // możesz dodać log błędu lub użyć domyślnej nazwy
            }
        }

        if (itemMeta.hasLore() && itemMeta.lore() != null) {
            List<Component> lore = itemMeta.lore();
            if (lore != null) {
                List<Component> updatedLore = new ArrayList<>();

                for (Component line : lore) {
                    if (line != null) {
                        try {
                            String serialized = formatter.format(MINI_MESSAGE.serialize(line));
                            Component parsedLine = MINI_MESSAGE.deserialize(serialized);
                            updatedLore.add(parsedLine);
                        } catch (Exception e) {
                            // Fallback if serialization fails
                            updatedLore.add(line); // Zachowaj oryginalną linię
                        }
                    } else {
                        // Jeśli linia jest nullem, dodaj pustą linię lub jakiś placeholder
                        updatedLore.add(Component.text(""));
                    }
                }

                itemMeta.lore(updatedLore);
            }
        }


        clone.setItemMeta(itemMeta);
        return new GuiItem(clone, action);
    }

    public static GuiItem transform(ItemStack itemStack, Formatter formatter, FilterMode filterMode, GuiAction<InventoryClickEvent> action) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            return new GuiItem(itemStack, action);
        }

        if (itemMeta.hasDisplayName()) {
            String serialized = formatter.format(MINI_MESSAGE.serialize(itemMeta.displayName()));
            Component parsed = MINI_MESSAGE.deserialize(serialized);

            itemMeta.displayName(parsed);
        }

        if (itemMeta.hasLore()) {
            List<Component> updatedLore = itemMeta.lore().stream()
                    .map(line -> {
                        String serialized = MINI_MESSAGE.serialize(line);

                        if (serialized.contains(FilterMode.NEWEST.getTranslation())) {
                            serialized = serialized.replace(FilterMode.NEWEST.getTranslation(), filterMode.getTranslation());
                        } else if (serialized.contains(FilterMode.OLDEST.getTranslation())) {
                            serialized = serialized.replace(FilterMode.OLDEST.getTranslation(), filterMode.getTranslation());
                        } else if (serialized.contains(FilterMode.GRANTED.getTranslation())) {
                            serialized = serialized.replace(FilterMode.GRANTED.getTranslation(), filterMode.getTranslation());
                        } else if (serialized.contains("{filter_mode}")) {
                            serialized = serialized.replace("{filter_mode}", filterMode.getTranslation());
                        }

                        return MINI_MESSAGE.deserialize(serialized);
                    })
                    .toList();

            itemMeta.lore(updatedLore);
        }

        itemStack.setItemMeta(itemMeta);
        return new GuiItem(itemStack, action);
    }

}