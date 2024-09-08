package org.example.fontchooser.listeners;

import org.example.fontchooser.FontContainer;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by dheid on 4/1/17.
 */
public class SizeListSelectionListener implements ListSelectionListener {

    private final FontContainer fontContainer;

    public SizeListSelectionListener(FontContainer fontContainer) {
        this.fontContainer = fontContainer;
    }

    @Override
    public void valueChanged(@NotNull ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            var newSize = fontContainer.getSelectedSize();
            var newFont = fontContainer.getSelectedFont().deriveFont(newSize);
            fontContainer.setSelectedFont(newFont);
            fontContainer.setPreviewFont(fontContainer.getSelectedFont());
        }
    }
}
