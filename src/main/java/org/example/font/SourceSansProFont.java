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

package org.example.font;

import com.formdev.flatlaf.util.FontUtils;

/**
 * The Source Sans Pro font family.
 * <p>
 * To install the font, invoke following once (e.g. in your {@code main()} method; on AWT thread).
 * <p>
 * For lazy loading use:
 * <pre>{@code
 * SourceSansProFont.installLazy();
 * }</pre>
 * <p>
 * Or load immediately with:
 * <pre>{@code
 * SourceSansProFont.install();
 * // or
 * SourceSansProFont.installBasic();
 * SourceSansProFont.installLight();
 * SourceSansProFont.installSemiBold();
 * }</pre>
 * <p>
 * Use as application font (invoke before setting up FlatLaf):
 * <pre>{@code
 * FlatLaf.setPreferredFontFamily( SourceSansProFont.FAMILY );
 * FlatLaf.setPreferredLightFontFamily( SourceSansProFont.FAMILY_LIGHT );
 * FlatLaf.setPreferredSemiboldFontFamily( SourceSansProFont.FAMILY_SEMIBOLD );
 * }</pre>
 * <p>
 * Create single fonts:
 * <pre>{@code
 * new Font( SourceSansProFont.FAMILY, Font.PLAIN, 12 );
 * new Font( SourceSansProFont.FAMILY, Font.ITALIC, 12 );
 * new Font( SourceSansProFont.FAMILY, Font.BOLD, 12 );
 * new Font( SourceSansProFont.FAMILY, Font.BOLD | Font.ITALIC, 12 );
 * new Font( SourceSansProFont.FAMILY_LIGHT, Font.PLAIN, 12 );
 * new Font( SourceSansProFont.FAMILY_LIGHT, Font.ITALIC, 12 );
 * new Font( SourceSansProFont.FAMILY_SEMIBOLD, Font.PLAIN, 12 );
 * new Font( SourceSansProFont.FAMILY_SEMIBOLD, Font.ITALIC, 12 );
 * }</pre>
 * <p>
 * If using lazy loading, invoke one of following before creating the font:
 * <pre>{@code
 * FontUtils.loadFontFamily( SourceSansProFont.FAMILY );
 * FontUtils.loadFontFamily( SourceSansProFont.FAMILY_LIGHT );
 * FontUtils.loadFontFamily( SourceSansProFont.FAMILY_SEMIBOLD );
 * }</pre>
 * <p>
 * E.g.:
 * <pre>{@code
 * FontUtils.loadFontFamily( SourceSansProFont.FAMILY );
 * Font font = new Font( SourceSansProFont.FAMILY, Font.PLAIN, 12 );
 * }</pre>
 * <p>
 * Or use following:
 * <pre>{@code
 * Font font = FontUtils.getCompositeFont( SourceSansProFont.FAMILY, Font.PLAIN, 12 );
 * }</pre>
 *
 * @author Karl Tauber
 */
public final class SourceSansProFont {

    /**
     * Family name for basic styles (regular, italic and bold).
     * <p>
     * Usage:
     * <pre>{@code
     * new Font( SourceSansProFont.FAMILY, Font.PLAIN, 12 );
     * new Font( SourceSansProFont.FAMILY, Font.ITALIC, 12 );
     * new Font( SourceSansProFont.FAMILY, Font.BOLD, 12 );
     * new Font( SourceSansProFont.FAMILY, Font.BOLD | Font.ITALIC, 12 );
     * }</pre>
     */
    public static final String FAMILY = "Source Sans Pro";

    /**
     * Family name for light styles.
     * <p>
     * Usage:
     * <pre>{@code
     * new Font( SourceSansProFont.FAMILY_LIGHT, Font.PLAIN, 12 );
     * new Font( SourceSansProFont.FAMILY_LIGHT, Font.ITALIC, 12 );
     * }</pre>
     */
    public static final String FAMILY_LIGHT = "Source Sans Pro";

    /**
     * Family name for semibold styles.
     * <p>
     * Usage:
     * <pre>{@code
     * new Font( SourceSansProFont.FAMILY_SEMIBOLD, Font.PLAIN, 12 );
     * new Font( SourceSansProFont.FAMILY_SEMIBOLD, Font.ITALIC, 12 );
     * }</pre>
     */
    public static final String FAMILY_SEMIBOLD = "Source Sans Pro SemiBold";

    /**
     * Family name for bold styles.
     * <p>
     * Usage:
     * <pre>{@code
     * new Font( SourceSansProFont.FAMILY_BOLD, Font.PLAIN, 12 );
     * new Font( SourceSansProFont.FAMILY_BOLD, Font.ITALIC, 12 );
     * }</pre>
     */
    public static final String FAMILY_BOLD = "Source Sans Pro Bold";

    /**
     * Use for {@link #installStyle(String)} to install single font style.
     */
    public static final String STYLE_REGULAR = // basic styles
        "SourceSansPro-Regular.ttf", STYLE_ITALIC =
        "SourceSansPro-Italic.ttf", STYLE_SEMIBOLD =
        "SourceSansPro-SemiBold.ttf", STYLE_SEMIBOLD_ITALIC =
        "SourceSansPro-SemiBoldItalic.ttf", STYLE_BOLD =
        "SourceSansPro-Bold.ttf", STYLE_BOLD_ITALIC =
        "SourceSansPro-BoldItalic.ttf";

    private SourceSansProFont() {
    }

    /**
     * Registers the fonts for lazy loading via
     * {@link FontUtils#registerFontFamilyLoader(String, Runnable)}.
     * <p>
     * This is the preferred method (when using FlatLaf) to avoid unnecessary loading of maybe
     * unused fonts.
     * <p>
     * <strong>Note</strong>: When using '{@code new Font(...)}', you need to first invoke
     * {@link FontUtils#loadFontFamily(String)} to ensure that the font family is loaded.
     * When FlatLaf loads a font, or when using
     * {@link FontUtils#getCompositeFont(String, int, int)},
     * this is done automatically.
     */
    public static void installLazy() {
        FontUtils.registerFontFamilyLoader(
            FAMILY,
            SourceSansProFont::installBasic
        );
    }

    /**
     * Creates and registers the fonts for all styles.
     * <p>
     * When using FlatLaf, consider using {@link #installLazy()}.
     */
    public static void install() {
        installBasic();
    }

    /**
     * Creates and registers the fonts for basic styles (regular, italic and bold).
     * <p>
     * When using FlatLaf, consider using {@link #installLazy()}.
     */
    public static void installBasic() {
        installStyle(STYLE_REGULAR);
        installStyle(STYLE_ITALIC);
        installStyle(STYLE_SEMIBOLD);
        installStyle(STYLE_SEMIBOLD_ITALIC);
        installStyle(STYLE_BOLD);
        installStyle(STYLE_BOLD_ITALIC);
    }

    /**
     * Creates and registers the font for the given style.
     * See {@code STYLE_} constants.
     */
    public static void installStyle(String name) {
        FontUtils.installFont(
            SourceSansProFont.class.getResource(STR."/fonts/\{name}")
        );
    }
}
