package com.guy7cc.voxelodyssey.game.entity;

import com.guy7cc.voxelodyssey.core.entity.VOEntity;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public interface VODamageable<T extends LivingEntity> extends VOEntity<T> {
    double getHp();

    void setHp(double hp);

    double getMaxHp();

    void setMaxHp(double maxHp);

    default void damage(double damage){
        setHp(Math.max(0, getHp()) - damage);
        applyHp();
    }

    default void damageSilent(double damage){
        setHp(Math.max(0, getHp()) - damage);
        applyHpSilent();
    }

    default void applyHp() {
        LivingEntity handle = getHandle();
        double hp = getHp();
        double maxHp = getMaxHp();
        double currentMaxHp = handle.getAttribute(Attribute.MAX_HEALTH).getValue();
        double displayHp = currentMaxHp * hp / maxHp;
        if (displayHp < handle.getHealth()) {
            handle.damage(handle.getHealth() - displayHp);
        } else if (displayHp > handle.getHealth()) {
            handle.heal(displayHp - handle.getHealth());
        }
    }

    default void applyHpSilent() {
        LivingEntity handle = getHandle();
        double hp = getHp();
        double maxHp = getMaxHp();
        double currentMaxHp = handle.getAttribute(Attribute.MAX_HEALTH).getValue();
        double displayHp = currentMaxHp * hp / maxHp;
        handle.setHealth(displayHp);
    }
}
