/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.sprite;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 * Provides the implementation for the rectangular sprite, so long as the property
 * Image is defined
 * @author pramukh
 */
public abstract  class RectangularSprite extends Sprite<Rectangle>
{

    public static final double DEFAULT_ANGLE = 25;
    
    //private final BooleanProperty strech = new SimpleBooleanProperty();
    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();

    
    
    
    public RectangularSprite(double width, double height)
    {
        this(0, 0, width, height);
    }

    public RectangularSprite(double x, double y, double width, double height)
    {
        this(x, y, width, height, DEFAULT_ANGLE);
    }

    public RectangularSprite(double x, double y, double width, double height, double angle)
    {
        super(x, y, angle);
        prefWidthProperty().set(width);
        prefHeightProperty().set(height);
        
        ImageView sprite = new ImageView();
        sprite.setPreserveRatio(false);
        sprite.fitHeightProperty().bind(heightProperty());
        sprite.fitWidthProperty().bind(widthProperty());
        sprite.imageProperty().bind(image);
        
        Group container = new Group(sprite);
        this.getChildren().add(container);
        
        Rotate rotate = new Rotate(Double.NaN, Rotate.X_AXIS);
        rotate.angleProperty().bind(angleProperty());
        rotate.pivotYProperty().bind(heightProperty());
        //Translate zShift = new Translate();
//        zShift.zProperty().bind(heightProperty().multiply(MathBindings.sin(angleProperty())).negate());
        container.getTransforms().addAll(rotate);
        //container.translateZProperty().bind(heightProperty().negate().multiply(MathBindings.cos(MathBindings.toRadians(angleProperty()))));
    }
    
    
    
    
    
    
    
    
    
    
    public Image getImage()
    {
        return image.get();
    }

    protected void setImage(Image value)
    {
        image.set(value);
    }

    protected ObjectProperty<Image> internalImageProperty()
    {
        return image;
    }

    public ReadOnlyObjectProperty<Image> imageProperty()
    {
        return image;
    }
    
    
    
//    public boolean isStrech()
//    {
//        return strech.get();
//    }
//
//    public void setStrech(boolean value)
//    {
//        strech.set(value);
//    }
//
//    public BooleanProperty strechProperty()
//    {
//        return strech;
//    }

    @Override
    public Rectangle getBoundingBox()
    {
        return new Rectangle(getTranslateX(), getTranslateY(), getPrefHeight(), getPrefHeight());
    }
    
}
