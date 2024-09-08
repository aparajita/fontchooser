package org.example.fontchooser;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

public class FontNameComparator implements Comparator<Font>, Serializable {

    @Serial
    private static final long serialVersionUID = 1143602375442062028L;

    @Override
    public int compare(@NotNull Font font1, @NotNull Font font2) {
        return font1.getName().compareTo(font2.getName());
    }
}
