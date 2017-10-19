/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.level;

import com.mycompany.gamelibrary.game.sprite.Sprite;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.shape.Shape;


/**
 *
 * @author Pramukh
 */
public class SpriteSteppedEvent extends Event
{
    
    final Sprite<? extends Shape> sprite;
    
    public static final EventType<SpriteSteppedEvent> SPRITE_STEPPED = new EventType<>(ANY, "SPRITE_STEPPED");
    
    public SpriteSteppedEvent(EventType<? extends SpriteSteppedEvent> eventType, Sprite<? extends Shape> sprite)
    {
        super(eventType);
        this.sprite = sprite;
    }
    
    public Sprite<? extends Shape> getSprite()
    {
        return sprite;
    }
    
    
}
