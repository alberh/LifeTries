package com.lifetries;

import com.badlogic.ashley.core.ComponentMapper;
import com.lifetries.actors.EntityActorComponent;
import com.lifetries.components.*;

public abstract class Mappers {

    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<TargetPositionComponent> targetPosition = ComponentMapper.getFor(TargetPositionComponent.class);
    public static final ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<ColorComponent> color = ComponentMapper.getFor(ColorComponent.class);
    public static final ComponentMapper<EnergyComponent> energy = ComponentMapper.getFor(EnergyComponent.class);
    public static final ComponentMapper<StateComponent> state = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<EntityActorComponent> actor = ComponentMapper.getFor(EntityActorComponent.class);
}
