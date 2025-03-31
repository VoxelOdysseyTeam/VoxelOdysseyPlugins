package com.guy7cc.voxelodyssey.core.gui.actionbar;

import org.bukkit.entity.Player;

import java.util.SortedSet;
import java.util.TreeSet;

public class ActionBarBridgeImpl implements ActionBarBridge {
    private final Player player;
    private final SortedSet<ActionBarComponent> components = new TreeSet<>();
    private boolean visible;

    public ActionBarBridgeImpl(Player player, boolean visible){
        this.player = player;
        this.visible = visible;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public SortedSet<ActionBarComponent> getComponents() {
        return components;
    }

    @Override
    public void addComponent(ActionBarComponent component) {
        components.add(component);
    }

    @Override
    public void removeComponent(ActionBarComponent component) {
        components.remove(component);
    }

    @Override
    public boolean getVisible() {
        return this.visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
