# FontChooser

This is a modified version of [FontChooser](https://github.com/dheid/fontchooser) by [dheid](https://github.com/dheid)
that I created for use in my own projects. Thank you Daniel for the great work!

This is not a fork, as I just wanted to use it as a starting point for my own modifications and am not not using a bunch
of features in the original version.

### Better style display

The main feature in this version is better display of font styles (see **StyleEntry.java**):

- The style portion of
  PostScript font names are parsed and transformed into a more human-readable form.
- Inconsistencies in style naming are corrected.
- Edge cases like the style not being separated from the family name are handled.
- When a new font family is selected, the default style for that family is selected.

### Other changes

- Pressing Tab in the preview field moves the focus instead of inserting a tab.
- Only numbers can be entered in the size field.
- List labels are indented slightly to the right to visually look more aligned with the list items.
