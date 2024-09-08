package org.example.fontchooser;

import com.formdev.flatlaf.util.FontUtils;

import java.awt.*;

public class FontFamiliesFactory {

    public static FontFamilies create() {
        var fontFamilies = new FontFamilies();
        var allFonts = FontUtils.getAllFonts();

        for (Font font : allFonts) {
            if (!font.getFamily().startsWith(".")) {
                fontFamilies.add(font);
            }
        }

        return fontFamilies;
    }
}
