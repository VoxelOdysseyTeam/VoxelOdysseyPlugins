package com.guy7cc.voxelodyssey.core.gui.sidebar;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SimpleSidebarComponent implements SidebarComponent {
    private int lastTick = -1;
    private int score;
    private String text = "";
    private final Set<SidebarBridge> containers = new HashSet<>();

    @Override
    public void tick(int globalTick) {
        if (globalTick > lastTick) internalTick(globalTick);
        lastTick = globalTick;
    }

    protected void internalTick(int globalTick) {

    }

    @Override
    public Collection<SidebarBridge> getContainers() {
        return Collections.unmodifiableCollection(containers);
    }

    @Override
    public void addContainer(SidebarBridge bridge) {
        containers.add(bridge);
    }

    @Override
    public void removeContainer(SidebarBridge bridge) {
        containers.remove(bridge);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        if (!this.text.equals(text)) {
            refresh();
        }
        this.text = text;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        if (this.score != score) {
            refresh();
        }
        this.score = score;
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }
}
