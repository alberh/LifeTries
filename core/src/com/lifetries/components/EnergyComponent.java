package com.lifetries.components;

import com.badlogic.ashley.core.Component;

public class EnergyComponent implements Component {
    
    public float energyMax = 1;
    public float currentEnergy = 1;
    public float energyLoss = 0.1f;
}
