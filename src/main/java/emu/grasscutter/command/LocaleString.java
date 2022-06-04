package emu.grasscutter.command;

import emu.grasscutter.game.player.Player;
import org.jetbrains.annotations.NotNull;
import static emu.grasscutter.utils.Language.translate;

public record LocaleString(String key, Object... args) {
    @Override
    public String toString() {
        return translate(key, args);
    }

    public String toString(@NotNull Player player) {
        return translate(player, key, args);
    }
}
