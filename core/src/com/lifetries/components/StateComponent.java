package com.lifetries.components;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {

    public float lastCheckTime = 0;
    public float checkTime = 1;
    
    public boolean isMoving;
    public boolean isRunning;
    public boolean wantsToLook;
    
    public boolean hasEnergy;
    public boolean isChargingEnergy;
}
