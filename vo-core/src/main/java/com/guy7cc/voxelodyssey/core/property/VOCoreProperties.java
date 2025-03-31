package com.guy7cc.voxelodyssey.core.property;

import com.guy7cc.voxelodyssey.core.VOCorePlugin;
import com.guy7cc.voxelodyssey.core.registry.IndexedKey;
import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.core.registry.Registry;

import java.util.UUID;

public class VOCoreProperties {
    public static final Registry<Property<?>> REGISTRY;

    // GENERAL
    public static final Property<Key> OWNER;

    // ITEM PROPERTY
    public static final Property<UUID> UNIQUE;
    public static final Property<Integer> USE;
    public static final Property<Integer> MAX_USE;
    public static final Property<Integer> COOLTIME;
    public static final Property<Key> COOLTIME_KEY;

    // EFFECT PROPERTY
    public static final Property<Integer> LEFT_TICK;
    public static final Property<Integer> INTERVAL;
    public static final Property<Double> DAMAGE;

    // MODIFIER PROPERTY
    public static final Property<IndexedKey> SOURCE;
    public static final Property<Integer> RANK;
    public static final Property<Double> ADD;
    public static final Property<Double> MUL;
    
    static {
        REGISTRY = new Registry<>(VOCorePlugin::getPlugin);

        // GENERAL
        OWNER = register(new KeyProperty(
                Key.vo("owner"),
                Key.vodefault()
        ));


        UNIQUE = register(new UuidProperty(
                Key.vo("unique")
        ));
        USE = register(new IntegerProperty(
                Key.vo("use"),
                0
        ));
        MAX_USE = register(new IntegerProperty(
                Key.vo("max_use"),
                10
        ));
        COOLTIME = register(new IntegerProperty(
                Key.vo("cooltime"),
                100
        ));
        COOLTIME_KEY = register(new KeyProperty(
                Key.vo("cooltime_key"),
                Key.vodefault()
        ));

        // EFFECT PROPERTY
        LEFT_TICK = register(new IntegerProperty(
                Key.vo("left_tick"),
                50
        ));
        INTERVAL = register(new IntegerProperty(
                Key.vo("interval"),
                20
        ));
        DAMAGE = register(new DoubleProperty(
                Key.vo("damage"),
                1d
        ));

        // MODIFIER PROPERTY
        SOURCE = register(new JsonSerializableProperty<>(
                Key.vo("source"),
                IndexedKey::none
        ));
        RANK = register(new IntegerProperty(
                Key.vo("rank"),
                0
        ));
        ADD = register(new DoubleProperty(
                Key.vo("add"),
                0d
        ));
        MUL = register(new DoubleProperty(
                Key.vo("mul"),
                0d
        ));
    }

    private VOCoreProperties() {
        
    }

    private static <T, S extends Property<T>> S register(S object) {
        REGISTRY.register(object);
        return object;
    }
}
