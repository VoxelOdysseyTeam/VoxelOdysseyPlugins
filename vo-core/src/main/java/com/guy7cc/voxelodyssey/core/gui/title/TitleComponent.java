package com.guy7cc.voxelodyssey.core.gui.title;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public interface TitleComponent extends Tickable {
    Component getPaperComponent();

    boolean shouldBeRemoved();

    static Component alignLeft(String text, TitlePosition pos) {
        Component component = Component.empty();
        int offset = 0;
        for (char c : text.toCharArray()) {
            component = component.append(Component.text(c + CharWidth.toSpaceChar(c)).color(TextColor.color(pos.r, offset, 0)));
            offset += CharWidth.toWidth(c);
        }
        return component;
    }

    static Component alignCenter(String text, TitlePosition pos) {
        Component component = Component.empty();
        int offset = 0;
        for (char c : text.toCharArray()) {
            offset += CharWidth.toWidth(c);
        }
        if (CharWidth.spaceCharExists(-offset)) {
            component = component.append(Component.text(CharWidth.toSpaceChar(-offset)));
        } else {
            StringBuilder sb = new StringBuilder();
            for (char c : text.toCharArray()) {
                sb.append(CharWidth.toSpaceChar(c));
            }
            component = component.append(Component.text(sb.toString()));
        }
        return component;
    }
}
