/*
 * StatusBarComponentBorder.java  2/6/13 1:04 PM
 *
 * Copyright (C) 2012-2013 Nick Ma
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.dv.ui.editor;


import com.dv.util.DataViewerUtilities;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/* ----------------------------------------------------------
 * CVS NOTE: Changes to the CVS repository prior to the 
 *           release of version 3.0.0beta1 has meant a 
 *           resetting of CVS revision numbers.
 * ----------------------------------------------------------
 */

/**
 * Simple border for status bar panels.
 *
 * @author Takis Diakoumis
 * @version $Revision: 1460 $
 * @date $Date: 2009-01-25 11:06:46 +1100 (Sun, 25 Jan 2009) $
 */
public class StatusBarComponentBorder implements Border {

    /**
     * the border insets
     */
    private static final Insets insets = new Insets(1, 1, 1, 0);

    public Insets getBorderInsets(Component c) {
        return insets;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(DataViewerUtilities.getDefaultBorderColour());
        // top edge
        g.drawLine(x, y, width, y);
        // bottom edge
        g.drawLine(x, height - 1, width, height - 1);
        // left edge
        g.drawLine(x, 0, x, height - 1);
    }

    public boolean isBorderOpaque() {
        return false;
    }

}











