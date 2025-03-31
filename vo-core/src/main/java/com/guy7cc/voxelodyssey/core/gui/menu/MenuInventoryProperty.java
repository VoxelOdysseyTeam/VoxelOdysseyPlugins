package com.guy7cc.voxelodyssey.core.gui.menu;

import net.kyori.adventure.text.Component;

public class MenuInventoryProperty {
    public final int lineNum;
    public final Component title;
    public final MenuComponent[] components;

    public MenuInventoryProperty(int lineNum, Component title) {
        if (lineNum <= 0 || lineNum > 6) throw new IllegalArgumentException("Menu inventory's line num must be from 1 to 6");
        this.lineNum = lineNum;
        this.title = title;
        this.components = new MenuComponent[lineNum * 9];
    }

    public MenuInventoryProperty setComponent(int index, MenuComponent component) {
        if (0 <= index && index < components.length) {
            components[index] = component;
        }
        return this;
    }
}
