package com.lifetries;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.lifetries.components.StateComponent;

public class SelectionManager {

    public Array<Entity> selected;

    public SelectionManager() {
        selected = new Array<Entity>();
    }

    public void selectOne(Entity entity) {
        StateComponent state;

        
        Gdx.app.log("SelectionManager", ":" + selected.size);
        if (selected.size > 0) {
            for (int i = 0; i < selected.size; i++) {
                state = Mappers.state.get(selected.get(i));
                state.isSelected = false;
            }
            selected.clear();
        }
        selected.add(entity);
        Gdx.app.log("SelectionManager", ":" + selected.size);

        state = Mappers.state.get(entity);
        state.isSelected = true;
    }
    
    public void deselect() {
        selected.clear();
    }
}
