package com.guy7cc.voxelodyssey.game.entity;

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import com.guy7cc.voxelodyssey.game.system.VOElement;
import com.guy7cc.voxelodyssey.game.system.VOElementalVector;
import org.bukkit.entity.LivingEntity;

public interface VODamageNotifiable<T extends LivingEntity> extends VODamageable<T> {
    default void notifyDamage(VOElementalVector v){
        for (VOElement element : VOElement.values()) {
            double damage = v.get(element);
            if (damage <= 1e-3) continue;
            VoxelOdysseyCore.spawn(VOEntityTypes.DAMAGE_NUM, new VODamageNum.FactoryArgs(damage, element, getHandle().getEyeLocation()));
        }
    }
}
