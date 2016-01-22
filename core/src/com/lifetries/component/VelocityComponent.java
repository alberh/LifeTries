package com.lifetries.component;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {

    public enum Direction {
        Left, Up, Right, Down
    }
    
    public float x = 0;
    public float y = 0;
    public boolean isMoving = false;
    public float speed = 100;
    public Direction lastDirection = Direction.Down;
}
