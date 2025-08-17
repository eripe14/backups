package com.eripe14.backups.inventory;

import com.eripe14.backups.adventure.LegacyColorPostProcessor;
import com.eripe14.backups.adventure.LegacyColorPreProcessor;
import dev.triumphteam.gui.guis.BaseGui;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.*;

public class InventoryQueueService {

    private final Map<Player, Deque<BaseGui>> playerLastGuis = new HashMap<>();
    private final MiniMessage miniMessage = MiniMessage.builder()
            .postProcessor(new LegacyColorPostProcessor())
            .preProcessor(new LegacyColorPreProcessor())
            .build();

    public void addGui(Player player, BaseGui gui) {
        Deque<BaseGui> baseGuis = this.playerLastGuis.computeIfAbsent(player, k -> new LinkedList<>());

        for (BaseGui baseGui : baseGuis) {
            if (baseGui.title().equals(gui.title())) {
                return;
            }
        }

        baseGuis.offer(gui);
    }

    public void removeGui(Player player, BaseGui gui) {
        Deque<BaseGui> baseGuis = this.playerLastGuis.get(player);

        if (baseGuis == null) {
            return;
        }

        baseGuis.remove(gui);
    }

    public void reset(Player player) {
        this.playerLastGuis.remove(player);
    }

    public void openLatest(Player player) {
        Deque<BaseGui> guiHistory = this.playerLastGuis.get(player);

        if (guiHistory == null) {
            return;
        }

        guiHistory.removeLast();

        BaseGui secondLastGui = guiHistory.peekLast();

        if (secondLastGui == null) {
            player.closeInventory();
            return;
        }

        secondLastGui.open(player);
    }

    public void openSpecificGui(Player player, String guiName) {
        Deque<BaseGui> guiHistory = this.playerLastGuis.get(player);

        if (guiHistory == null) {
            return;
        }

        Optional<BaseGui> findGui = guiHistory.stream()
                .filter(gui -> gui.title().equals(this.miniMessage.deserialize(guiName)))
                .findFirst();

        findGui.ifPresent(gui -> gui.open(player));
    }

}