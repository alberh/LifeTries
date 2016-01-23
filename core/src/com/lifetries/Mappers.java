package com.lifetries;

import com.badlogic.ashley.core.ComponentMapper;
import com.lifetries.components.ColorComponent;
import com.lifetries.components.PositionComponent;
import com.lifetries.components.TargetPositionComponent;
import com.lifetries.components.AnimationComponent;
import com.lifetries.components.VelocityComponent;

public abstract class Mappers {
    
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<TargetPositionComponent> targetPosition = ComponentMapper.getFor(TargetPositionComponent.class);
    public static final ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<ColorComponent> color = ComponentMapper.getFor(ColorComponent.class);
}
