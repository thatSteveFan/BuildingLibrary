/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game;

import com.mycompany.gamelibrary.sprites.Sprite;
import javafx.scene.shape.Shape;


/**
 * A spot that triggers an event when a Sprite steps on it.
 * @author Pramukh
 * @param <T> The shape of the spot
 */
public abstract class TriggerSpot<T extends Shape>    
{
    public abstract T getShape();
    
    abstract SpriteSteppedEvent trigger(Sprite s);
            
}
