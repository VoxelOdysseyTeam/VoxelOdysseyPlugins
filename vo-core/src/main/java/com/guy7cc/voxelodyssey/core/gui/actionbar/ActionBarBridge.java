package com.guy7cc.voxelodyssey.core.gui.actionbar;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.SortedSet;

public interface ActionBarBridge extends Tickable {
    @Override
    default void tick(int globalTick) {
        Tickable.super.tick(globalTick);
        SortedSet<ActionBarComponent> components = getComponents();
        if(getVisible()){
            for(var component : getComponents()){
                if(component.shouldSendMessage()){
                    getPlayer().sendMessage(component.getPaperComponent());
                    break;
                }
            }
        }
        components.removeIf(ActionBarComponent::shouldBeRemoved);
    }

    @Override
    default Collection<? extends Tickable> getTickables(){
        return getComponents();
    }

    Player getPlayer();

    SortedSet<ActionBarComponent> getComponents();

    void addComponent(ActionBarComponent component);

    void removeComponent(ActionBarComponent component);

    boolean getVisible();

    void setVisible(boolean visible);
}
