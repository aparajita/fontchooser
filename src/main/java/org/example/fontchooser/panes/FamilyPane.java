package org.example.fontchooser.panes;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatSearchIcon;
import org.example.MyJTextField;
import org.example.fontchooser.model.FamilyListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.Serial;

public class FamilyPane extends JPanel {

    @Serial
    private static final long serialVersionUID = 5705429171724237594L;

    private final JList<String> familyList = new JList<>();

    private final SearchListener searchListener;

    public FamilyPane() {
        var familyListModel = new FamilyListModel();
        searchListener = new SearchListener(familyListModel, this);

        initializeList(familyListModel);
        setMinimumSize(new Dimension(80, 100));
        setPreferredSize(new Dimension(240, 160));

        setLayout(new GridBagLayout());
        addSearchField();
        addScrollPane();
    }

    private void initializeList(ListModel<String> familyListModel) {
        familyList.setModel(familyListModel);
        familyList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void addSearchField() {
        var gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.weightx = 1.0;

        var searchField = new MyJTextField();
        searchField.setMargin(new Insets(4, 4, 4, 4));
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        searchField.putClientProperty(
            FlatClientProperties.TEXT_FIELD_LEADING_ICON,
            new FlatSearchIcon()
        );
        searchField.requestFocus();
        searchField.addKeyListener(searchListener);
        add(searchField, gridBagConstraints);
    }

    private void addScrollPane() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(new JScrollPane(familyList), gridBagConstraints);
    }

    public void setSelectedFamily(String family) {
        familyList.setSelectedValue(family, true);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        familyList.addListSelectionListener(listener);
    }

    public void removeListSelectionListener(ListSelectionListener listener) {
        familyList.removeListSelectionListener(listener);
    }

    public String getSelectedFamily() {
        return familyList.getSelectedValue();
    }
}
