package org.example.fontchooser.panes;

import org.example.MyJTextArea;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class PreviewPane extends JScrollPane {

    @Serial
    private static final long serialVersionUID = -854781598723857579L;

    private final JTextArea previewText = new MyJTextArea();

    public PreviewPane() {
        previewText.setText("Bhulite Diyona");
        previewText.setMargin(new Insets(5, 5, 5, 5));
        setPreferredSize(new Dimension(200, 80));
        setViewportView(previewText);
    }

    @Override
    public void updateUI() {
        super.updateUI();
    }

    public void setPreviewFont(Font font) {
        previewText.setFont(font);
    }
}
