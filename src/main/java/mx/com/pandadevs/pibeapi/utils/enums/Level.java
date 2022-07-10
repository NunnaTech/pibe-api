package mx.com.pandadevs.pibeapi.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Locale;

public enum Level {
    BASICO("Basico"),
    INTERMEDIO("Intermedio"),
    AVANZADO("Avanzado");
    private String level;

    Level(String level) {
    }
    // Getters && Setters

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    @JsonCreator
    public static Level getLevelByName(String level) {
        return Arrays.stream(Level.values())
                .filter(enumValue -> enumValue.level.equals(level.toUpperCase(Locale.ROOT)))
                .findFirst()
                .orElse(null);
    }
}
