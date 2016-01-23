package com.lifetries.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.lifetries.Mappers;
import com.lifetries.components.EnergyComponent;
import com.lifetries.components.VelocityComponent;

public class EnergySystem extends IteratingSystem {

    public EnergySystem() {
        super(
                Family.all(
                        EnergyComponent.class,
                        VelocityComponent.class
                ).get(),
                3
        );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        EnergyComponent energy = Mappers.energy.get(entity);
        VelocityComponent velocity = Mappers.velocity.get(entity);
        
        energy.currentEnergy -= energy.energyLoss * deltaTime;
        if (energy.currentEnergy < 0) {
            energy.currentEnergy = 0;
            velocity.isMoving = false;
        }
    }

}
