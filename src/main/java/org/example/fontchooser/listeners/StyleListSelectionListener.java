package org.example.fontchooser.listeners;

import org.example.fontchooser.FontContainer;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by dheid on 4/1/17.
 */
public class StyleListSelectionListener implements ListSelectionListener {

    private final FontContainer fontContainer;

    public StyleListSelectionListener(FontContainer fontContainer) {
        this.fontContainer = fontContainer;
    }

    @Override
    public void valueChanged(@NotNull ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            var selectedStyle = fontContainer.getSelectedStyle();
            var newFont = selectedStyle.getFont().deriveFont(fontContainer.getSelectedSize());
            fontContainer.setSelectedFont(newFont);
            fontContainer.setPreviewFont(newFont);
        }
    }
}
