package org.example.fontchooser.panes;

import javax.swing.*;
import java.awt.*;

public class StyleCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
        JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus
    ) {
        String style = null;

        if (value instanceof StyleEntry entry) {
            style = entry.getName();
        }

        return super.getListCellRendererComponent(list, style, index, isSelected, cellHasFocus);
    }
}
