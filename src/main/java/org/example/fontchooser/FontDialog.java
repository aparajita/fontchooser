/*
 * A font chooser JavaBean component.
 * Copyright (C) 2009 Dr Christos Bohoris
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 3 as published by the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * swing@connectina.com
 */
package org.example.fontchooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;

/**
 * A dialog containing a {@code FontChooser} as well as OK and Cancel buttons.
 *
 * @author Christos Bohoris
 */
public class FontDialog extends JDialog {

    @Serial
    private static final long serialVersionUID = -5545636367279574840L;

    public static void showDialog(Component component) {
        FontDialog dialog = new FontDialog((Frame) null, "Select Font", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setSelectedFont(component.getFont());
        dialog.setVisible(true);

        if (!dialog.cancelled) {
            component.setFont(dialog.getSelectedFont());
        }
    }

    private final FontChooser chooser = new FontChooser();

    private final JButton cancelButton = new JButton("Cancel");

    private final JButton okButton = new JButton("OK");

    public FontDialog() {
        initDialog();
    }

    public FontDialog(Frame owner) {
        super(owner);
        initDialog();
    }

    public FontDialog(Frame owner, boolean modal) {
        super(owner, modal);
        initDialog();
    }

    public FontDialog(Frame owner, String title) {
        super(owner, title);
        initDialog();
    }

    public FontDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        initDialog();
    }

    public FontDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        initDialog();
    }

    public FontDialog(Dialog owner) {
        super(owner);
        initDialog();
    }

    public FontDialog(Dialog owner, boolean modal) {
        super(owner, modal);
        initDialog();
    }

    public FontDialog(Dialog owner, String title) {
        super(owner, title);
        initDialog();
    }

    public FontDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
        initDialog();
    }

    public FontDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        initDialog();
    }

    public FontDialog(Window owner) {
        super(owner);
        initDialog();
    }

    public FontDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
        initDialog();
    }

    public FontDialog(Window owner, String title) {
        super(owner, title);
        initDialog();
    }

    public FontDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
        initDialog();
    }

    public FontDialog(
        Window owner,
        String title,
        ModalityType modalityType,
        GraphicsConfiguration gc
    ) {
        super(owner, title, modalityType, gc);
        initDialog();
    }

    private boolean cancelled = false;

    private void initDialog() {
        initComponents();
        initKeyBindings();
        setMinimumSize(new Dimension(500, 400));
        getRootPane().setDefaultButton(okButton);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancelled = true;
            }
        });
        pack();
    }

    private void initComponents() {
        var chooserPanel = new JPanel();
        chooserPanel.setBorder(BorderFactory.createEmptyBorder(13, 13, 0, 13));
        chooserPanel.setLayout(new BorderLayout(0, 13));
        chooserPanel.add(chooser);
        add(chooserPanel);

        var controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(13, 13, 13, 13));
        controlPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        add(controlPanel, BorderLayout.PAGE_END);

        cancelButton.addActionListener(event -> {
            cancelled = true;
            dispose();
        });
        controlPanel.add(cancelButton);

        okButton.addActionListener(event -> dispose());
        controlPanel.add(okButton);
    }

    private void initKeyBindings() {
        var escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        var actionKey = "action:WINDOW_CLOSING";

        var dispatchClosing = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dispatchEvent(new WindowEvent(FontDialog.this, WindowEvent.WINDOW_CLOSING));
            }
        };

        var rootPane = getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, actionKey);
        rootPane.getActionMap().put(actionKey, dispatchClosing);
    }

    public Font getSelectedFont() {
        return chooser.getSelectedFont();
    }

    public void setSelectedFont(Font font) {
        chooser.setSelectedFont(font);
    }

    public boolean wasCancelled() {
        return cancelled;
    }
}
