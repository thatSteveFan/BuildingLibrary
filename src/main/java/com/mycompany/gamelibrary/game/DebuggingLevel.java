/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game;

import com.mycompany.gamelibrary.buildingLibrary.RectangularBuilding;
import com.mycompany.gamelibrary.buildingLibrary.RectangularBuildingBuilder;
import com.mycompany.gamelibrary.sprites.Direction;
import com.mycompany.gamelibrary.sprites.FourDirectionRectangularSprite;
import com.mycompany.gamelibrary.sprites.Sprite;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import static com.mycompany.gamelibrary.sprites.Direction.*;


/**
 *
 * @author Pramukh
 */
public class DebuggingLevel extends CameraLevel
{

    public static final Image DEFAULT_SPRITE_IMAGE = new Image(
            "https://www.petfinder.com/wp-content/uploads/2012/11/140272627-grooming-needs-senior-cat-632x475.jpg");
    public static final Image DEFAULT_BUILDING_IMAGE = new Image(
            "http://www.politicalmetaphors.com/wp-content/uploads/2015/04/blog-shapes-square-windows.jpg");

    private final DoubleProperty buildingAngle = new SimpleDoubleProperty();
    private final DoubleProperty buildingWidth = new SimpleDoubleProperty();
    private final DoubleProperty buildingHeight = new SimpleDoubleProperty();

    BooleanProperty upKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty downKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty leftKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty rightKeyDown = new SimpleBooleanProperty(false);

    BooleanProperty wKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty aKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty sKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty dKeyDown = new SimpleBooleanProperty(false);
    private Sprite player;

    private DoubleProperty xShift = new SimpleDoubleProperty(0);
    private DoubleProperty yShift = new SimpleDoubleProperty(0);

    public DebuggingLevel()
    {
        super();

        displayDebug(1000, 10);

        FourDirectionRectangularSprite player = new FourDirectionRectangularSprite(
                500, 500, 100, 70, 20, Direction.DOWN, DEFAULT_SPRITE_IMAGE,
                DEFAULT_SPRITE_IMAGE, DEFAULT_SPRITE_IMAGE, DEFAULT_SPRITE_IMAGE);

        player.setPrefWidth(50);
        player.setPrefHeight(50 * 475 / 632.0);
        Rotate spriteRotate = new Rotate(0, Rotate.X_AXIS);
        spriteRotate.angleProperty().bind(angleProperty());
        player.getTransforms().add(spriteRotate);
        player.translateZProperty().bind(angleProperty().multiply(50 * 475)
                .divide(632.0).negate());
        player.setTranslateX(500);
        player.setTranslateY(600);
        this.addSprite(player);

        RectangularBuildingBuilder builder = new RectangularBuildingBuilder(
                DEFAULT_BUILDING_IMAGE, DEFAULT_BUILDING_IMAGE,
                DEFAULT_BUILDING_IMAGE, DEFAULT_BUILDING_IMAGE,
                RectangularBuilding.DEFAULT_ANGLE, 150, 150, 150, 0, 0);
        for(int i = 0;i < 5;i++)
        {
            builder.setX(i * 200);
            builder.setY(50 * i);
            builder.setWidth(150.0 + 20 * i);
            RectangularBuilding rb = builder.build();
            rb.prefWidthProperty().bind(buildingWidth);
            rb.prefHeightProperty().bind(buildingHeight);

            rb.angleProperty().bind(buildingAngle);

            addBuilding(rb);
        }

        IntegerProperty cameraXShift = new SimpleIntegerProperty(0);
        IntegerProperty cameraYShift = new SimpleIntegerProperty(0);

        //super.setCamera(tempCam);
        player.directionProperty().bind(Bindings.createObjectBinding(()
                -> 
                {
                    if(upKeyDown.getValue()
                            || wKeyDown.getValue())
                    {
                        return UP;
                    }

                    if(downKeyDown.getValue()
                            || sKeyDown.getValue())
                    {
                        return DOWN;
                    }

                    if(leftKeyDown.getValue()
                            || aKeyDown.getValue())
                    {
                        return LEFT;
                    }

                    if(rightKeyDown.getValue()
                            || dKeyDown.getValue())
                    {
                        return RIGHT;
                    }
                    return null;
        }, upKeyDown, dKeyDown, aKeyDown, downKeyDown, leftKeyDown, rightKeyDown,
                                                                     wKeyDown,
                                                                     sKeyDown));

        onKeyReleasedProperty().set(e
                -> 
                {
                    switch(e.getCode())
                    {
                        case UP:
                            upKeyDownProperty().setValue(false);
                            break;
                        case LEFT:
                            leftKeyDownProperty().setValue(false);
                            break;
                        case DOWN:
                            downKeyDownProperty().setValue(false);
                            break;
                        case RIGHT:
                            rightKeyDownProperty().setValue(false);
                            break;
                        case W:
                            wKeyDownProperty().setValue(false);
                            break;
                        case A:
                            aKeyDownProperty().setValue(false);
                            break;
                        case S:
                            sKeyDownProperty().setValue(false);
                            break;
                        case D:
                            dKeyDownProperty().setValue(false);
                            break;
                    }
        });
        onKeyPressedProperty().set((e)
                -> 
                {
                    System.out.println("Key Pressed");

                    switch(e.getCode())
                    {
                        case UP:
                            this.aKeyDownProperty().setValue(true);
                            break;
                        case LEFT:
                            this.leftKeyDownProperty().setValue(true);
                            break;
                        case DOWN:
                            this.downKeyDownProperty().setValue(true);
                            break;
                        case RIGHT:
                            this.rightKeyDownProperty().setValue(true);
                            break;
                        case W:
                            this.wKeyDownProperty().setValue(true);
                            break;
                        case A:
                            this.aKeyDownProperty().setValue(true);
                            break;
                        case S:
                            this.sKeyDownProperty().setValue(true);
                            break;
                        case D:
                            this.dKeyDownProperty().setValue(true);
                            break;
                    }
        });

    }

    public void tick()
    {
        if(upKeyDown.getValue() || wKeyDown.getValue())
        {
            yShift.set(yShift.get() - 1);
            for(Sprite sprite : getSpritesUnmodifiable())
            {
                sprite.setTranslateY(sprite.getTranslateY() - 1);
            }
        }
        if(downKeyDown.getValue() || sKeyDown.getValue())
        {
            yShift.set(yShift.get() + 1);
            for(Sprite sprite : getSpritesUnmodifiable())
            {
                sprite.setTranslateY(sprite.getTranslateY() + 1);
            }
        }
        if(leftKeyDown.getValue() || aKeyDown.getValue())
        {
            xShift.set(xShift.get() - 1);
            for(Sprite sprite : getSpritesUnmodifiable())
            {
                sprite.setTranslateX(sprite.getTranslateX() - 1);
            }
        }
        if(rightKeyDown.getValue() || dKeyDown.getValue())
        {
            xShift.set(xShift.get() + 1);
            for(Sprite sprite : getSpritesUnmodifiable())
            {
                sprite.setTranslateX(sprite.getTranslateX() + 1);
            }
        }
    }

    public final void setBuildingAngle(Double value)
    {
        buildingAngle.set(value);

    }

    public final Double getBuildingAngle()
    {
        return buildingAngle.get();
    }

    public final DoubleProperty buildingAngleProperty()
    {
        return buildingAngle;

    }

    public final void setBuildingWidth(Double value)
    {
        buildingWidth.set(value);
    }

    public final Double getBuildingWidth()
    {
        return buildingWidth.get();
    }

    public final DoubleProperty buildingWidthProperty()
    {
        return buildingWidth;
    }

    public final void setBuildingHeight(Double value)
    {
        buildingHeight.set(value);
    }

    public final Double getBuildingHeight()
    {
        return buildingHeight.get();
    }

    public final DoubleProperty buildingHeightProperty()
    {
        return buildingHeight;
    }

    public final void setUpKeyDown(Boolean value)
    {
        upKeyDown.set(value);
    }

    public final Boolean getUpKeyDown()
    {
        return upKeyDown.get();
    }

    public final BooleanProperty upKeyDownProperty()
    {
        return upKeyDown;
    }

    public final void setDownKeyDown(Boolean value)
    {
        downKeyDown.set(value);
    }

    public final Boolean getDownKeyDown()
    {
        return downKeyDown.get();
    }

    public final BooleanProperty downKeyDownProperty()
    {
        return downKeyDown;
    }

    public final void setLeftKeyDown(Boolean value)
    {
        leftKeyDown.set(value);
    }

    public final Boolean getLeftKeyDown()
    {
        return leftKeyDown.get();
    }

    public final BooleanProperty leftKeyDownProperty()
    {
        return leftKeyDown;
    }

    public final void setRightKeyDown(Boolean value)
    {
        rightKeyDown.set(value);
    }

    public final Boolean getRightKeyDown()
    {
        return rightKeyDown.get();
    }

    public final BooleanProperty rightKeyDownProperty()
    {
        return rightKeyDown;
    }

    public final void setWKeyDown(Boolean value)
    {
        wKeyDown.set(value);
    }

    public final Boolean getWKeyDown()
    {
        return wKeyDown.get();
    }

    public final BooleanProperty wKeyDownProperty()
    {
        return wKeyDown;
    }

    public final void setAKeyDown(Boolean value)
    {
        aKeyDown.set(value);
    }

    public final Boolean getAKeyDown()
    {
        return aKeyDown.get();
    }

    public final BooleanProperty aKeyDownProperty()
    {
        return aKeyDown;
    }

    public final void setSKeyDown(Boolean value)
    {
        sKeyDown.set(value);
    }

    public final Boolean getSKeyDown()
    {
        return sKeyDown.get();
    }

    public final BooleanProperty sKeyDownProperty()
    {
        return sKeyDown;
    }

    public final void setDKeyDown(Boolean value)
    {
        dKeyDown.set(value);
    }

    public final Boolean getDKeyDown()
    {
        return dKeyDown.get();
    }

    public final BooleanProperty dKeyDownProperty()
    {
        return dKeyDown;
    }

}
