/*
 * SongScribe song notation program
 * Copyright (C) Sri Chinmoy Centres International
 *
 * This file is part of SongScribe.
 *
 * SongScribe is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * SongScribe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.example;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

/*
 * This is a delegate class that handles focus events for text components.
 * It handles tab key presses for JTextAreas, allowing the user to tab between components.
 */
public class TextFocusDelegate implements FocusListener {

    private boolean ignoreTabKey = false;
    private final JTextComponent host;

    public TextFocusDelegate(JTextComponent textComponent) {
        host = textComponent;
        textComponent.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        ignoreTabKey = true;
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Defined for FocusListener completeness
    }

    public boolean processKeyEvent(@NotNull KeyEvent e) {
        // JTextFields don't need special tab handling
        if (host instanceof JTextField) {
            ignoreTabKey = false;
            return false;
        }

        var handled = false;

        // Prevent tabs from being entered into the text area
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            e.consume();
            handled = true;

            if (!ignoreTabKey) {
                if (e.isShiftDown()) {
                    host.transferFocusBackward();
                } else {
                    host.transferFocus();
                }
            }
        }

        ignoreTabKey = false;
        return handled;
    }
}
