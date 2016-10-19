/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.sprites;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author pramukh
 */
public class SimpleRectangularSpriteBuilder extends SpriteBuilder<Rectangle>
{

    private final DoubleProperty x = new SimpleDoubleProperty();
    private final DoubleProperty y = new SimpleDoubleProperty();
    private final DoubleProperty width = new SimpleDoubleProperty();
    private final DoubleProperty height = new SimpleDoubleProperty();
    private final DoubleProperty angle = new SimpleDoubleProperty();
    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();

    public SimpleRectangularSpriteBuilder(double width, double height)
    {
        this(0, 0, width, height);
    }

    
    public SimpleRectangularSpriteBuilder(double x, double y, double width, double height)
    {
        this(x, y, width, height, SimpleRectangularSprite.DEFAULT_ANGLE);
    }
    
    public SimpleRectangularSpriteBuilder(double x, double y, double width, double height, double angle)
    {
        this(x, y, width, height, angle, null);
    }
    
    public SimpleRectangularSpriteBuilder(double x, double y, double width, double height, double angle, Image image)
    {
        this.x.set(x);
        this.y.set(y);
        this.angle.set(angle);
        this.width.set(width);
        this.height.set(height);
        this.image.set(image);
    }
    
    
    
    public double getX()
    {
        return x.get();
    }

    public void setX(double value)
    {
        x.set(value);
    }

    public DoubleProperty xProperty()
    {
        return x;
    }

    public double getY()
    {
        return y.get();
    }

    public void setY(double value)
    {
        y.set(value);
    }

    public DoubleProperty yProperty()
    {
        return y;
    }

    public double getWidth()
    {
        return width.get();
    }

    public void setWidth(double value)
    {
        width.set(value);
    }

    public DoubleProperty widthProperty()
    {
        return width;
    }

    public double getHeight()
    {
        return height.get();
    }

    public void setHeight(double value)
    {
        height.set(value);
    }

    public DoubleProperty heightProperty()
    {
        return height;
    }

    public Image getImage()
    {
        return image.get();
    }

    public void setImage(Image value)
    {
        image.set(value);
    }

    public ObjectProperty<Image> imageProperty()
    {
        return image;
    }
    
    public double getAngle()
    {
        return angle.get();
    }
    
    public void setAngle(double angle)
    {
        this.angle.set(angle);
    }
    
    public DoubleProperty angleProperty()
    {
        return angle;
    }

    @Override
    public SimpleRectangularSprite build()
    {
        return new SimpleRectangularSprite(x.get(), y.get(), width.get(), height.get(), angle.get(), image.get());
    }

}
