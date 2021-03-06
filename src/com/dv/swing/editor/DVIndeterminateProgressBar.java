/*
 * DVIndeterminateProgressBar.java  2/6/13 1:04 PM
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

package com.dv.swing.editor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.UIManager;


/**
 * @author Nick
 */
public class DVIndeterminateProgressBar extends JComponent implements Runnable {

    private Color scrollbarColour;

    private int scrollerWidth;
    private int animationOffset;

    private Timer timer;
    private TimerTask paintProgress;

    private boolean inProgress;
    private boolean paintBorder;

    public DVIndeterminateProgressBar() {
        this(true);
    }

    public DVIndeterminateProgressBar(boolean paintBorder) {
        inProgress = false;
        scrollerWidth = 20;
        this.paintBorder = paintBorder;

        animationOffset = scrollerWidth * -1;
//        setScrollbarColour(UIManager.getColor("ProgressBar.foreground"));
        setScrollbarColour(Color.GRAY);
    }

    public void run() {
        animationOffset++;
        repaint();
        if (animationOffset >= 0) {
            animationOffset = scrollerWidth * -1;
        }
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
        inProgress = false;
        repaint();
    }

    public void start() {

        paintProgress = new TimerTask() {
            public void run() {
                EventQueue.invokeLater(DVIndeterminateProgressBar.this);
            }
        };

        timer = new Timer();
        timer.schedule(paintProgress, 0, 25);
        inProgress = true;
        repaint();
    }

    public void cleanup() {
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
    }

    public void paintComponent(Graphics g) {

        int width = getWidth();
        int height = getHeight();

        int y1 = height - 2;
        int y4 = height - 3;

        if (paintBorder) {
            // draw the line border
            g.setColor(getScrollbarColour());
            g.drawRect(0, 0, width - 2, height - 2);
            width--;
        } else {
            // draw the default border
            paintBorder(g);
            y1 = height;
            y4 = height - 1;
        }

        if (!inProgress) {
            return;
        }

        // set the polygon points
        int[] xPosns = {0, 0, 0, 0};
        int[] yPosns = {y1, 1, 1, y1};

        // constrain the clip
        //g.setClip(1, 1, width - 3, y4);
        g.setClip(0, 1, width, y4);

        g.setColor(getScrollbarColour());

        for (int i = 0, k = width + scrollerWidth; i < k; i += scrollerWidth) {

            xPosns[0] = i + animationOffset;
            xPosns[1] = xPosns[0] + (scrollerWidth / 2);
            xPosns[2] = xPosns[0] + scrollerWidth;
            xPosns[3] = xPosns[1];

            g.fillPolygon(xPosns, yPosns, 4);

        }

    }

    public Color getScrollbarColour() {
        return scrollbarColour;
    }

    public void setScrollbarColour(Color scrollbarColour) {
        this.scrollbarColour = scrollbarColour;
    }

}
