package org.example.fontchooser.panes;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public final class StyleEntry {
    private static final Pattern STYLE_PATTERN = Pattern.compile(
        "^(?:(?:Extra)?Light|Regular|Medium|Semi[Bb]old|(?:Extra)?Bold|Black|(?:Extra)" +
            "?Heavy|Thin|Italic" +
            "|Oblique|Condensed).*");

    private final Font font;
    private final String name;

    public StyleEntry(Font font) {
        this.font = font;
        this.name = getStyleDescription(font);
    }

    public Font getFont() {
        return font;
    }

    public String getName() {
        return name;
    }

    @NotNull
    public static String getStyleDescription(@NotNull Font font) {
        var psName = font.getPSName();

        // First split on "_". Everthing before the first "_" is the family name,
        // and everything after is the style name.
        ArrayList<String> parts = new ArrayList<>(Arrays.asList(psName.split("_")));

        if (parts.size() > 1) {
            // Insert a dummy family name, we don't use it
            parts.set(0, "Dummy");

            // If there are hyphens in the style name, we need to remove them
            parts.set(1, parts.get(1).replace("-", ""));
            psName = String.join("-", parts);
        }

        // Split on hyphen. The last part is the style name.
        parts = new ArrayList<>(Arrays.asList(psName.split("-")));

        /*
            If there is only one part, usually it's the family name alone,
            in which case we return "Regular". But we need to handle cases
            like this:

            Damascus (Damascus)
            Damascus (DamascusBold)
            Damascus (DamascusLight)
            Damascus (DamascusMedium)
            Damascus (DamascusSemiBold)
        */
        if (parts.size() == 1) {
            var family = font.getFamily();
            var part = parts.getFirst();

            if (part.equals(family)) {
                return "Regular";
            } else if (part.startsWith(family)) {
                // Check to see if the part after the family name is a style.
                // If so, we will parse it. Otherwise, assume it's part of the family name.
                var afterFamily = part.substring(family.length());

                if (STYLE_PATTERN.matcher(afterFamily).matches()) {
                    parts.add(afterFamily);
                } else {
                    return "Regular";
                }
            } else {
                // There are no discernible styles
                return "Regular";
            }
        }

        // The last part is the styles
        return String.join(" ", parseStyles(parts.getLast()));
    }

    @NotNull
    @Contract(pure = true)
    private static String parseStyles(@NotNull String styles) {
        return styles
            // Replace some camel case style names that should not be separated
            .replaceAll("SemiBold", "Semibold")
            .replaceAll("DemiBold", "Demibold")
            .replaceAll("UltraLight", "Ultralight")
            .replaceAll("OsF", "Oldstyle Figures")
            // Convert camel case to separate words
            .replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2")
            // Convert abbreviations to full words
            .replaceAll("\\bIta?\\b", "Italic")
            .replaceAll("\\bSC\\b", "Small Caps")
            .replaceAll("\\bCn\\b", "Condensed")
            .replaceAll("\\bExt\\b", "Extended")
            .replaceAll("\\bExp\\b", "Expanded");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // We can't compare the font directly because the font size/style may be different
        var other = (StyleEntry) obj;
        return font.getPSName().equals(other.font.getPSName());
    }

    @Override
    public int hashCode() {
        return font.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
