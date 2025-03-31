package com.guy7cc.voxelodyssey.core.gui.title;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface TitleBridge extends Tickable {
    @Override
    default void tick(int globalTick) {
        Tickable.super.tick(globalTick);
        Collection<TitleComponent> components = getComponents();
        Component component = Component.empty();
        for (var titleComponent : getComponents()) {
            component = component.append(titleComponent.getPaperComponent());
        }
        getPlayer().showTitle(Title.title(component, Component.empty()));
        components.removeIf(TitleComponent::shouldBeRemoved);
    }

    default Collection<? extends Tickable> getTickables() {
        return getComponents();
    }

    Player getPlayer();

    Collection<TitleComponent> getComponents();

    void addComponent(TitleComponent component);

    void removeComponent(TitleComponent component);

    void setVisible(boolean visible);
}
