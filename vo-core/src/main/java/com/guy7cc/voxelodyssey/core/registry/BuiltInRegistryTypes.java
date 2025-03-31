package com.guy7cc.voxelodyssey.core.registry;

import com.guy7cc.voxelodyssey.core.entity.VOEntityType;
import com.guy7cc.voxelodyssey.core.item.VOItem;
import com.guy7cc.voxelodyssey.core.property.Property;

public class BuiltInRegistryTypes {
    public static final RegistryType<VOEntityType<?, ?>> ENTITY_TYPE = new RegistryType<>(Key.vo("entity_type"));
    public static final RegistryType<VOItem> ITEM = new RegistryType<>(Key.vo("item"));
    public static final RegistryType<Property<?>> PROPERTY = new RegistryType<>(Key.vo("property"));
}
