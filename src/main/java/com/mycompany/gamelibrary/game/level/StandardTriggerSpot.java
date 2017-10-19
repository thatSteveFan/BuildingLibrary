/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.level;

import com.mycompany.gamelibrary.game.sprite.Sprite;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



/**
 *
 * @author Pramukh
 */
public class StandardTriggerSpot extends TriggerSpot<Rectangle, SpriteSteppedEvent>
{

    public StandardTriggerSpot(Rectangle shape)
    {
        super(shape);
    }

    @Override
    protected void stepped(Sprite<? extends Shape> s)
    {
//        for(EventHandler<? extends SpriteSteppedEvent> handler : getListeners())
//        {
//            handler.handle(new SpriteSteppedEvent(SpriteSteppedEvent.SPRITE_STEPPED, s));
//        }
    }
    
}
