package com.guy7cc.voxelodyssey.core.gui.actionbar;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface ActionBarComponent extends Tickable, Comparable<ActionBarComponent> {
    @Override
    default int compareTo(@NotNull ActionBarComponent o){
        return o.getRank() - this.getRank();
    }

    int getRank();

    Component getPaperComponent();

    boolean shouldSendMessage();

    boolean shouldBeRemoved();
}
