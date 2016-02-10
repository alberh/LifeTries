package com.lifetries;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.lifetries.components.StateComponent;

public class SelectionManager {

    public Array<Entity> selected;

    public SelectionManager() {
        selected = new Array<Entity>();
    }

    public Entity getFirst() {
        if (selected.size > 0) {
            return selected.first();
        } else {
            return null;
        }
    }

    public void selectOne(Entity entity) {
        StateComponent state;

        if (selected.size > 0) {
            for (int i = 0; i < selected.size; i++) {
                state = Mappers.state.get(selected.get(i));
                state.isSelected = false;
            }
            selected.clear();
        }

        state = Mappers.state.get(entity);
        state.isSelected = true;

        selected.add(entity);
    }
}
