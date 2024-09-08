package org.example.fontchooser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class FontFamilies implements Iterable<FontFamily>, Serializable {

    private static final FontFamilies INSTANCE = createFontFamilies();

    @NotNull
    private static FontFamilies createFontFamilies() {
        return FontFamiliesFactory.create();
    }

    public static FontFamilies getInstance() {
        return INSTANCE;
    }

    private final Map<String, FontFamily> families = new TreeMap<>();

    public void add(@NotNull Font font) {
        var family = font.getFamily();
        var fontFamily = families.computeIfAbsent(family, FontFamily::new);
        fontFamily.add(font);
    }

    @NotNull
    @Override
    public Iterator<FontFamily> iterator() {
        return families.values().iterator();
    }

    @Nullable
    public FontFamily get(String name) {
        return families.get(name);
    }

    public int size() {
        return families.size();
    }
}
