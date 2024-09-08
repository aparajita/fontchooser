package org.example.fontchooser.panes;

import org.example.InputUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.io.Serial;

public class SizePane extends JPanel {

    @Serial
    private static final long serialVersionUID = -2849151625253919609L;

    private final JList<Integer> sizeList = new JList<>();

    private final JSpinner sizeSpinner = new JSpinner();

    private final DefaultListModel<Integer> sizeListModel = new DefaultListModel<>();

    public SizePane() {
        setLayout(new GridBagLayout());
        initSizeListModel();
        initSizeList();
        initSizeSpinner();
        addSizeSpinner();
        addSizeScrollPane();
    }

    private void addSizeScrollPane() {
        var sizeScrollPane = new JScrollPane();
        sizeScrollPane.setMinimumSize(new Dimension(50, 100));
        sizeScrollPane.setPreferredSize(new Dimension(60, 100));
        sizeScrollPane.setViewportView(sizeList);
        var gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        add(sizeScrollPane, gridBagConstraints);
    }

    private void addSizeSpinner() {
        var gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 6, 0);
        add(sizeSpinner, gridBagConstraints);
    }

    private void initSizeSpinner() {
        var spinnerHeight = (int) sizeSpinner.getPreferredSize().getHeight();
        sizeSpinner.setPreferredSize(new Dimension(60, spinnerHeight));
        sizeSpinner.setModel(new SpinnerNumberModel(12, 6, 128, 1));
        setupSpinnerEditor(sizeSpinner);
        sizeSpinner.addChangeListener(event -> {

            var value = (Integer) sizeSpinner.getValue();
            var index = ((DefaultListModel<Integer>) sizeList.getModel()).indexOf(value);

            if (index > -1) {
                sizeList.setSelectedValue(value, true);
            } else {
                sizeList.clearSelection();
            }
        });
    }

    private static void setupSpinnerEditor(JSpinner spinner) {
        var editor = (JSpinner.DefaultEditor) spinner.getEditor();
        var textField = editor.getTextField();
        var border = new JScrollPane().getBorder();

        if (border != null && !(border instanceof UIResource)) {
            textField.setBorder(border);
        }

        InputUtils.addNumericFilter(spinner);
    }

    private void initSizeList() {
        sizeList.setModel(sizeListModel);
        sizeList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sizeList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                var index =
                    ((DefaultListModel<Integer>) sizeList.getModel()).indexOf(sizeList.getSelectedValue());

                if (index >= 0) {
                    sizeSpinner.setValue(sizeList.getSelectedValue());
                }
            }
        });

        var renderer = (DefaultListCellRenderer) sizeList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void initSizeListModel() {
        int size = 6;
        int step = 1;
        int ceil = 14;

        do {
            sizeListModel.addElement(size);

            if (size == ceil) {
                ceil += ceil;
                step += step;
            }

            size += step;
        } while (size <= 128);
    }

    @Override
    public void updateUI() {
        super.updateUI();

        if (sizeSpinner != null) {
            setupSpinnerEditor(sizeSpinner);
        }
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        sizeList.addListSelectionListener(listener);
    }

    public void removeListSelectionListener(ListSelectionListener listener) {
        sizeList.removeListSelectionListener(listener);
    }

    public void setSelectedSize(int size) {
        if (sizeListModel.contains(size)) {
            sizeList.setSelectedValue(size, true);
        }

        sizeSpinner.setValue(size);
    }

    public int getSelectedSize() {
        if (!sizeList.isSelectionEmpty()) {
            return sizeList.getSelectedValue();
        }

        return (Integer) sizeSpinner.getValue();
    }
}
