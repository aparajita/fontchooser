package org.example;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.extras.FlatUIDefaultsInspector;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.FontUtils;
import com.formdev.flatlaf.util.SystemInfo;
import org.example.font.SourceSansProFont;
import org.example.fontchooser.FontDialog;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {

    public Test() {
    }

    public static void main(String[] args) {
        initLaf();
        var frame = new Test();
        SwingUtilities.invokeLater(frame::run);
    }

    public void run() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var panel = new JPanel();
        var button = new JButton("Click me");
        button.addActionListener(_ -> {
            var chooser = new FontDialog(this, "Font Chooser", true);
            chooser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            chooser.setLocationRelativeTo(null);
            chooser.setSelectedFont(
                FontUtils.getCompositeFont("Source Sans Pro", Font.PLAIN, 14)
            );

            // Make the dialog taller than the default
            chooser.setSize(chooser.getWidth(), chooser.getHeight() + 100);
            chooser.setVisible(true);

            if (!chooser.wasCancelled()) {
                var font = chooser.getSelectedFont();
                System.out.println(STR."Selected font: \{font.getPSName()}");
            }
        });
        panel.add(button);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static void initLaf() {
        try {
            if (SystemInfo.isMacOS) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
            }

            // Enable anti-aliasing and sub-pixel rendering for fonts
            System.setProperty("swing.aatext", "true");
            System.setProperty("awt.useSystemAAFontSettings", "lcd");

            // Prevent background erasure in Swing components
            System.setProperty("sun.awt.noerasebackground", "true");

            SourceSansProFont.install();
            FlatLaf.setPreferredFontFamily(SourceSansProFont.FAMILY);
            FlatLaf.setPreferredLightFontFamily(SourceSansProFont.FAMILY_LIGHT);
            FlatLaf.setPreferredSemiboldFontFamily(
                SourceSansProFont.FAMILY_SEMIBOLD
            );

            FlatLaf.registerCustomDefaultsSource("org.example");

            if (SystemInfo.isMacOS) {
                UIManager.setLookAndFeel(new FlatMacLightLaf());
                FlatMacLightLaf.setup();
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
                FlatLightLaf.setup();
            }

            FlatUIDefaultsInspector.install("ctrl shift alt Y");
            FlatInspector.install("ctrl shift alt X");
        } catch (Exception e) {
            System.out.print("Error initializing laf");
        }
    }
}
