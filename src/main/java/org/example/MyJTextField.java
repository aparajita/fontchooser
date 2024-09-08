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
import java.awt.event.KeyEvent;

public class MyJTextField extends JTextField {

    private final TextFocusDelegate focusDelegate;

    public MyJTextField() {
        focusDelegate = new TextFocusDelegate(this);
    }

    public MyJTextField(int columns) {
        super(columns);
        focusDelegate = new TextFocusDelegate(this);
    }

    @Override
    protected void processKeyEvent(@NotNull KeyEvent e) {
        if (!focusDelegate.processKeyEvent(e)) {
            super.processKeyEvent(e);
        }
    }
}
