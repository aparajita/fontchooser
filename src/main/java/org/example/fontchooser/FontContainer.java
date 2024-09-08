package org.example.fontchooser;

import org.example.fontchooser.panes.StyleEntry;

import java.awt.*;

/**
 * Created by dheid on 4/1/17.
 */
public interface FontContainer {

    StyleEntry getSelectedStyle();

    float getSelectedSize();

    String getSelectedFamily();

    Font getSelectedFont();

    void setSelectedFont(Font font);

    void setPreviewFont(Font font);
}
