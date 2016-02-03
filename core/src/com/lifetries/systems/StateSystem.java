package com.lifetries.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.lifetries.Mappers;
import com.lifetries.components.EnergyComponent;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.StateComponent;
import com.lifetries.components.TargetPositionComponent;

public class StateSystem extends IteratingSystem {

    private StateComponent state;
    private PositionComponent position;
    private TargetPositionComponent targetPosition;
    private EnergyComponent energy;

    public float runProb = 0.05f;
    public float runEnergyProp = 0.35f;
    public float chargeProb = 0.1f;

    public StateSystem() {
        super(
                Family.all(
                        StateComponent.class,
                        PositionComponent.class,
                        TargetPositionComponent.class,
                        EnergyComponent.class
                ).get(),
                0
        );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        state = Mappers.state.get(entity);
        position = Mappers.position.get(entity);
        targetPosition = Mappers.targetPosition.get(entity);
        energy = Mappers.energy.get(entity);

        state.lastCheckTime += deltaTime;

        if (state.lastCheckTime >= state.checkTime) {
            state.lastCheckTime -= state.checkTime;

            energyChecks();
            standChecks();
            movingChecks();
        }
    }

    private void energyChecks() {
        if (state.hasEnergy) {
            if (energy.currentEnergy == 0) {
                state.hasEnergy = false;
            }
        } else if (state.isChargingEnergy && energy.currentEnergy >= energy.energyMax) {
            energy.currentEnergy = energy.energyMax;
            state.hasEnergy = true;
        } else if (MathUtils.randomBoolean(chargeProb)) {
            state.isChargingEnergy = true;
        }
    }

    private void standChecks() {
        if (!state.isMoving && state.hasEnergy) {
            if (MathUtils.randomBoolean(0.33f)) {
                state.wantsToLook = true;
            } else if (MathUtils.randomBoolean(0.2f)) {
                state.wantsToLook = true;
                state.isMoving = true;
            }
        }
    }

    private void movingChecks() {
        if (state.isMoving) {
            if (state.hasEnergy) {
                if (position.x == targetPosition.x && position.y == targetPosition.y) {
                    state.isMoving = false;
                    state.isRunning = false;
                } else if (energy.currentEnergy >= runEnergyProp * energy.energyMax
                        && MathUtils.randomBoolean(runProb)) {
                    state.isRunning = true;
                }
            } else {
                state.isMoving = false;
                state.isRunning = false;
            }
        }
    }
}
