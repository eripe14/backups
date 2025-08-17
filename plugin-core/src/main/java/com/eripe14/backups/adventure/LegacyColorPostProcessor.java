package com.eripe14.backups.adventure;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class LegacyColorPostProcessor implements UnaryOperator<Component> {

    private static final TextReplacementConfig LEGACY_REPLACEMENT_CONFIG = TextReplacementConfig.builder()
            .match(Pattern.compile(".*"))
            .replacement((matchResult, build) -> Legacy.component(matchResult.group()))
            .build();

    @Override
    public Component apply(Component component) {
        return component.replaceText(LEGACY_REPLACEMENT_CONFIG);
    }

}