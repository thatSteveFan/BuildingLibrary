/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.sprite;

import java.util.Arrays;


/**
 *
 * @author Pramukh
 */
public class Movement
{
    
    public static final Movement NOT_MOVING = new Movement(Direction.DOWN, 0);

    private Direction[] directions;
    private double speed;
    
    public Movement(Direction direction, double speed)
    {
        directions = new Direction[1];
        directions[0] = direction;
    }
    
    public Movement(Direction[] directions, double speed)
    {
        if(directions.length > 2)
        {
            throw new IllegalArgumentException("A Movement can only be in two directions");
        }
        else if(directions.length == 2)
        {
            if(directions[0].opposite() == directions[1])
            {
                throw new IllegalArgumentException("2 opposite directions");
            }
            if(directions[0] == directions[1])
            {
                throw new IllegalArgumentException("two of the same direction");
            }
        }
        else
        {
            this.directions = directions;
            this.speed = speed;
        }
    }
    
    
    public Direction[] getDirections()
    {
        return Arrays.copyOf(directions, 2);
    }
    
    public double getSpeed()
    {
        return speed;
    }
}
