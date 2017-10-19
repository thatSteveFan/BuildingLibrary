/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.sprite;

/**
 * An enum for the four directions, used by various sprites
 * @author pramukh
 */
public enum Direction
{
    UP, DOWN, LEFT, RIGHT;
    
    public Direction opposite()
    {
        switch (this)
        {
            case UP: return DOWN;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            case DOWN: return UP;
        }
        throw new IllegalArgumentException();
    }
}
