/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.level;

import com.mycompany.gamelibrary.buildingLibrary.RectangularBuilding;
import com.mycompany.gamelibrary.buildingLibrary.RectangularBuildingBuilder;
import com.mycompany.gamelibrary.game.sprite.Direction;
import com.mycompany.gamelibrary.game.sprite.FourDirectionRectangularSprite;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.PerspectiveCamera;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import static com.mycompany.gamelibrary.game.sprite.Direction.*;



/**
 *
 * @author Pramukh
 */
public class DebuggingLevel extends CameraLevel<PerspectiveCamera>
{

    public static final Image DEFAULT_SPRITE_IMAGE = new Image(
            "https://www.petfinder.com/wp-content/uploads/2012/11/140272627-grooming-needs-senior-cat-632x475.jpg");
    public static final Image DEFAULT_BUILDING_IMAGE = new Image(
            "http://www.politicalmetaphors.com/wp-content/uploads/2015/04/blog-shapes-square-windows.jpg");

    private final DoubleProperty buildingAngle = new SimpleDoubleProperty();
    private final DoubleProperty buildingWidth = new SimpleDoubleProperty();
    private final DoubleProperty buildingHeight = new SimpleDoubleProperty();

    
    private FourDirectionRectangularSprite player;

    private DoubleProperty xShift = new SimpleDoubleProperty(0);
    private DoubleProperty yShift = new SimpleDoubleProperty(0);

    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DebuggingLevel()
    {
        super(new PerspectiveCamera(true));

        
        
        displayDebug(1000, 10);

        player = new FourDirectionRectangularSprite(
                500, 500, 100, 70, 20, Direction.DOWN, DEFAULT_SPRITE_IMAGE,
                DEFAULT_SPRITE_IMAGE, DEFAULT_SPRITE_IMAGE, DEFAULT_SPRITE_IMAGE);

        player.setPrefWidth(50);
        player.setPrefHeight(50 * 475 / 632.0);

//        player.translateZProperty().bind(buildingAngleProperty().multiply(
//                50 * 475)
//                .divide(632.0).negate());
        player.translateYProperty().bind(yShift);
        player.translateXProperty().bind(xShift);
        player.angleProperty().bind(angleProperty());
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



        cameraProperty().get().translateXProperty().bind(xShift);
        cameraProperty().get().translateYProperty().bind(yShift);
//        cameraProperty().get().translateYProperty().bind(cameraDistanceProperty().multiply(sin).add(yShift.multiply(cos)));//.multiply(cos)));
//        sin.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
//        {
//            System.out.printf("sin: %f, yTransate: %f\n", newValue, cameraProperty().get().getTranslateY());
//        });
        cameraProperty().get().translateZProperty().bind(cameraDistanceProperty().negate().divide(cos));//.add(yShift.multiply(sin)));//.multiply(sin));
        cameraProperty().get().setFarClip(50_000);
        cameraProperty().get().setNearClip(2);
        

//        Rotate cameraRotate = new Rotate(Double.NaN, Rotate.X_AXIS);
//        cameraRotate.angleProperty().bind(angleProperty());
//        cameraRotate.pivotXProperty().bind(camera.get().translateXProperty());
//        cameraRotate.pivotYProperty().bind(camera.get().translateYProperty());
//        cameraRotate.pivotZProperty().bind(camera.get().translateZProperty());

        Rotate cameraRotate = new Rotate(Double.NaN, Rotate.X_AXIS);
        cameraRotate.angleProperty().bind(angleProperty());
        cameraRotate.pivotXProperty().bind(player.translateXProperty());
        cameraRotate.pivotYProperty().bind(player.translateYProperty());
        cameraRotate.pivotZProperty().bind(player.translateZProperty().add(cameraDistanceProperty()));

        cameraProperty().get().getTransforms().add(cameraRotate);

    }

    @Override
    public void tick()
    {
        super.tick();
        if(upKeyDown.getValue() || wKeyDown.getValue())
        {
            yShift.set(yShift.get() - 1);
        }
        if(downKeyDown.getValue() || sKeyDown.getValue())
        {
            yShift.set(yShift.get() + 1);
        }
        if(leftKeyDown.getValue() || aKeyDown.getValue())
        {
            xShift.set(xShift.get() - 1);
        }
        if(rightKeyDown.getValue() || dKeyDown.getValue())
        {
            xShift.set(xShift.get() + 1);
        }
        //System.out.println(leftKeyDown.get());
              
//        System.out.printf("player x,y,z: %f, %f, %f\n", player.getTranslateX(),
//                          player.getTranslateY(), player.getTranslateZ());
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



}
