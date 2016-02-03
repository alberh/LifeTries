package com.lifetries.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.lifetries.Mappers;
import com.lifetries.components.EnergyComponent;
import com.lifetries.components.StateComponent;

public class EnergySystem extends IteratingSystem {

    public EnergySystem() {
        super(
                Family.all(
                        EnergyComponent.class,
                        StateComponent.class
                ).get(),
                4
        );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        EnergyComponent energy = Mappers.energy.get(entity);
        StateComponent state = Mappers.state.get(entity);

        if (state.hasEnergy) {
            energy.currentEnergy
                    = Math.max(energy.currentEnergy - energy.energyLoss * deltaTime, 0);
        } else if (state.isChargingEnergy) {
            energy.currentEnergy
                    = Math.min(energy.currentEnergy + 3 * energy.energyLoss, energy.energyMax);
        }
    }
}
