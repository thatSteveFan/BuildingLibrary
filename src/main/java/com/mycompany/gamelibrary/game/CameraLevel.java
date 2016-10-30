/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game;

import javafx.beans.property.*;
import javafx.scene.PerspectiveCamera;


/**
 *
 * @author Pramukh
 */
public class CameraLevel extends Level
{
        private final DoubleProperty angle = new SimpleDoubleProperty();
    ObjectProperty<PerspectiveCamera> camera = new SimpleObjectProperty<>();
    
    public static final double DEFAULT_CAMERA_ANGLE = 70;
    
    
    
    public CameraLevel()
    {
        PerspectiveCamera tempCam = new PerspectiveCamera();
        
        
        
        camera.setValue(tempCam);
    }
    
        public PerspectiveCamera getCamera()
    {
        return camera.get();
    }
    
    public void setCamera(PerspectiveCamera cam)
    {
        getChildren().remove(camera.get());
        camera.setValue(cam);
    }
    
    public ObjectProperty<PerspectiveCamera> cameraProperty()
    {
        return camera;
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
    
    
}
