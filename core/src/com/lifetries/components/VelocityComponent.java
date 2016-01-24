package com.lifetries.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {

    public enum Direction {
        Left, Up, Right, Down
    }
    
    public float x = 0;
    public float y = 0;
    public float speed = 100;
    public Direction lastDirection = Direction.Down;
}
