/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game;

import com.mycompany.gamelibrary.sprites.Sprite;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.shape.Shape;


/**
 *
 * @author Pramukh
 */
public class SpriteSteppedEvent extends Event
{
    
    Sprite<? extends Shape> sprite;
    public SpriteSteppedEvent(EventType<? extends SpriteSteppedEvent> eventType, Sprite<? extends Shape> sprite)
    {
        super(eventType);
        this.sprite = sprite;
    }
    
    
    
}
