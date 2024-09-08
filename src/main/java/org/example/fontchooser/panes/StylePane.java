package org.example.fontchooser.panes;

import org.example.fontchooser.FontFamilies;
import org.example.fontchooser.model.FontSelectionModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.Serial;
import java.util.Objects;

public class StylePane extends JScrollPane implements ChangeListener {

    @Serial
    private static final long serialVersionUID = -176731082795772255L;

    private final JList<StyleEntry> styleList = new JList<>();

    private final DefaultListModel<StyleEntry> styleListModel;

    private String family;

    public StylePane() {
        styleListModel = new DefaultListModel<>();
        styleList.setModel(styleListModel);
        styleList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        styleList.setCellRenderer(new StyleCellRenderer());
        setMinimumSize(new Dimension(160, 100));
        setPreferredSize(new Dimension(160, 100));
        setViewportView(styleList);

    }

    public void addListSelectionListener(ListSelectionListener listener) {
        styleList.addListSelectionListener(listener);
    }

    public void removeListSelectionListener(ListSelectionListener listener) {
        styleList.removeListSelectionListener(listener);
    }

    public void setSelectedStyle(Font font) {
        styleList.setSelectedValue(new StyleEntry(font), true);
    }

    public StyleEntry getSelectedStyle() {
        return styleList.getSelectedValue();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        var fontSelectionModel = (FontSelectionModel) e.getSource();
        var selectedFont = fontSelectionModel.getSelectedFont();
        loadFamily(selectedFont.getFamily());
    }

    public void loadFamily(String family) {
        if (Objects.equals(this.family, family)) {
            return;
        }

        this.family = family;
        var fontFamilies = FontFamilies.getInstance();
        var fontFamily = fontFamilies.get(family);

        if (fontFamily != null) {
            var selectionListeners = styleList.getListSelectionListeners();
            removeSelectionListeners(selectionListeners);
            updateListModel(fontFamily.getStyles());
            addSelectionListeners(selectionListeners);
        }
    }

    private void updateListModel(@NotNull Iterable<Font> fonts) {
        styleListModel.clear();

        for (Font font : fonts) {
            styleListModel.addElement(new StyleEntry(font));
        }
    }

    private void addSelectionListeners(ListSelectionListener @NotNull [] selectionListeners) {
        for (ListSelectionListener listener : selectionListeners) {
            styleList.addListSelectionListener(listener);
        }
    }

    private void removeSelectionListeners(ListSelectionListener @NotNull [] selectionListeners) {
        for (ListSelectionListener listener : selectionListeners) {
            styleList.removeListSelectionListener(listener);
        }
    }

}
