package org.example.fontchooser.panes;

import org.example.fontchooser.model.FamilyListModel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;

public class SearchListener extends KeyAdapter {

    private final FamilyListModel familyListModel;

    private final FamilyPane familyPane;

    public SearchListener(FamilyListModel familyListModel, FamilyPane familyPane) {
        this.familyListModel = familyListModel;
        this.familyPane = familyPane;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        var searchField = (JTextField) e.getSource();
        var searchString = searchField.getText().toLowerCase(Locale.ENGLISH);
        familyListModel.findFirst(searchString).ifPresent(familyPane::setSelectedFamily);
    }
}
