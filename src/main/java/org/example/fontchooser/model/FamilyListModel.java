package org.example.fontchooser.model;

import org.example.fontchooser.FontFamilies;
import org.example.fontchooser.FontFamily;

import javax.swing.*;
import java.util.*;

public class FamilyListModel extends AbstractListModel<String> {

    private final FontFamilies fontFamilies = FontFamilies.getInstance();

    private List<String> fontFamilyNames;

    @Override
    public int getSize() {
        initialize();
        return fontFamilyNames.size();
    }

    @Override
    public String getElementAt(int index) {
        initialize();
        return fontFamilyNames.get(index);
    }

    public Optional<String> findFirst(CharSequence searchString) {
        initialize();

        for (String family : fontFamilyNames) {
            if (family.toLowerCase(Locale.ENGLISH).contains(searchString)) {
                return Optional.of(family);
            }
        }

        return Optional.empty();
    }

    private void initialize() {
        if (fontFamilyNames == null) {
            fontFamilyNames = new ArrayList<>(fontFamilies.size());

            for (FontFamily fontFamily : fontFamilies) {
                fontFamilyNames.add(fontFamily.getName());
            }

            fontFamilyNames.sort(Comparator.naturalOrder());
        }
    }
}
