package org.example.fontchooser;

import java.awt.*;
import java.util.Collection;
import java.util.TreeSet;

public class FontFamily {

    private final String name;

    private final Collection<Font> styles = new TreeSet<>(new FontNameComparator());

    public FontFamily(String name) {
        this.name = name;
    }

    public boolean add(Font font) {
        return styles.add(font);
    }

    public String getName() {
        return name;
    }

    public Collection<Font> getStyles() {
        return styles;
    }
}
