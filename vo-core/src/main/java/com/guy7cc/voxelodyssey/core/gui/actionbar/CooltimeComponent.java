package com.guy7cc.voxelodyssey.core.gui.actionbar;

import com.guy7cc.voxelodyssey.core.item.CoolDown;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class CooltimeComponent implements ActionBarComponent {
    private final Player player;
    private final CoolDown coolDown;

    public CooltimeComponent(Player player, CoolDown coolDown){
        this.player = player;
        this.coolDown = coolDown;
    }

    @Override
    public int getRank() {
        return 1;
    }

    @Override
    public Component getPaperComponent() {
        return coolDown.getPaperComponentForActionBar(player);
    }

    @Override
    public boolean shouldSendMessage() {
        return coolDown.shouldSendMessage(player);
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }

    @Override
    public void tick(int globalTick) {

    }
}
