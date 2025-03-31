package com.guy7cc.voxelodyssey.core.gui.title;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TitleBridgeImpl implements TitleBridge {
    private final Player player;
    private final Set<TitleComponent> components = new HashSet<>();
    private boolean visible;

    public TitleBridgeImpl(Player player, boolean visible){
        this.player = player;
        this.visible = visible;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Collection<TitleComponent> getComponents() {
        return components;
    }

    @Override
    public void addComponent(TitleComponent component) {
        components.add(component);
    }

    @Override
    public void removeComponent(TitleComponent component) {
        components.remove(component);
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
