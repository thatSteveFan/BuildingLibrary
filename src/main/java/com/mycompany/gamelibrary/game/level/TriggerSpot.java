/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.level;

import com.mycompany.gamelibrary.game.sprite.Sprite;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.shape.Shape;


/**
 * A spot that triggers an event when a Sprite steps on it.
 *
 * @author Pramukh
 * @param <T> The shape of the spot
 * @param <E>
 */
public abstract class TriggerSpot<T extends Shape, E extends SpriteSteppedEvent>
{

    private final T baseShape;
    private List<EventHandler<? extends E>> handlers;

    final Level containingLevel;

    public TriggerSpot(T shape)
    {
        this(shape, null);
    }

    public TriggerSpot(T shape, Level level)
    {
        baseShape = shape;
        this.containingLevel = level;
    }

    public T getShape()
    {
        return baseShape;
    }

    protected abstract void stepped(Sprite<? extends Shape> s);

    public void addListener(EventHandler<? extends E> handler)
    {
        handlers.add(handler);
    }
    
    public void removeListener(EventHandler<? extends E> handler)
    {
        handlers.remove(handler);
    }
    
    protected List<EventHandler<? extends E>> getListeners()
    {
        return handlers;
    }

}
