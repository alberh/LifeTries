package com.lifetries.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;

public class StateComponent implements Component {

    public float lastCheckTime = 0;
    public float checkTime = 0.75f + MathUtils.random(1.25f);
    
    public boolean isMoving;
    public boolean isRunning;
    public boolean wantsToLook;
    
    public boolean hasEnergy;
    public boolean isChargingEnergy;
    
    public boolean isSelected;
    public static Entity selectedEntity;
}
