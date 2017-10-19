/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.sprite;

import javafx.scene.shape.Shape;

/**
 *
 * @author pramukh
 * @param <T> The type of sprite built
 */
public abstract class SpriteBuilder <T extends Shape>
{
    public abstract Sprite<T> build();
}
