/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.level;

import eu.lestard.advanced_bindings.api.MathBindings;
import javafx.beans.property.*;
import javafx.scene.Camera;
import javafx.scene.Group;


/**
 *
 * @author Pramukh
 * @param <T> The type of camera
 */
public abstract class CameraLevel<T extends Camera> extends Level
{

    private Group cameraOwner;
    private double counter = 0;
    
    private final DoubleProperty angle = new SimpleDoubleProperty();
    ObjectProperty<T> camera = new SimpleObjectProperty<>();

    public static final double DEFAULT_CAMERA_ANGLE = 70;
    public static final double DEFAULT_CAMERA_DISTANCE = 500;

    protected final DoubleProperty sin = new SimpleDoubleProperty();
    {
        sin.bind(MathBindings.sin(MathBindings.toRadians(angle)));
    }
    protected final DoubleProperty cos = new SimpleDoubleProperty();
    {
        cos.bind(MathBindings.cos(MathBindings.toRadians(angle)));
    }

    public final DoubleProperty cameraDistance = new SimpleDoubleProperty(
            DEFAULT_CAMERA_DISTANCE);

    protected CameraLevel(T c)
    {
        //PerspectiveCamera tempCam = new PerspectiveCamera(true);

        
//        Rotate cameraRotate = new Rotate(Double.NaN, Rotate.X_AXIS);
//        cameraRotate.pivotXProperty().bind(tempCam.translateXProperty());
//        cameraRotate.pivotYProperty().bind(tempCam.translateYProperty());
//        cameraRotate.pivotZProperty().bind(tempCam.translateZProperty());
//        
//        cameraRotate.angleProperty().bind(angle);
//        tempCam.getTransforms().add(cameraRotate);
        
        cameraOwner = new Group(c);
        
        camera.setValue(c);
    }

    @Override
    public void tick()
    {
        super.tick();
//        counter += 0.01;
//        cameraOwner.setTranslateY(100 * Math.sin(counter));
    }
    
    public T getCamera()
    {
        return camera.get();
    }

    public final void setAngle(Double value)
    {
        angle.set(value);
    }

    public final Double getAngle()
    {
        return angle.get();
    }

    public final DoubleProperty angleProperty()
    {
        return angle;
    }

    public final void setCameraDistance(Double value)
    {
        cameraDistance.set(value);
    }

    public final Double getCameraDistance()
    {
        return cameraDistance.get();
    }

    public final DoubleProperty cameraDistanceProperty()
    {
        return cameraDistance;
    }

    public void setCamera(T cam)
    {
        getChildren().remove(camera.get());
        camera.setValue(cam);
    }

    public ObjectProperty<T> cameraProperty()
    {
        return camera;
    }

}
