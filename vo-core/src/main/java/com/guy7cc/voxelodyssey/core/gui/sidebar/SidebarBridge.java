package com.guy7cc.voxelodyssey.core.gui.sidebar;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface SidebarBridge extends Tickable {
    @Override
    default void tick(int globalTick){
        Tickable.super.tick(globalTick);
        getComponents().removeIf(SidebarComponent::shouldBeRemoved);
    }

    @Override
    default Collection<? extends Tickable> getTickables() {
        return getComponents();
    }

    Player getPlayer();

    Collection<SidebarComponent> getComponents();

    void addComponent(SidebarComponent component);

    void setComponent(SidebarComponent component, int index);

    void removeComponent(SidebarComponent component);

    void refreshComponent(SidebarComponent component);

    default void refreshAll() {
        getComponents().forEach(this::refreshComponent);
    }

    void setDisplay(Component display);

    void setVisible(Boolean visible);

    void dispose();
}
