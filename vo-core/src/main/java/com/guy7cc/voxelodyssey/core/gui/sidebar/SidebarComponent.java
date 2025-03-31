package com.guy7cc.voxelodyssey.core.gui.sidebar;

import com.guy7cc.voxelodyssey.core.common.Tickable;

import java.util.Collection;

public interface SidebarComponent extends Tickable {
    Collection<SidebarBridge> getContainers();

    void addContainer(SidebarBridge bridge);

    void removeContainer(SidebarBridge bridge);

    String getText();

    void setText(String text);

    default void refresh() {
        for (SidebarBridge bridge : getContainers()) {
            bridge.refreshComponent(this);
        }
    }

    int getScore();

    void setScore(int score);

    boolean shouldBeRemoved();
}
