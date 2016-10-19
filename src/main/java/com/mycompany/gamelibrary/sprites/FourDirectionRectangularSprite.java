/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.sprites;

import static com.mycompany.gamelibrary.sprites.Direction.DOWN;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * A sprite with four images for the when moving four different directions
 *
 * @author pramukh
 */
public class FourDirectionRectangularSprite extends RectangularSprite
{

    public static final int DEFAULT_HEIGTH = 50;
    public static final int DEFAULT_WIDTH = 50;
    public static final Direction DEFAULT_DIRECTION = DOWN;

    private final ObjectProperty<Image> leftImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> rightImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> upImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> downImage = new SimpleObjectProperty<>();
    private Image currentImage;
    private final ObjectProperty<Direction> currentDirection = new SimpleObjectProperty<>();

    /**
     * takes the first four images from the list and uses them for the
     * constructor
     *
     * @param list
     * @throws IllegalArgumentException
     */
    public FourDirectionRectangularSprite(List<? extends Image> list) throws IllegalArgumentException
    {
        this(list.get(0), list.get(1), list.get(2), list.get(3));

    }

    public FourDirectionRectangularSprite(Image left, Image right, Image up, Image down)
    {
        this(DEFAULT_WIDTH, DEFAULT_HEIGTH, left, right, up, down);
    }

    public FourDirectionRectangularSprite(double width, double height, Image left, Image right, Image up, Image down)
    {
        this(width, height, DEFAULT_DIRECTION, left, right, up, down);
    }

    public FourDirectionRectangularSprite(double width, double height, Direction startingDirection, Image left, Image right, Image up, Image down)
    {
        this(0, 0, width, height, startingDirection, left, right, up, down);
    }

    public FourDirectionRectangularSprite(double x, double y, double width, double height, Direction startingDirection, Image left, Image right, Image up, Image down)
    {
        this(x, y, width, height, DEFAULT_ANGLE, startingDirection, left, right, up, down);
    }

    @SuppressWarnings(value = "OverridableMethodCallInConstructor")
    public FourDirectionRectangularSprite(double x, double y, double width, double height, double angle, Direction startingDirection, Image left, Image right, Image up, Image down)
    {
        super(x, y, width, height, angle);
        currentDirection.set(startingDirection);
        leftImage.setValue(left);
        rightImage.setValue(right);
        upImage.setValue(up);
        downImage.setValue(down);

        super.internalImageProperty().bind(toImageProperty(currentDirection));

    }

    private ObjectBinding<Image> toImageProperty(ObjectProperty<Direction> prop)
    {
        return Bindings.createObjectBinding(()
                -> 
                {
                    if (prop.getValue() == null)
                    {
                        return currentImage;
                    }
                    switch (prop.getValue())
                    {
                        case UP:

                            return currentImage = upImage.getValue();
                        case DOWN:
                            return currentImage = downImage.getValue();
                        case LEFT:
                            return currentImage = leftImage.getValue();
                        case RIGHT:
                            return currentImage = rightImage.getValue();
                        default:
                            throw new IllegalArgumentException("invalid direction" + prop.getValue());
                    }
        }, prop);
    }

    public Direction getDirection()
    {
        return currentDirection.get();
    }

    public void setDirection(Direction d)
    {
        currentDirection.setValue(d);
    }

    public ObjectProperty<Direction> directionProperty()
    {
        return currentDirection;
    }

    @Override
    public Image getImage()
    {
        return toImageProperty(currentDirection).get();
    }
}
