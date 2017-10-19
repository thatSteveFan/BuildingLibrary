/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.UI;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


/**
 *
 * @author Pramukh
 */
public class FrameCounter extends AnimationTimer
{

    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    private final DoubleProperty fps = new SimpleDoubleProperty();
    
    @Override
    public void handle(long now)
    {
        long oldFrameTime = frameTimes[frameTimeIndex];
        frameTimes[frameTimeIndex] = now;
        frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
        if(frameTimeIndex == 0)
        {
            arrayFilled = true;
        }
        if(arrayFilled)
        {
            long elapsedNanos = now - oldFrameTime;
            long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
            fps.setValue(1_000_000_000.0 / elapsedNanosPerFrame);
            //fps.setValue(String.format("Current frame rate: %.1f", frameRate));
        }
    }

    public final void setFps(Double value)
    {
        fps.set(value);
    }

    public final Double getFps()
    {
        return fps.get();
    }

    public final DoubleProperty fpsProperty()
    {
        return fps;
    }
    
    
    
}
