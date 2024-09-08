package org.example.fontchooser.listeners;

import org.example.fontchooser.FontContainer;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by dheid on 4/1/17.
 */
public class FamilyListSelectionListener implements ListSelectionListener {

    private final FontContainer fontContainer;

    public FamilyListSelectionListener(FontContainer fontContainer) {
        this.fontContainer = fontContainer;
    }

    @Override
    public void valueChanged(@NotNull ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            var oldFont = fontContainer.getSelectedFont();
            var newFont = new Font(
                fontContainer.getSelectedFamily(),
                oldFont.getStyle(),
                (int) fontContainer.getSelectedSize()
            );

            fontContainer.setSelectedFont(newFont);
            fontContainer.setPreviewFont(newFont);
        }
    }
}
