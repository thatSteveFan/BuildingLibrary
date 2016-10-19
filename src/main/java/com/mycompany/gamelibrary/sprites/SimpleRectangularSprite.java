/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.sprites;

import javafx.scene.image.Image;

/**
 *
 * @author pramukh
 */
public class SimpleRectangularSprite extends RectangularSprite
{

    public SimpleRectangularSprite(double width, double height)
    {
        this(0, 0, width, height);
    }

    public SimpleRectangularSprite(double x, double y, double width, double height)
    {
        this(x, y, width, height, RectangularSprite.DEFAULT_ANGLE);
    }

    public SimpleRectangularSprite(double x, double y, double width, double height, double angle)
    {
        this(x, y, width, height, angle, null);
    }

    public SimpleRectangularSprite(double x, double y, double width, double height, double angle, Image i)
    {
        super(x, y, width, height, angle);
        internalImageProperty().set(i);
    }
}
