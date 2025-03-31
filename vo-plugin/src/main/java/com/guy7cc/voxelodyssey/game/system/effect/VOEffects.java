package com.guy7cc.voxelodyssey.game.system.effect;

import com.guy7cc.voxelodyssey.core.common.Copyable;
import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.core.registry.Registry;
import com.guy7cc.voxelodyssey.core.util.TranslationUtil;
import com.guy7cc.voxelodyssey.game.VOPlugin;
import net.kyori.adventure.text.Component;

public class VOEffects {
    public static final Registry<VOEffect<?>> REGISTRY = new Registry<>(VOPlugin::getPlugin);
    public static final NoneEffect NONE = register(new NoneEffect());
    public static final ElementalDamage ELEMENTAL_DAMAGE = register(new ElementalDamage(
            Key.vo("elemental_damage")
    ));
    public static final FieldDamage FIELD_DAMAGE = register(new FieldDamage(
            Key.vo("field_damage")
    ));
    public static final Debuff BURN = register(new Debuff(
            Key.vo("burn"),
            Component.translatable(TranslationUtil.guiKey("burn"))
    ));
    public static final Damage FALL = register(new Damage(
            Key.vo("fall")
    ));

    private VOEffects() {

    }

    private static <T extends Copyable<T>, S extends VOEffect<T>> S register(S object) {
        REGISTRY.register(object);
        return object;
    }
}
