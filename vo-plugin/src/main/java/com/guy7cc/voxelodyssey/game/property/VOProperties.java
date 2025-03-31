package com.guy7cc.voxelodyssey.game.property;

import com.guy7cc.voxelodyssey.core.property.Property;
import com.guy7cc.voxelodyssey.core.property.VOCoreProperties;
import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.game.system.VOElementalVector;
import com.guy7cc.voxelodyssey.game.system.VOElementalVectorProperty;

public class VOProperties {
    // EFFECT PROPERTY
    public static final Property<VOElementalVector> ELEMENT;

    // MODIFIER PROPERTY
    public static final Property<VOElementalVector> ADD_VECTOR;
    public static final Property<VOElementalVector> MUL_VECTOR;

    static {
        // EFFECT PROPERTY
        ELEMENT = register(new VOElementalVectorProperty(
                Key.vo("element"),
                new VOElementalVector()
        ));

        // MODIFIER PROPERTY
        ADD_VECTOR = register(new VOElementalVectorProperty(
                Key.vo("add_vector"),
                new VOElementalVector(0d)
        ));
        MUL_VECTOR = register(new VOElementalVectorProperty(
                Key.vo("mul_vector"),
                new VOElementalVector(1d)
        ));
    }

    private VOProperties() {

    }

    private static <T, S extends Property<T>> S register(S object) {
        VOCoreProperties.REGISTRY.register(object);
        return object;
    }
}
