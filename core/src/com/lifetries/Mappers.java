package com.lifetries;

import com.badlogic.ashley.core.ComponentMapper;
import com.lifetries.component.ColorComponent;
import com.lifetries.component.PositionComponent;
import com.lifetries.component.TargetPositionComponent;
import com.lifetries.component.AnimationComponent;
import com.lifetries.component.VelocityComponent;

public abstract class Mappers {
    
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<TargetPositionComponent> targetPosition = ComponentMapper.getFor(TargetPositionComponent.class);
    public static final ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<ColorComponent> color = ComponentMapper.getFor(ColorComponent.class);
}
